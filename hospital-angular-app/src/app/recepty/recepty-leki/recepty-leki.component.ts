import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ReceptaService} from "../recepta.service";
import {Lekarz} from "../../lekarze/lekarz.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {KartaService} from "../../karty/karta.service";
import {ActivatedRoute} from "@angular/router";
import {Recepta} from "../recepta.model";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {Lek} from "../../leki/lek.model";
import {LekService} from "../../leki/lek.service";

@Component({
  selector: 'app-recepty-leki',
  templateUrl: './recepty-leki.component.html',
  styleUrls: ['./recepty-leki.component.css']
})
export class ReceptyLekiComponent implements OnInit {

  leki: Lek[] = [];
  lekiOnRecepta: Lek[] = [];
  isLoading: boolean = true;
  formAddLek: FormGroup;
  pesel: string;
  id_karty: number;
  id_recepty: number;


  constructor(private http: HttpClient,
              private lekService: LekService,
              private receptaService: ReceptaService,
              private route: ActivatedRoute) {

  }

  ngOnInit() {

    this.id_karty = +this.route.snapshot.params['id_karty'];
    this.pesel = this.route.snapshot.params['pesel'];
    this.id_recepty = this.route.snapshot.params['id_recepty'];

    this.fetchAvailableLeki();
    this.reloadData();
    this.setupForm();
  }

  reloadData() {
    this.receptaService.findAllLekiOnRecepta(this.id_recepty).subscribe(lekiOnRecepta => {
      this.fetchAvailableLeki();
      this.lekiOnRecepta = lekiOnRecepta;
      this.isLoading = false;
    });
  }

  fetchAvailableLeki(){
    this.receptaService.findAvailableLekiForRecepta(this.id_recepty).subscribe(leki =>{
      this.isLoading = true;
      this.leki = leki;
      this.isLoading = false;
    })
  }



  setupForm() {
    this.formAddLek = new FormGroup({
      nazwa_leku: new FormControl(null,[Validators.required])
    });

  }

  onSubmit(form: FormGroup) {

    this.addLekToRecepta(form.getRawValue());
    this.reloadData();
    this.resetForm();
  }

  resetForm() {
    this.formAddLek.reset();
  }

  addLekToRecepta(lek: Lek) {
    this.receptaService.addLekToRecepta(this.id_recepty, lek.nazwa_leku).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
  }

  deleteLekFromRecepta(nazwa_leku: string) {
    this.receptaService.deleteLekFromRecepta(this.id_recepty, nazwa_leku).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(nazwa_leku: string) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteLekFromRecepta(nazwa_leku);
    }
  }

}
