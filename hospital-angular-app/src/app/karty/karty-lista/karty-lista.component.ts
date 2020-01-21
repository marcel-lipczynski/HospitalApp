import {Component, OnInit} from '@angular/core';
import {Karta} from "../karta.model";
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {KartaService} from "../karta.service";
import {ActivatedRoute} from "@angular/router";
import {PacjentService} from "../../pacjenci/pacjent.service";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {SalaService} from "../../sale/sala.service";
import {Sala} from "../../sale/sala.model";
import {map} from 'rxjs/operators'
import {Lekarz} from "../../lekarze/lekarz.model";

@Component({
  selector: 'app-karty-lista',
  templateUrl: './karty-lista.component.html',
  styleUrls: ['./karty-lista.component.css']
})
export class KartyListaComponent implements OnInit {

  karty: Karta[] = [];
  filteredKarty: Karta[] = [];
  sala: Sala;
  sale: Sala[] = [];
  isLoading: boolean = true;
  formAddKarta: FormGroup;
  formEditKarta: FormGroup;
  pesel: string;
  imie: string;
  nazwisko: string;
  index: number;


  constructor(private http: HttpClient,
              private kartaService: KartaService,
              private pacjentService: PacjentService,
              private salaService: SalaService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.pesel = this.route.snapshot.params['pesel'];
    this.reloadData();
    this.setupForm();

  }

  addWypisToKarta(id_karty: number){
    this.kartaService.addWypis(id_karty).subscribe();
    this.reloadData();
  }


  loadPacjent() {
    this.pacjentService.findPacjentByPesel(this.pesel).subscribe(pacjent => {
      this.isLoading = true;
      this.imie = pacjent.imie;
      this.nazwisko = pacjent.nazwisko;
      this.isLoading = false;
    });
  }

  loadAvailableSale() {
    this.salaService.findAllAvailableSale().subscribe(sale => {
      this.isLoading = true;
      this.sale = sale;
      this.isLoading = false;
    });
  }

  reloadData() {
    this.kartaService.findAllKartyOfPacjent(this.pesel).subscribe(karty => {
      this.isLoading = true;
      this.karty = karty;
      this.filteredKarty = karty;
      this.loadPacjent();
      this.loadAvailableSale();
      this.isLoading = false;
    });
  }

  checkIfExistsActiveKarta(): boolean {
    for(let karta of this.karty){
      if(karta.data_wypisu == null){
        return true;
      }
    }
    return false;
  }

  setupForm() {
    this.formAddKarta = new FormGroup({
      data_przyjecia: new FormControl(null, [KartyListaComponent.checkIfDateIsBiggerThanToday,
        Validators.required, this.checkIfKartaWithGivenDateExists.bind(this)]),
      godzina_przyjecia: new FormControl(null, [Validators.required]),
      data_wypisu: new FormControl(null),
      nr_sali: new FormControl(null, [Validators.required]),
      pesel: new FormControl(this.pesel)
    });


    this.formEditKarta = new FormGroup({
      id_karty: new FormControl(null),
      data_przyjecia: new FormControl({value: null, disabled: true}),
      godzina_przyjecia: new FormControl({value: null, disabled:true}),
      data_wypisu: new FormControl(null),
      nr_sali: new FormControl(null,[Validators.required]),
      pesel: new FormControl(this.pesel)
    });

    this.formEditKarta.get('data_wypisu').setValidators([this.lessThan('data_przyjecia'),KartyListaComponent.checkIfDateIsBiggerThanToday])

  }

  onEditKarta(id_karty: number, data_przyjecia: string, godzina_przyjecia: string,
              data_wypisu: string, nr_sali: number) {
    this.formEditKarta.patchValue({
      'id_karty': id_karty,
      'data_przyjecia': data_przyjecia,
      'godzina_przyjecia': godzina_przyjecia,
      'data_wypisu': data_wypisu,
      'nr_sali': nr_sali
    });


  }

  resetForm() {
    this.formAddKarta.reset();
    this.formEditKarta.reset();
  }

  saveOrUpdateKarta(karta: Karta) {
    this.kartaService.saveOrUpdateKartaForPacjent(karta, this.pesel).subscribe(() => {
      this.reloadData();
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  onSubmit(form: FormGroup) {
    this.saveOrUpdateKarta(form.getRawValue());
    this.resetForm();
  }

  deleteKartaFromPacjent(id_karty: number) {
    this.kartaService.deleteKartaFromPacjent(id_karty, this.pesel).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(id_karty: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteKartaFromPacjent(id_karty);
    }
  }

  static checkIfDateIsBiggerThanToday(control: AbstractControl): { [dateIsLess: string]: boolean } {
    const currentDate = new Date();
    const data_przyjecia = new Date(control.value);
    if(data_przyjecia.getUTCFullYear() > currentDate.getUTCFullYear()){
      return {'dateIsBigger': true };
    }
    if(data_przyjecia.getUTCFullYear() === currentDate.getUTCFullYear()
      && data_przyjecia.getUTCMonth() > currentDate.getUTCMonth()){
      return {'dateIsBigger': true };
    }
    if(data_przyjecia.getUTCFullYear() === currentDate.getUTCFullYear()
      && data_przyjecia.getUTCMonth() === currentDate.getUTCMonth()
      && data_przyjecia.getDate() > currentDate.getDate()){
      return {'dateIsBigger': true };

    }
    return null;

  }

  checkIfKartaWithGivenDateExists(control: AbstractControl): { [duplicateDate: string]: boolean } {
    const controlValue = new Date(control.value);
    for(let karta of this.karty){
      if(controlValue.getDate() === new Date(karta.data_przyjecia).getDate() &&
      controlValue.getUTCMonth() == new Date(karta.data_przyjecia).getUTCMonth() &&
      controlValue.getUTCFullYear() == new Date(karta.data_przyjecia).getUTCFullYear()){
        return {'duplicateDate': true};
      }
    }
    return null;
  }




  lessThan(field: string): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} => {
      const group = control.parent;
      const fieldToCompare = group.get(field);
      const data_przyjecia = new Date(fieldToCompare.value);
      const data_wypisu = new Date(control.value);
      if(data_przyjecia.getUTCFullYear() > data_wypisu.getUTCFullYear()){
        return {'greaterThan': {value: control.value}};
      }
      if(data_przyjecia.getUTCFullYear() === data_wypisu.getUTCFullYear()
        && data_przyjecia.getUTCMonth() > data_wypisu.getUTCMonth()){
        return {'greaterThan': {value: control.value}};
      }
      if(data_przyjecia.getUTCFullYear() === data_wypisu.getUTCFullYear()
        && data_przyjecia.getUTCMonth() === data_wypisu.getUTCMonth()
        && data_przyjecia.getDate() > data_wypisu.getDate()){
        return {'greaterThan': {value: control.value}};

      }
      return null;
    }
  }

  filterKarty(filter: string): Karta[]{
    this.filteredKarty = [];
    if(filter === ''){
      this.filteredKarty = this.karty;
      return this.filteredKarty;
    }
    for(let karta of this.karty){
      if(karta.data_przyjecia === filter || karta.godzina_przyjecia === filter){
        this.filteredKarty.push(karta);
      }
    }
    return this.filteredKarty;
  }



}
