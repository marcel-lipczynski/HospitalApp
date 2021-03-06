import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Pielegniarka} from "../pielegniarka.model";
import {PielegniarkaService} from "../pielegniarka.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {Pacjent} from "../../pacjenci/pacjent.model";

@Component({
  selector: 'app-pielegniarki-lista',
  templateUrl: './pielegniarki-lista.component.html',
  styleUrls: ['./pielegniarki-lista.component.css']
})
export class PielegniarkiListaComponent implements OnInit {

  filteredPielegniarki: Pielegniarka[] = [];
  formAddPielegniarka: FormGroup;
  formEditPielegniarka: FormGroup;
  pielegniarki: Pielegniarka[] = [];
  isLoading: boolean = true;


  constructor(private pielegniarkaService: PielegniarkaService,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.reloadData();
    this.setupForm();
  }

  reloadData() {
    this.pielegniarkaService.findAllPielegniarki().subscribe(pielegniarki => {
      this.isLoading = true;
      this.filteredPielegniarki = pielegniarki;
      this.pielegniarki = pielegniarki;
      this.isLoading = false;
    })
  }

  deletePielegniarkaById(id_pielegniarki: number) {
    this.pielegniarkaService.deletePielegniarkaById(id_pielegniarki).subscribe(() => {
      this.reloadData();
    });
  }

  setupForm() {
    this.formAddPielegniarka = new FormGroup({
      imie: new FormControl(null, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      nazwisko: new FormControl(null, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      placa: new FormControl(null, [Validators.required,Validators.max(9999), Validators.min(1), Validators.pattern(/^\d+$/)])
    });

    this.formEditPielegniarka = new FormGroup({
      id_pielegniarki: new FormControl(null),
      imie: new FormControl(null, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      nazwisko: new FormControl(null, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z ]+$/)]),
      placa: new FormControl(null, [Validators.required,Validators.max(9999), Validators.min(1), Validators.pattern(/^\d+$/)])

    });


  }

  resetForm() {
    this.formEditPielegniarka.reset();
    this.formAddPielegniarka.reset();
  }

  onSubmit(form: FormGroup) {
    this.saveOrUpdatePielegniarka(form.getRawValue());
    this.resetForm();
  }

  saveOrUpdatePielegniarka(pielegniarka: Pielegniarka) {
    this.pielegniarkaService.saveOrUpdatePielegniarka(pielegniarka).subscribe(() =>
      this.reloadData());
    $("#exampleModalCenter").modal("hide");
    $("#exampleModalCenter2").modal("hide");
  }

  onConfirmDelete(id: number) {
    if (confirm("Are you sure you want to delete?")) {
      this.deletePielegniarkaById(id);
    }
  }

  onEditPielegniarka(id: number, imie: string, nazwisko: string, placa: number) {
    this.formEditPielegniarka.patchValue({
      'id_pielegniarki': id,
      'imie': imie,
      'nazwisko': nazwisko,
      'placa': placa
    });
  }

  filterPielegniarki(filter: string): Pielegniarka[]{
    this.filteredPielegniarki = [];
    if(filter === ''){
      this.filteredPielegniarki = this.pielegniarki;
      return this.filteredPielegniarki;
    }
    for(let pielegniarka of this.pielegniarki){
      if(pielegniarka.imie === filter || pielegniarka.nazwisko === filter){
        this.filteredPielegniarki.push(pielegniarka);
      }
    }
    return this.filteredPielegniarki;
  }


}
