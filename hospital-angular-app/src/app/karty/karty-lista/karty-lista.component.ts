import {Component, OnInit} from '@angular/core';
import {Karta} from "../karta.model";
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {KartaService} from "../karta.service";
import {ActivatedRoute} from "@angular/router";
import {PacjentService} from "../../pacjenci/pacjent.service";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {SalaService} from "../../sale/sala.service";
import {Sala} from "../../sale/sala.model";

@Component({
  selector: 'app-karty-lista',
  templateUrl: './karty-lista.component.html',
  styleUrls: ['./karty-lista.component.css']
})
export class KartyListaComponent implements OnInit {

  karty: Karta[] = [];
  sale: Sala[] = [];
  isLoading: boolean = true;
  formAddKarta: FormGroup;
  formEditKarta: FormGroup;
  pesel: string;
  imie: string;
  nazwisko: string;
  numery_sal: number[] = [];



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
    this.loadPacjent();
    this.loadAvailableSale();
  }

  loadPacjent() {
    this.pacjentService.findPacjentByPesel(this.pesel).subscribe(pacjent => {
      this.isLoading = true;
      this.imie = pacjent.imie;
      this.nazwisko = pacjent.nazwisko;
      this.isLoading = false;
    });
  }

  loadAvailableSale(){
    this.salaService.findAllSale().subscribe(sale =>{
      this.isLoading = true;
      this.sale = sale;
      this.isLoading = false;
    });
  }

  reloadData() {
    this.kartaService.findAllKartyOfPacjent(this.pesel).subscribe(karty => {
      this.karty = karty;
      // this.loadPacjent();
      this.isLoading = false;
    });
  }

  setupForm() {
    this.formAddKarta = new FormGroup({
      data_przyjecia: new FormControl(null),
      godzina_przyjecia: new FormControl(null),
      data_wypisu: new FormControl(null),
      nr_sali: new FormControl(null),
      pesel: new FormControl(this.pesel)
    });

    this.formEditKarta = new FormGroup({
      id_karty: new FormControl(null),
      data_przyjecia: new FormControl(null),
      godzina_przyjecia: new FormControl(null),
      data_wypisu: new FormControl(null),
      nr_sali: new FormControl(null),
      pesel: new FormControl(this.pesel)
    });
  }

  onEditKarta(id_karty: number, data_przyjecia: string, godzina_przyjecia: string,
              data_wypisu: string, nr_sali: number) {
      this.formEditKarta.patchValue({
        'id_karty': id_karty,
        'data_przyjecia' : data_przyjecia,
        'godzina_przyjecia' : godzina_przyjecia,
        'data_wypisu' : data_wypisu,
        'nr_sali' : nr_sali
      });
  }

  resetForm() {
    this.formAddKarta.reset();
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


}
