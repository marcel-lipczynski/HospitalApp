import { Component, OnInit } from '@angular/core';
import {Pielegniarka} from "../pielegniarka.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {SalaService} from "../../sale/sala.service";
import {PielegniarkaService} from "../pielegniarka.service";
import {ActivatedRoute} from "@angular/router";
import {Sala} from "../../sale/sala.model";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-pielegniarki-sale',
  templateUrl: './pielegniarki-sale.component.html',
  styleUrls: ['./pielegniarki-sale.component.css']
})
export class PielegniarkiSaleComponent implements OnInit {

  saleFromPielegniarka: Sala[] = [];
  sale: Sala[] = [];
  formAddSala: FormGroup;
  isLoading: boolean = true;
  id_pielegniarki : number;



  constructor(private http: HttpClient,
              private salaService: SalaService,
              private pielegniarkaService: PielegniarkaService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.id_pielegniarki = +this.route.snapshot.params['id_pielegniarki'];

    this.fetchAvailableSale();
    this.reloadData();
    this.setupForm();

  }

  reloadData() {
    this.pielegniarkaService.findAllSaleOfPielegniarka(this.id_pielegniarki).subscribe(saleFromPielegniarka => {
      this.fetchAvailableSale();
      this.saleFromPielegniarka = saleFromPielegniarka;
      this.isLoading = false;
    });
  }

  fetchAvailableSale(){
    this.pielegniarkaService.findAvailableSaleForPielegniarka(this.id_pielegniarki).subscribe(sale =>{
      this.sale = sale;
    })
  }



  setupForm() {
    this.formAddSala = new FormGroup({
      nr_sali: new FormControl(null, [Validators.required])
    });

  }

  onSubmit(form: FormGroup) {
    this.addSalaToPielegniarka(form.getRawValue());
    this.reloadData();
    this.resetForm();
  }

  resetForm() {
    this.formAddSala.reset();
  }

  addSalaToPielegniarka(sala: Sala) {
    this.pielegniarkaService.addSalaToPielegniarka(this.id_pielegniarki, sala.nr_sali).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
  }

  deleteSalaFromPielegniarka(nr_sali: number) {
    this.pielegniarkaService.deleteSalaFromPielegniarka(this.id_pielegniarki, nr_sali).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(nr_sali: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteSalaFromPielegniarka(nr_sali);
    }
  }


}
