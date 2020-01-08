import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PacjentService} from "../pacjent.service";
import {Pacjent} from "../pacjent.model";
import {Observable} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-pacjenci-lista',
  templateUrl: './pacjenci-lista.component.html',
  styleUrls: ['./pacjenci-lista.component.css']
})
export class PacjenciListaComponent implements OnInit {

  pacjenci: Pacjent[];
  isLoading: boolean = false;
  formPacjent: FormGroup;


  //tworzymy controle dla pól w pacjencie!
  // peselControl = new FormControl('');
  // imieControl = new FormControl('');
  // nazwiskoControl = new FormControl('');


  constructor(private http: HttpClient,
              private pacjentService: PacjentService) { }

  ngOnInit() {
    this.reloadData();
    this.setupAddForm();
  }

  reloadData(){
    //subscribe działa tak że przy "next" zmianie danych zwroc je do pacjenci
    //i jak zawrocisz to przypisz do moich pacjentow! :D
    this.pacjentService.findAllPacjenci().subscribe(pacjenci => {
      this.isLoading = true;
      this.pacjenci = pacjenci;
      this.isLoading = false;
    })
  }

  //saving or updating pacjent
  saveOrUpdatePacjent(pacjent: Pacjent){
    this.pacjentService.saveOrUpdatePacjent(pacjent).subscribe(() =>
    this.reloadData());
  }

  //submitting form
  onSubmit(){
    this.saveOrUpdatePacjent(this.formPacjent.value);
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

  setupAddForm(){
    this.formPacjent = new FormGroup({
      imie: new FormControl(''),
      nazwisko: new FormControl(''),
      pesel: new FormControl()
    });
  }

}
