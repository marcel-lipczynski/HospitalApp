import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pielegniarka} from "../pielegniarka.model";
import {PielegniarkaService} from "../pielegniarka.service";
import {FormControl, FormGroup} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-pielegniarki-lista',
  templateUrl: './pielegniarki-lista.component.html',
  styleUrls: ['./pielegniarki-lista.component.css']
})
export class PielegniarkiListaComponent implements OnInit {

  formAddPielegniarka: FormGroup;
  formEditPielegniarka: FormGroup;
  pielegniarki: Pielegniarka[] = [];
  isLoading: boolean = false;


  constructor(private pielegniarkaService: PielegniarkaService,
              private http: HttpClient) { }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }

  reloadData(){
    this.pielegniarkaService.findAllPielegniarki().subscribe(pielegniarki =>{
      this.isLoading = true;
      this.pielegniarki = pielegniarki;
      this.isLoading = false;
    })
  }

  deletePielegniarkaById(id_pielegniarki: number){
    this.pielegniarkaService.deletePielegniarkaById(id_pielegniarki).subscribe(() => {
      this.reloadData();
    });
  }

  setupForm(){
    this.formAddPielegniarka = new FormGroup({
      imie: new FormControl(null),
      nazwisko: new FormControl(null),
      placa: new FormControl(null)
    });

    this.formEditPielegniarka = new FormGroup({
      id_pielegniarki: new FormControl(null),
      imie: new FormControl(null),
      nazwisko: new FormControl(null),
      placa: new FormControl(null)
    });



  }

  resetForm(){
    this.formEditPielegniarka.reset();
    this.formAddPielegniarka.reset();
  }

  onSubmit(form: FormGroup){
    this.saveOrUpdatePielegniarka(form.getRawValue());
    this.resetForm();
  }

  saveOrUpdatePielegniarka(pielegniarka: Pielegniarka){
    this.pielegniarkaService.saveOrUpdatePielegniarka(pielegniarka).subscribe(() =>
    this.reloadData());
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  onConfirmDelete(id: number){
    if(confirm("Are you sure you want to delete?")){
      this.deletePielegniarkaById(id);
    }
  }

  onEditPielegniarka(id:number, imie: string, nazwisko: string, placa:number){
    this.formEditPielegniarka.patchValue({
      'id_pielegniarki' : id,
      'imie' : imie,
      'nazwisko' : nazwisko,
      'placa' : placa
    });
  }

}
