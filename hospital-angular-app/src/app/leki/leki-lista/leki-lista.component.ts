import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
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
  nazwyLekow: string[] = [];


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
      this.nazwyLekow = [];
      leki.forEach(element =>{
        this.nazwyLekow.push(element.nazwa_leku);
      });
      this.isLoading = false;
    });
  }

  setupForm() {
    this.formAddLek = new FormGroup({
      'nazwa_leku': new FormControl(null, [Validators.maxLength(20),
        Validators.required, this.checkIfNazwaLekuExists.bind(this)]),
      'rodzaj_leku': new FormControl(null,
        [Validators.maxLength(50), Validators.required])
    });

    this.formEditLek = new FormGroup({
      'nazwa_leku': new FormControl({value: null, disabled: true}),
      'rodzaj_leku': new FormControl(null,[Validators.maxLength(50), Validators.required])
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


  //custom validator for nazwy leków

  checkIfNazwaLekuExists(control: AbstractControl): {[nazwaLekuExists: string] : boolean}{
    for(let i=0; i < this.nazwyLekow.length; i++){

      if(control.value != null && this.nazwyLekow[i] === (control.value).toUpperCase()){
        return {'nazwaLekuExists': true};
      }
    }
    return null;
  }

  get nazwa_leku(){
    return this.formAddLek.get('nazwa_leku');
  }




}
