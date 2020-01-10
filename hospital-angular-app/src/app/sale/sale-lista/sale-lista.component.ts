import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SalaService} from "../sala.service";
import {Sala} from "../sala.model";
import {FormControl, FormGroup} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-sale-lista',
  templateUrl: './sale-lista.component.html',
  styleUrls: ['./sale-lista.component.css']
})
export class SaleListaComponent implements OnInit {

  sale: Sala[] = [];
  isLoading: boolean = false;
  formAddSala: FormGroup;
  formEditSala: FormGroup;


  constructor(private http: HttpClient,
              private salaService: SalaService) { }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }

  reloadData(){
    this.salaService.findAllSale().subscribe(sale =>{
      this.isLoading = true;
      this.sale = sale;
      this.isLoading = false;
    });
  }

  setupForm(){
    this.formAddSala = new FormGroup({
      pojemnosc : new FormControl(null),
      oddzial : new FormControl(null)
    })

    this.formEditSala = new FormGroup({
      nr_sali : new FormControl(null),
      pojemnosc : new FormControl(null),
      oddzial : new FormControl(null)
    })
  }

  resetForm(){
    this.formAddSala.reset();
    this.formEditSala.reset();
  }

  onSubmit(form: FormGroup){
    this.saveOrUpdateSala(form.getRawValue());
    this.resetForm();
  }

  saveOrUpdateSala(sala: Sala){
    this.salaService.saveOrUpdateSala(sala).subscribe(() => {
      this.reloadData();
    })
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  deleteSalaByNrSali(nr_sali : number){
    this.salaService.deleteSalaByNrSali(nr_sali).subscribe(()=>{
      this.reloadData();
    })
  }

  onConfirmDelete(nr_sali: number){
    if(confirm("Are you sure you want to delete?")){
      this.deleteSalaByNrSali(nr_sali);
    }
  }

  onEditSala(nr_sali: number, pojemnosc: number, oddzial:string){
    this.formEditSala.patchValue({
      'nr_sali' : nr_sali,
      'pojemnosc' : pojemnosc,
      'oddzial' : oddzial
    });
  }



}
