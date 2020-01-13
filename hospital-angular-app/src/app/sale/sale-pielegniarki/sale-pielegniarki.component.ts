import { Component, OnInit } from '@angular/core';
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {HttpClient} from "@angular/common/http";
import {LekService} from "../../leki/lek.service";
import {ReceptaService} from "../../recepty/recepta.service";
import {ActivatedRoute} from "@angular/router";
import {SalaService} from "../sala.service";
import {PielegniarkaService} from "../../pielegniarki/pielegniarka.service";
import {Pielegniarka} from "../../pielegniarki/pielegniarka.model";
import {FormControl, FormGroup} from "@angular/forms";
import {Lek} from "../../leki/lek.model";

@Component({
  selector: 'app-sale-pielegniarki',
  templateUrl: './sale-pielegniarki.component.html',
  styleUrls: ['./sale-pielegniarki.component.css']
})
export class SalePielegniarkiComponent implements OnInit {

  pielegniarkiFromSala: Pielegniarka[] = [];
  pielegiarki: Pielegniarka[] = [];
  formAddPielegniarka: FormGroup;
  isLoading: boolean = true;
  nr_sali : number;



  constructor(private http: HttpClient,
              private salaService: SalaService,
              private pielegniarkaService: PielegniarkaService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.nr_sali = +this.route.snapshot.params['nr_sali'];

    this.fetchAvailablePielegniarki();
    this.reloadData();
    this.setupForm();

  }

  reloadData() {
    this.salaService.findAllPielegniarkiInSala(this.nr_sali).subscribe(pielegniarkiFromSala => {
      this.pielegniarkiFromSala = pielegniarkiFromSala;
      this.isLoading = false;
    });
  }

  fetchAvailablePielegniarki(){
    this.pielegniarkaService.findAllPielegniarki().subscribe(pielegniarki =>{
      this.pielegiarki = pielegniarki;
    })
  }



  setupForm() {
    this.formAddPielegniarka = new FormGroup({
      id_pielegniarki: new FormControl(null)
    });

  }

  onSubmit(form: FormGroup) {
    this.addPielegniarkaToSala(form.getRawValue());
    this.resetForm();
  }

  resetForm() {
    this.formAddPielegniarka.reset();
  }

  addPielegniarkaToSala(pielegniarka: Pielegniarka) {
    this.salaService.addPielegniarkaToSala(this.nr_sali, pielegniarka.id_pielegniarki).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
  }

  deletePielegniarkaFromSala(id_pielegniarki: number) {
    this.salaService.deletePielegniarkaFromSala(this.nr_sali, id_pielegniarki).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(id_pielegniarki: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deletePielegniarkaFromSala(id_pielegniarki);
    }
  }




}
