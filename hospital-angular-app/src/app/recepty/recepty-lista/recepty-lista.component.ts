import { Component, OnInit } from '@angular/core';
import {Recepta} from "../recepta.model";
import {Lekarz} from "../../lekarze/lekarz.model";
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ReceptaService} from "../recepta.service";
import {KartaService} from "../../karty/karta.service";
import {ActivatedRoute} from "@angular/router";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-recepty-lista',
  templateUrl: './recepty-lista.component.html',
  styleUrls: ['./recepty-lista.component.css']
})
export class ReceptyListaComponent implements OnInit {

  recepty: Recepta[] = [];
  lekarze: Lekarz[] = [];
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
      this.recepty = recepty;
      this.isLoading = false;
    });
  }

  findAllLekarzeOnKarta(){
    return this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarze =>{
      this.lekarze = lekarze;
    })
  }

  setupForm() {
    this.formAddRecepta = new FormGroup({
      data_wystawienia: new FormControl(null),
      id_lekarza: new FormControl(null),
      id_karty: new FormControl(this.id_karty)
    });

    this.formEditRecepta = new FormGroup({
      id_recepty: new FormControl(null),
      data_wystawienia: new FormControl(null),
      id_lekarza: new FormControl(null),
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

}
