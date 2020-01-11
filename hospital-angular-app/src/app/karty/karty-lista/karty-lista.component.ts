import { Component, OnInit } from '@angular/core';
import {Karta} from "../karta.model";
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {KartaService} from "../karta.service";
import {ActivatedRoute} from "@angular/router";
import {Pacjent} from "../../pacjenci/pacjent.model";
import {PacjentService} from "../../pacjenci/pacjent.service";

@Component({
  selector: 'app-karty-lista',
  templateUrl: './karty-lista.component.html',
  styleUrls: ['./karty-lista.component.css']
})
export class KartyListaComponent implements OnInit {

  karty: Karta[] = [];
  pacjent: Pacjent;
  isLoading: boolean = true;
  formAddKarta: FormGroup;
  formEditKarta: FormGroup;
  pesel: string;


  constructor(private http: HttpClient,
              private kartaService: KartaService,
              private pacjentService: PacjentService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.pesel = this.route.snapshot.params['pesel'];
    this.reloadData();
    this.setupForm();

  }

  loadPacjent(){
    this.pacjentService.findPacjentByPesel(this.pesel).subscribe(pacjent =>{
      this.pacjent = pacjent;
    });
  }

  reloadData(){
    this.kartaService.findAllKartyOfPacjent(this.pesel).subscribe(karty =>{
      this.karty = karty;
      this.loadPacjent();
      this.isLoading = false;
    });
  }

  setupForm(){
    this.formAddKarta = new FormGroup({
      id_karty : new FormControl(null),
      data_przyjecia: new FormControl(null),
      godzina_przyjecia : new FormControl(null),
      data_wypisu : new FormControl(null),
      nr_sali : new FormControl(null),
      pesel: new FormControl(null)
    });

    this.formEditKarta = new FormGroup({
      id_karty : new FormControl(null),
      data_przyjecia: new FormControl(null),
      godzina_przyjecia : new FormControl(null),
      data_wypisu : new FormControl(null),
      nr_sali : new FormControl(null),
      pesel: new FormControl(null)
    });

  }

}
