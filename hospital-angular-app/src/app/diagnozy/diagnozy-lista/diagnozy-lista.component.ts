import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DiagnozaService} from "../diagnoza.service";
import {Diagnoza} from "../diagnoza.model";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {KartaService} from "../../karty/karta.service";
import {Lekarz} from "../../lekarze/lekarz.model";

@Component({
  selector: 'app-diagnozy-lista',
  templateUrl: './diagnozy-lista.component.html',
  styleUrls: ['./diagnozy-lista.component.css']
})
export class DiagnozyListaComponent implements OnInit {

  diagnozy: Diagnoza[] = [];
  lekarze: Lekarz[] = [];
  isLoading: boolean = true;
  formAddDiagnoza: FormGroup;
  formEditDiagnoza: FormGroup;
  pesel: string;
  id_karty: number;
  lekarzDeleted: boolean;


  constructor(private http: HttpClient,
              private diagnozaService: DiagnozaService,
              private kartaService: KartaService,
              private route: ActivatedRoute) {

  }

  ngOnInit() {

    this.id_karty = +this.route.snapshot.params['id_karty'];
    this.pesel = this.route.snapshot.params['pesel'];
    this.reloadData();
    this.setupForm();
  }

  reloadData() {
    this.diagnozaService.findAllDiagnozyForKartaPobytu(this.pesel, this.id_karty).subscribe(diagnozy => {
      this.findAllLekarzeOnKarta();
      this.diagnozy = diagnozy;
      this.isLoading = false;
    });
  }

  findAllLekarzeOnKarta() {
    return this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarze => {
      this.lekarze = lekarze;
    })
  }

  setupForm() {
    this.formAddDiagnoza = new FormGroup({
      opis: new FormControl(null),
      data_wystawienia: new FormControl(null),
      id_lekarza: new FormControl(null),
      id_karty: new FormControl(this.id_karty)
    });

    this.formEditDiagnoza = new FormGroup({
      id_diagnozy: new FormControl(null),
      opis: new FormControl(null),
      data_wystawienia: new FormControl(null),
      id_lekarza: new FormControl(null),
      id_karty: new FormControl(this.id_karty)
    });
  }

  onEditForm(id_diagnozy: number, opis: string, data_wystawienia: string, id_lekarza: number, id_karty: number) {
    this.formEditDiagnoza.patchValue({
      'id_diagnozy': id_diagnozy,
      'opis': opis,
      'data_wystawienia': data_wystawienia,
      'id_lekarza': id_lekarza,
    });
  }

  onSubmit(form: FormGroup) {
    this.saveOrUpdateDiagnozaInKartaPobytu(form.getRawValue());
    this.resetForm();
  }

  resetForm() {
    this.formAddDiagnoza.reset();
  }

  saveOrUpdateDiagnozaInKartaPobytu(diagnoza: Diagnoza) {
    this.diagnozaService.saveOrUpdateDiagnozaInKartaPobytu(diagnoza, this.pesel, this.id_karty).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  deleteDiagnozaByIdFromKartaPobytu(id_diagnozy: number) {
    this.diagnozaService.deleteDiagnozaByIdFromKartaPobytu(id_diagnozy, this.pesel, this.id_karty).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(id_diagnozy: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteDiagnozaByIdFromKartaPobytu(id_diagnozy);
    }
  }

}
