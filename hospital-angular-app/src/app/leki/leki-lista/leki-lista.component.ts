import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormControl, FormGroup} from "@angular/forms";
import {LekService} from "../lek.service";
import {Lek} from "../lek.model";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-leki-lista',
  templateUrl: './leki-lista.component.html',
  styleUrls: ['./leki-lista.component.css']
})
export class LekiListaComponent implements OnInit {

  leki: Lek[] = [];
  isLoading: boolean = true;
  formAddLek: FormGroup;
  formEditLek: FormGroup;


  constructor(private http: HttpClient,
              private lekService: LekService) {
  }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }

  reloadData() {
    this.lekService.findAllLeki().subscribe(leki => {
      this.leki = leki;
      this.isLoading = false;
    })
  }

  setupForm() {
    this.formAddLek = new FormGroup({
      'nazwa_leku': new FormControl(null),
      'rodzaj_leku': new FormControl(null)
    });

    this.formEditLek = new FormGroup({
      'nazwa_leku': new FormControl({value: null, disable: true}),
      'rodzaj_leku': new FormControl(null)
    });
  }

  resetForm(){
    this.formAddLek.reset();
  }

  saveOrUpdateLek(lek: Lek){
    this.lekService.saveOrUpdateLek(lek).subscribe(() =>{
      this.reloadData();
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  onSubmit(form: FormGroup){
    this.saveOrUpdateLek(form.getRawValue());
    this.resetForm();
  }

  onEditLek(nazwa_leku: string, rodzaj_leku: string){
    this.formEditLek.patchValue({
      'nazwa_leku' : nazwa_leku,
      'rodzaj_leku' : rodzaj_leku
    });
  }

  deleteLekByNazwa(nazwa_leku : string){
    this.lekService.deleteLekByNazwa(nazwa_leku).subscribe(()=>{
      this.reloadData();
    })
  }

  onConfirmDelete(nazwa_leku: string){
    if(confirm("Are you sure you want to delete?")){
      this.deleteLekByNazwa(nazwa_leku);
    }
  }




}
