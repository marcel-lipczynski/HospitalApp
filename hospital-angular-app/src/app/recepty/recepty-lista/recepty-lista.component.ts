import { Component, OnInit } from '@angular/core';
import {Recepta} from "../recepta.model";
import {Lekarz} from "../../lekarze/lekarz.model";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ReceptaService} from "../recepta.service";
import {KartaService} from "../../karty/karta.service";
import {ActivatedRoute} from "@angular/router";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {Observable} from "rxjs";
import {Karta} from "../../karty/karta.model";

@Component({
  selector: 'app-recepty-lista',
  templateUrl: './recepty-lista.component.html',
  styleUrls: ['./recepty-lista.component.css']
})
export class ReceptyListaComponent implements OnInit {

  recepty: Recepta[] = [];
  filteredRecepty: Recepta[] = [];
  lekarze: Lekarz[] = [];
  lekarzeAvailable: Lekarz[] = [];
  isLoading: boolean = true;
  formAddRecepta: FormGroup;
  formEditRecepta: FormGroup;
  pesel: string;
  id_karty: number;


  constructor(private http: HttpClient,
              private receptaService: ReceptaService,
              private kartaService: KartaService,
              private route: ActivatedRoute) {

  }

  ngOnInit() {

    this.id_karty = +this.route.snapshot.params['id_karty'];
    this.pesel = this.route.snapshot.params['pesel'];

    this.reloadData();
    this.setupForm();
  }

  reloadData() {
    this.receptaService.findAllReceptyForKartaPobytu(this.pesel, this.id_karty).subscribe(recepty => {
      this.findAllLekarzeOnKarta();
      this.findLekarzWhoCanAddRecepta();
      this.recepty = recepty;
      this.filteredRecepty = recepty;
      this.isLoading = false;
    });
  }

  findAllLekarzeOnKarta(){
    return this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarze =>{
      this.lekarze = lekarze;
    });
  }

  findLekarzWhoCanAddRecepta(){
    return this.receptaService.findLekarzWhoCanAddRecepta(this.id_karty).subscribe(lekarzeAvailable =>{
      this.lekarzeAvailable = lekarzeAvailable;
    })
  }

  setupForm() {
    this.formAddRecepta = new FormGroup({
      data_wystawienia: new FormControl(null, [Validators.required,this.checkIfDateIsBiggerThanToday],
        [this.checkIfDataWystawieniaNotLessThanDataPrzyjecia.bind(this),
          this.checkIfDataWystawieniaNotBiggerThanDataWypisu.bind(this)]),
      id_lekarza: new FormControl(null,[Validators.required]),
      id_karty: new FormControl(this.id_karty)
    });

    this.formEditRecepta = new FormGroup({
      id_recepty: new FormControl(null),
      data_wystawienia: new FormControl(null, [Validators.required,this.checkIfDateIsBiggerThanToday],
        [this.checkIfDataWystawieniaNotLessThanDataPrzyjecia.bind(this),
          this.checkIfDataWystawieniaNotBiggerThanDataWypisu.bind(this)]),
      id_lekarza: new FormControl(null,[Validators.required]),
      id_karty: new FormControl(this.id_karty)
    });
  }

  onEditForm(id_recepty: number, data_wystawienia: string, id_lekarza: number) {
    this.formEditRecepta.patchValue({
      'id_recepty': id_recepty,
      'data_wystawienia': data_wystawienia,
      'id_lekarza': id_lekarza
    });
  }

  onSubmit(form: FormGroup) {
    this.saveOrUpdateReceptaInKartaPobytu(form.getRawValue());
    this.resetForm();
  }

  resetForm() {
    this.formAddRecepta.reset();
  }

  saveOrUpdateReceptaInKartaPobytu(recepta: Recepta) {
    this.receptaService.saveOrUpdateReceptaInKartaPobytu(recepta, this.pesel, this.id_karty).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  deleteReceptaByIdFromKartaPobytu(id_recepty: number) {
    this.receptaService.deleteReceptaByIdFromKartaPobytu(id_recepty, this.pesel, this.id_karty).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(id_recepty: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteReceptaByIdFromKartaPobytu(id_recepty);
    }
  }

  checkIfDataWystawieniaNotLessThanDataPrzyjecia(control: AbstractControl): Promise<any> | Observable<any>{
    return new Promise<any>((resolve) => {
      this.kartaService.findKartaById(this.id_karty).subscribe(karta => {
        const dataWystawieniaDiagnozy = new Date(control.value);
        const data_przyjecia = new Date(karta.data_przyjecia);
        if (data_przyjecia.getUTCFullYear() > dataWystawieniaDiagnozy.getUTCFullYear()) {
          resolve({'dateNotLess': true});
        } else if (data_przyjecia.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
          && data_przyjecia.getUTCMonth() > dataWystawieniaDiagnozy.getUTCMonth()) {
          resolve({'dateNotLess': true});
        } else if (data_przyjecia.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
          && data_przyjecia.getUTCMonth() === dataWystawieniaDiagnozy.getUTCMonth()
          && data_przyjecia.getDate() > dataWystawieniaDiagnozy.getDate()) {
          resolve({'dateNotLess': true});
        } else {
          resolve(null);
        }

      });
    });

  }


  checkIfDataWystawieniaNotBiggerThanDataWypisu(control: AbstractControl): Promise<any> | Observable<any>{
    return new Promise<any>((resolve) => {
      this.kartaService.findKartaById(this.id_karty).subscribe(karta => {
        if(karta.data_wypisu == null){
          resolve(null);
        }
        const dataWystawieniaDiagnozy = new Date(control.value);
        const data_wypisu = new Date(karta.data_wypisu);

        if (data_wypisu.getUTCFullYear() < dataWystawieniaDiagnozy.getUTCFullYear()) {
          resolve({'dateNotBigger': true});
        } else if (data_wypisu.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
          && data_wypisu.getUTCMonth() < dataWystawieniaDiagnozy.getUTCMonth()) {
          resolve({'dateNotBigger': true});
        } else if (data_wypisu.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
          && data_wypisu.getUTCMonth() === dataWystawieniaDiagnozy.getUTCMonth()
          && data_wypisu.getDate() < dataWystawieniaDiagnozy.getDate()) {
          resolve({'dateNotBigger': true});
        } else {
          resolve(null);
        }

      });
    });

  }

  checkIfDateIsBiggerThanToday(control: AbstractControl): { [dateIsLess: string]: boolean } {
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


  filterRecepty(filter: string): Recepta[]{
    this.filteredRecepty = [];
    if(filter === ''){
      this.filteredRecepty = this.recepty;
      return this.filteredRecepty;
    }
    for(let recepta of this.recepty){
      if(recepta.data_wystawienia === filter){
        this.filteredRecepty.push(recepta);
      }
    }
    return this.filteredRecepty;
  }





}
