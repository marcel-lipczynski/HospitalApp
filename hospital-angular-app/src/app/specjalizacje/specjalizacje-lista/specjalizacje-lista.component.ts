import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SpecjalizacjaService} from "../specjalizacja.service";
import {Specjalizacja} from "../specjalizacja.model";
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";



// const placa_min_placa_max: ValidatorFn = (fg: FormGroup) => {
//   const placa_min = fg.get('placa_min').value;
//   const placa_max = fg.get('placa_max').value;
//
//   return placa_min && placa_max && placa_min < placa_max ? null : { moreThanError: true };
// }




@Component({
  selector: 'app-specjalizacje-lista',
  templateUrl: './specjalizacje-lista.component.html',
  styleUrls: ['./specjalizacje-lista.component.css']
})
export class SpecjalizacjeListaComponent implements OnInit {

  specjalizacje: Specjalizacja[] = [];
  nazwy_specjalizacji: string[] = [];
  isLoading: boolean = true;
  formAddSpecjalizacja: FormGroup;
  formEditSpecjalizacja: FormGroup;


  constructor(private http: HttpClient,
              private specjalizacjaService: SpecjalizacjaService) {

  }

  ngOnInit() {

    this.reloadData();
    this.setupForm();




  }


  reloadData(){
    this.specjalizacjaService.findAllSpecjalizacje().subscribe(specjalizacje => {
      this.specjalizacje = specjalizacje;
      specjalizacje.forEach(specjalizacja => {
        this.nazwy_specjalizacji.push(specjalizacja.nazwa_specjalizacji);
      });
      this.isLoading = false;
    });
  }

  setupForm(){
    this.formAddSpecjalizacja = new FormGroup({
      nazwa_specjalizacji: new FormControl(null, [Validators.maxLength(50),Validators.required, Validators.pattern(/^[A-Za-z ]+$/),this.checkIfSpecjalizacjaExists.bind(this)]),
      placa_min: new FormControl(null),
      placa_max: new FormControl(null)
    });

    this.formAddSpecjalizacja.get('placa_min').setValidators([this.greaterThan('placa_max'), Validators.max(99999), Validators.min(0),Validators.required,Validators.pattern(/^\d+$/)] );
    this.formAddSpecjalizacja.get('placa_max').setValidators([this.lessThan('placa_min'), Validators.max(99999), Validators.min(0),Validators.required,Validators.pattern(/^\d+$/)] );


    this.formEditSpecjalizacja = new FormGroup({
      nazwa_specjalizacji: new FormControl({value: null, disabled: true}),
      placa_min: new FormControl(null),
      placa_max: new FormControl(null)
    });

    this.formEditSpecjalizacja.get('placa_min').setValidators([this.greaterThan('placa_max'), Validators.max(99999), Validators.min(0),Validators.required,Validators.pattern(/^\d+$/)] );
    this.formEditSpecjalizacja.get('placa_max').setValidators([this.lessThan('placa_min'), Validators.max(99999), Validators.min(0),Validators.required,Validators.pattern(/^\d+$/)] );

  }



  greaterThan(field: string): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} => {
      const group = control.parent;
      const fieldToCompare = group.get(field);
      if(fieldToCompare === null){
        return null;
      }
      const isLessThan = Number(fieldToCompare.value) < Number(control.value);
      return isLessThan ? {'lessThan': {value: control.value}} : null;
    }
  }

  lessThan(field: string): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} => {
      const group = control.parent;
      const fieldToCompare = group.get(field);
      if(fieldToCompare === null){
        return null;
      }
      const isLessThan = Number(fieldToCompare.value) > Number(control.value);
      return isLessThan ? {'greaterThan': {value: control.value}} : null;
    }
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

  checkIfSpecjalizacjaExists(control: AbstractControl): {[specjalizacjaExists: string] : boolean}{
    for(let i=0; i < this.nazwy_specjalizacji.length; i++){

      if(control.value != null && this.nazwy_specjalizacji[i] === (control.value).toUpperCase()){
        return {'specjalizacjaExists': true};
      }
    }
    return null;
  }




}
