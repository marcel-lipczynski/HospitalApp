import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PacjentService} from "../pacjent.service";
import {Pacjent} from "../pacjent.model";
import {Observable} from "rxjs";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

@Component({
  selector: 'app-pacjenci-lista',
  templateUrl: './pacjenci-lista.component.html',
  styleUrls: ['./pacjenci-lista.component.css']
})
export class PacjenciListaComponent implements OnInit {

  filteredPacjenci: Pacjent[] = [];
  pacjenci: Pacjent[] = [];
  isLoading: boolean = true;
  formPacjent: FormGroup;
  formPacjentEdit: FormGroup;
  pesels: string[] = [];



  constructor(private http: HttpClient,
              private pacjentService: PacjentService) { }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }

  reloadData(){
    //subscribe działa tak że przy "next" zmianie danych zwroc je do pacjenci
    //i jak zawrocisz to przypisz do moich pacjentow! :D
    this.pacjentService.findAllPacjenci().subscribe(pacjenci => {
      this.isLoading = true;
      this.pacjenci = pacjenci;
      this.filteredPacjenci = pacjenci;
      this.pesels = [];
      pacjenci.forEach(element =>
      {
        this.pesels.push(element.pesel);
      });
      this.isLoading = false;
    });
  }

  //saving or updating pacjent
  saveOrUpdatePacjent(pacjent: Pacjent){
    this.pacjentService.saveOrUpdatePacjent(pacjent).subscribe(() =>
    this.reloadData());
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");

  }

  //submitting form
  onSubmit(form: FormGroup){
    this.saveOrUpdatePacjent(form.getRawValue());
    this.resetForm();
  }

  //Deleting pacjent by pesel
  deletePacjentByPesel(pesel: string){
    this.pacjentService.deletePacjentByPesel(pesel).subscribe(() =>
    this.reloadData());
  }

  //confirm delete Pacjent
  onConfirmDelete(pesel: string){
    if(confirm("Are you sure you want to delete?")){
      this.deletePacjentByPesel(pesel);
    }
  }

  resetForm(){
    this.formPacjent.reset();
  }

  setupForm(){
    this.formPacjent = new FormGroup({
      imie: new FormControl(null,[Validators.required,Validators.maxLength(20)]),
      nazwisko: new FormControl(null,[Validators.required,Validators.maxLength(20)]),
      pesel: new FormControl(null,
        [Validators.required,Validators.pattern(/^\d+$/),
          Validators.minLength(11),Validators.maxLength(11), this.checkIfPeselExists.bind(this)])
    });

    this.formPacjentEdit = new FormGroup({
      imie: new FormControl(null,[Validators.required,Validators.maxLength(20)]),
      nazwisko: new FormControl(null,[Validators.required,Validators.maxLength(20)]),
      pesel: new FormControl({value: null, disabled: true})
    });
  }

  onEditPacjent(pesel: string, imie:string, nazwisko: string){
    // this.pacjentPesel.emit(pesel);
    this.formPacjentEdit.patchValue({
      'pesel': pesel,
      'imie': imie,
      'nazwisko' : nazwisko
    });
  }

  checkIfPeselExists(control: AbstractControl): {[peselExists: string] : boolean}{
    for(let i=0; i < this.pesels.length; i++){
      if(control.value !=null && this.pesels[i] === control.value){
        return {'peselExists': true};
      }
    }
    return null;
  }


  get pesel(){
    return this.formPacjent.get('pesel');
  }

  filterPacjenci(filter: string): Pacjent[]{
    this.filteredPacjenci = [];
    if(filter === ''){
      this.filteredPacjenci = this.pacjenci;
    }
    for(let pacjent of this.pacjenci){
      if(pacjent.imie === filter || pacjent.nazwisko === filter || pacjent.pesel === filter){
        this.filteredPacjenci.push(pacjent);
      }
    }
    return this.filteredPacjenci;
  }

}
