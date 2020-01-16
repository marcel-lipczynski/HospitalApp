import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {Lekarz} from "../../lekarze/lekarz.model";
import {Operacja} from "../operacja.model";
import {HttpClient} from "@angular/common/http";
import {OperacjaService} from "../operacja.service";
import {ActivatedRoute} from "@angular/router";
import {KartaService} from "../../karty/karta.service";
import {Lek} from "../../leki/lek.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-operacje-lista',
  templateUrl: './operacje-lista.component.html',
  styleUrls: ['./operacje-lista.component.css']
})
export class OperacjeListaComponent implements OnInit {

  operacje: Operacja[] = [];
  lekarze: Lekarz[] = [];
  lekarzeAvailable: Lekarz[] = [];
  isLoading: boolean = true;
  formAddOperacja: FormGroup;
  formEditOperacja: FormGroup;
  pesel: string;
  id_karty: number;


  constructor(private http: HttpClient,
              private operacjaService: OperacjaService,
              private kartaService: KartaService,
              private route: ActivatedRoute) { }

  ngOnInit() {

    this.id_karty = +this.route.snapshot.params['id_karty'];
    this.pesel = this.route.snapshot.params['pesel'];

    this.reloadData();
    this.setupForm();
  }

  reloadData() {
    this.operacjaService.findAllOperacjeForKartaPobytu(this.pesel, this.id_karty).subscribe(operacje => {
      this.findAllLekarzeOnKarta();
      this.findLekarzWhoCanAddOperacja();
      this.operacje = operacje;
      this.isLoading = false;
    });
  }

  findLekarzWhoCanAddOperacja(){
    return this.operacjaService.findLekarzWhoCanAddOperacja(this.id_karty).subscribe(lekarzeAvailable =>{
      this.lekarzeAvailable = lekarzeAvailable;
    })
  }

  findAllLekarzeOnKarta(){
    return this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarze =>{
      this.lekarze = lekarze;
    })
  }

  setupForm() {
    this.formAddOperacja = new FormGroup({
      nazwa_operacji: new FormControl(null,[Validators.maxLength(50),Validators.required]),
      termin: new FormControl(null, [Validators.required,this.checkIfTerminIsBiggerThanToday],
        [this.checkIfTerminNotLessThanDataPrzyjecia.bind(this),
          this.checkIfTerminNotBiggerThanDataWypisu.bind(this)]),
      id_lekarza: new FormControl(null,[Validators.required]),
      id_karty: new FormControl(this.id_karty)
    });

    this.formEditOperacja = new FormGroup({
      id_operacji: new FormControl(null),
      nazwa_operacji: new FormControl(null,[Validators.maxLength(50),Validators.required]),
      termin: new FormControl(null, [Validators.required,this.checkIfTerminIsBiggerThanToday],
        [this.checkIfTerminNotLessThanDataPrzyjecia.bind(this),
          this.checkIfTerminNotBiggerThanDataWypisu.bind(this)]),
      id_lekarza: new FormControl(null, [Validators.required]),
      id_karty: new FormControl(this.id_karty)
    });
  }

  onEditForm(id_operacji: number, nazwa_operacji: string, termin: string, id_lekarza: number) {
    this.formEditOperacja.patchValue({
      'id_operacji': id_operacji,
      'nazwa_operacji': nazwa_operacji,
      "termin" : termin,
      'id_lekarza': id_lekarza
    });
  }

  onSubmit(form: FormGroup) {
    this.saveOrUpdatOperacjaInKartaPobytu(form.getRawValue());
    this.resetForm();
  }

  resetForm() {
    this.formAddOperacja.reset();
  }

  saveOrUpdatOperacjaInKartaPobytu(operacja: Operacja) {
    this.operacjaService.saveOrUpdatOperacjaInKartaPobytu(operacja, this.pesel, this.id_karty).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  deletOperacjaByIdFromKartaPobytu(id_operacji: number) {
    this.operacjaService.deletOperacjaByIdFromKartaPobytu(id_operacji, this.pesel, this.id_karty).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(id_operacji: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deletOperacjaByIdFromKartaPobytu(id_operacji);
    }
  }



  checkIfTerminNotLessThanDataPrzyjecia(control: AbstractControl): Promise<any> | Observable<any>{
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


  checkIfTerminNotBiggerThanDataWypisu(control: AbstractControl): Promise<any> | Observable<any>{
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

  checkIfTerminIsBiggerThanToday(control: AbstractControl): { [dateIsLess: string]: boolean } {
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



}
