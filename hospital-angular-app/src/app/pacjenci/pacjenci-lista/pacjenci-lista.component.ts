import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PacjentService} from "../pacjent.service";
import {Pacjent} from "../pacjent.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-pacjenci-lista',
  templateUrl: './pacjenci-lista.component.html',
  styleUrls: ['./pacjenci-lista.component.css']
})
export class PacjenciListaComponent implements OnInit {

  pacjenci: Pacjent[];
  isLoading: boolean = false;

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

}
