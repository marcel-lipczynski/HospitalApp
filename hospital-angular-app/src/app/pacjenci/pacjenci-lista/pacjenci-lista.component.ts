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


  //tworzymy controle dla pól w pacjencie!
  peselControl = new FormControl('');
  imieControl = new FormControl('');
  nazwiskoControl = new FormControl('');


  //tworzymy form grupe dla controli!
  formPacjent = new FormGroup({
    imie: this.imieControl,
    nazwisko: this.nazwiskoControl,
    pesel: this.peselControl
  });

  constructor(private http: HttpClient,
              private pacjentService: PacjentService) { }

  ngOnInit() {
    this.reloadData();
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

  saveOrUpdatePacjent(pacjent: Pacjent){
    this.pacjentService.saveOrUpdatePacjent(pacjent).subscribe();
    this.reloadData();
  }

  onSubmit(){
    this.saveOrUpdatePacjent(this.formPacjent.value);
  }

  deletePacjentByPesel(pesel: string){
    this.pacjentService.deletePacjentByPesel(pesel).subscribe();
    this.reloadData();
  }

}
