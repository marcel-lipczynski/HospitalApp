import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SalaService} from "../sala.service";
import {Sala} from "../sala.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-sale-lista',
  templateUrl: './sale-lista.component.html',
  styleUrls: ['./sale-lista.component.css']
})
export class SaleListaComponent implements OnInit {

  sale: Sala[] = [];
  filteredSale: Sala[] = [];
  isLoading: boolean = true;
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
      this.filteredSale = sale;
      this.sale = sale;
      this.isLoading = false;
    });
  }

  setupForm(){
    this.formAddSala = new FormGroup({
      pojemnosc : new FormControl(null, [Validators.required, Validators.max(9)
      ,Validators.min(1),Validators.pattern(/^\d+$/)]),
      oddzial : new FormControl(null,[Validators.required,
        Validators.maxLength(50),Validators.pattern(/^[A-Za-z ]+$/)])

    });

    this.formEditSala = new FormGroup({
      nr_sali : new FormControl({value: null, disabled: true}),
      pojemnosc : new FormControl(null, [Validators.required, Validators.max(9)
        ,Validators.min(1),Validators.pattern(/^\d+$/)]),
      oddzial : new FormControl(null,[Validators.required,
        Validators.maxLength(50),Validators.pattern(/^[A-Za-z ]+$/)])
    });
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

  filterSale(filter: string): Sala[]{
    this.filteredSale = [];
    if(filter === ''){
      this.filteredSale = this.sale;
      return this.filteredSale;
    }
    for(let sala of this.sale){
      if(sala.nr_sali === +filter || sala.oddzial === filter){
        this.filteredSale.push(sala);
      }
    }
    return this.filteredSale;
  }



}
