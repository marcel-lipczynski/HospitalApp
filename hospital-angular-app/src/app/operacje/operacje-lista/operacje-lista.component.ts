import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Lekarz} from "../../lekarze/lekarz.model";
import {Operacja} from "../operacja.model";
import {HttpClient} from "@angular/common/http";
import {OperacjaService} from "../operacja.service";
import {ActivatedRoute} from "@angular/router";
import {KartaService} from "../../karty/karta.service";

@Component({
  selector: 'app-operacje-lista',
  templateUrl: './operacje-lista.component.html',
  styleUrls: ['./operacje-lista.component.css']
})
export class OperacjeListaComponent implements OnInit {

  operacje: Operacja[] = [];
  lekarze: Lekarz[] = [];
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
      this.operacje = operacje;
      this.isLoading = false;
    });
  }

  findAllLekarzeOnKarta(){
    return this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarze =>{
      this.lekarze = lekarze;
    })
  }

  setupForm() {
    this.formAddOperacja = new FormGroup({
      nazwa_operacji: new FormControl(null),
      termin: new FormControl(null),
      id_lekarza: new FormControl(null),
      id_karty: new FormControl(this.id_karty)
    });

    this.formEditOperacja = new FormGroup({
      id_operacji: new FormControl(null),
      nazwa_operacji: new FormControl(null),
      termin: new FormControl(null),
      id_lekarza: new FormControl(null),
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

}
