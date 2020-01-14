import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LekarzService} from "../lekarz.service";
import {Lekarz} from "../lekarz.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-lekarze-lista',
  templateUrl: './lekarze-lista.component.html',
  styleUrls: ['./lekarze-lista.component.css']
})
export class LekarzeListaComponent implements OnInit {

  lekarze: Lekarz[] = [];
  isLoading: boolean = true;
  formAddLekarz: FormGroup;
  fromEditLekarz: FormGroup;


  constructor(private http: HttpClient,
              private lekarzService: LekarzService) { }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }


  reloadData(){
    this.lekarzService.findAllLekarze().subscribe(lekarze =>{
      this.lekarze = lekarze;
      this.isLoading = false;
    });
  }

  setupForm(){
    this.formAddLekarz = new FormGroup({
      'imie' : new FormControl(null,[Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      'nazwisko' : new FormControl(null,[Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      'placa_pod' : new FormControl(null,[Validators.required,Validators.max(99999), Validators.min(1), Validators.pattern(/^\d+$/)])
    });

    this.fromEditLekarz = new FormGroup({
      'id_lekarza' : new FormControl(null),
      'imie' : new FormControl(null,[Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      'nazwisko' : new FormControl(null,[Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      'placa_pod' : new FormControl(null, [Validators.required,Validators.max(99999), Validators.min(1), Validators.pattern(/^\d+$/)])
    });
  }

  onSubmit(form: FormGroup){
    this.saveOrUpdateLekarz(form.getRawValue());
    this.resetForm();
  }

  resetForm(){
    this.formAddLekarz.reset();
  }

  onEditLekarz(id_lekarza: number, imie: string, nazwisko: string, placa_pod: number){
    this.fromEditLekarz.patchValue({
      'id_lekarza' : id_lekarza,
      'imie' : imie,
      'nazwisko' : nazwisko,
      'placa_pod' : placa_pod
    });
  }

  saveOrUpdateLekarz(lekarz: Lekarz){
    this.lekarzService.saveOrUpdateLekarz(lekarz).subscribe(() =>{
      this.reloadData();
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  onConfirmDelete(id_lekarza: number){
    if(confirm("Are you sure you want to delete?")){
      this.deleteLekarzById(id_lekarza);
    }
  }

  deleteLekarzById(id_lekarza: number){
    this.lekarzService.deleteLekarzById(id_lekarza).subscribe(() =>{
      this.reloadData();
    });
  }

}
