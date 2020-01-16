import {Component, OnInit} from '@angular/core';
import {Sala} from "../../sale/sala.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {SalaService} from "../../sale/sala.service";
import {PielegniarkaService} from "../../pielegniarki/pielegniarka.service";
import {ActivatedRoute} from "@angular/router";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {Lekarz} from "../../lekarze/lekarz.model";
import {LekarzService} from "../../lekarze/lekarz.service";
import {KartaService} from "../karta.service";

@Component({
  selector: 'app-karty-lekarze',
  templateUrl: './karty-lekarze.component.html',
  styleUrls: ['./karty-lekarze.component.css']
})
export class KartyLekarzeComponent implements OnInit {

  lekarzeFromKarta: Lekarz[] = [];
  lekarze: Lekarz[] = [];
  formAddLekarz: FormGroup;
  isLoading: boolean = true;
  id_karty: number;


  constructor(private http: HttpClient,
              private lekarzService: LekarzService,
              private kartaService: KartaService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.id_karty = +this.route.snapshot.params['id_karty'];


    this.reloadData();
    this.setupForm();

  }

  reloadData() {
    this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarzeFromKarta => {
      this.lekarzeFromKarta = lekarzeFromKarta;
      this.fetchAvailableLekarze();
      this.isLoading = false;
    });
  }

  fetchAvailableLekarze() {
    this.kartaService.findAvailableLekarze(this.id_karty).subscribe(lekarze => {
      this.lekarze = lekarze;
    })
  }


  setupForm() {
    this.formAddLekarz = new FormGroup({
      id_lekarza: new FormControl(null, [Validators.required])
    });

  }

  onSubmit(form: FormGroup) {
    this.addLekarzToKarta(form.getRawValue());
    this.resetForm();
    this.reloadData();
  }

  resetForm() {
    this.formAddLekarz.reset();
  }

  addLekarzToKarta(lekarz: Lekarz) {
    this.kartaService.addLekarzToKarta(this.id_karty, lekarz.id_lekarza).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
  }

  deleteLekarzFromKarta(id_lekarza: number) {
    this.kartaService.deleteLekarzFromKarta(this.id_karty, id_lekarza).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(id_lekarza: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteLekarzFromKarta(id_lekarza);
    }
  }

}
