import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SpecjalizacjaService} from "../specjalizacja.service";
import {Specjalizacja} from "../specjalizacja.model";
import {FormControl, FormGroup} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-specjalizacje-lista',
  templateUrl: './specjalizacje-lista.component.html',
  styleUrls: ['./specjalizacje-lista.component.css']
})
export class SpecjalizacjeListaComponent implements OnInit {

  specjalizacje: Specjalizacja[] = [];
  isLoading: boolean = true;
  formAddSpecjalizacja: FormGroup;
  formEditSpecjalizacja: FormGroup;


  constructor(private http: HttpClient,
              private specjalizacjaService: SpecjalizacjaService) { }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }

  reloadData(){
    this.specjalizacjaService.findAllSpecjalizacje().subscribe(specjalizacje => {
      this.specjalizacje = specjalizacje;
      this.isLoading = false;
    })
  }

  setupForm(){
    this.formAddSpecjalizacja = new FormGroup({
      nazwa_specjalizacji: new FormControl(null),
      placa_min: new FormControl(null),
      placa_max: new FormControl(null)
    });

    this.formEditSpecjalizacja = new FormGroup({
      nazwa_specjalizacji: new FormControl({value: null, disabled: true}),
      placa_min: new FormControl(null),
      placa_max: new FormControl(null)
    });
  }

  onSubmit(form: FormGroup){
    this.saveOrUpdateSpecjalizacja(form.getRawValue());
    this.resetForm();
  }

  saveOrUpdateSpecjalizacja(specjalizacja: Specjalizacja){
    this.specjalizacjaService.saveOrUpdateSpecjalizacja(specjalizacja).subscribe(()=>{
      this.reloadData();
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  resetForm(){
    this.formAddSpecjalizacja.reset();
  }

  deleteSpecjalizacjaByNazwa(nazwa_specjalizacji: string){
    this.specjalizacjaService.deleteSpecjalizacjaByNazwa(nazwa_specjalizacji).subscribe(()=>{
      this.reloadData();
    })
  }

  onConfirmDelete(nazwa_specjalizacji: string){
    if(confirm("Are you sure you want to delete?")){
      this.deleteSpecjalizacjaByNazwa(nazwa_specjalizacji);
    }
  }

  onEditSpecjalizacja(nazwa_specjalizacji: string, placa_min: number, placa_max: number){
    this.formEditSpecjalizacja.patchValue({
      'nazwa_specjalizacji' : nazwa_specjalizacji,
      'placa_min': placa_min,
      'placa_max': placa_max
    });
  }




}
