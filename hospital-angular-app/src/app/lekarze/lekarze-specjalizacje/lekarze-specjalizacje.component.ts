import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SpecjalizacjaService} from "../../specjalizacje/specjalizacja.service";
import {ActivatedRoute} from "@angular/router";
import {Specjalizacja} from "../../specjalizacje/specjalizacja.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Lek} from "../../leki/lek.model";
import {LekarzService} from "../lekarz.service";
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";
import {Lekarz} from "../lekarz.model";

@Component({
  selector: 'app-lekarze-specjalizacje',
  templateUrl: './lekarze-specjalizacje.component.html',
  styleUrls: ['./lekarze-specjalizacje.component.css']
})
export class LekarzeSpecjalizacjeComponent implements OnInit {

  specjalizacje: Specjalizacja[] = [];
  filteredSpecjlizacje: Specjalizacja[] = [];
  specjalizacjeLekarza: Specjalizacja[] = [];
  formAddSpecjalizacja: FormGroup;
  isLoading: boolean = true;
  id_lekarza: number;

  constructor(private http: HttpClient,
              private specjalizacjeService: SpecjalizacjaService,
              private lekarzService: LekarzService,
              private route: ActivatedRoute) { }

  ngOnInit() {

    this.id_lekarza = +this.route.snapshot.params['id_lekarza'];


    this.reloadData();
    this.setupForm();

  }

  reloadData() {
    this.lekarzService.findAllSpecjalizacjeOfLekarz(this.id_lekarza).subscribe(specjalizacjeLekarza => {
      this.fetchAvailableSpecjalizacje();
      this.filteredSpecjlizacje = specjalizacjeLekarza;
      this.specjalizacjeLekarza = specjalizacjeLekarza;
      this.isLoading = false;
    });
  }

  fetchAvailableSpecjalizacje(){
    this.lekarzService.findAvailableSpecjalizacjeForLekarz(this.id_lekarza).subscribe(specjalizacje =>{
      this.specjalizacje = specjalizacje;
    })
  }



  setupForm() {
    this.formAddSpecjalizacja = new FormGroup({
      nazwa_specjalizacji: new FormControl(null,[Validators.required])
    });

  }

  onSubmit(form: FormGroup) {
    this.addSpecjalizacjaToLekarz(form.getRawValue());
    this.reloadData();
    this.resetForm();
  }

  resetForm() {
    this.formAddSpecjalizacja.reset();
  }

  addSpecjalizacjaToLekarz(specjalizacja: Specjalizacja) {
    this.lekarzService.addSpecjalizacjaToLekarz(this.id_lekarza, specjalizacja.nazwa_specjalizacji).subscribe(() => {
      this.reloadData()
    });
    $("#exampleModalCenter").modal("hide");
  }

  deleteSpecjalizacjaFromLekarz(nazwa_specjalizacji: string) {
    this.lekarzService.deleteSpecjalizacjaFromLekarz(this.id_lekarza, nazwa_specjalizacji).subscribe(() => {
      this.reloadData();
    });
  }

  onConfirmDelete(nazwa_specjalzacji: string) {
    if (confirm("Are you sure you want to delete?")) {
      this.deleteSpecjalizacjaFromLekarz(nazwa_specjalzacji);
    }
  }

  filterSpecjalizacje(filter: string): Specjalizacja[]{
    this.filteredSpecjlizacje = [];
    if(filter === ''){
      this.filteredSpecjlizacje = this.specjalizacjeLekarza;
      return this.filteredSpecjlizacje;
    }
    for(let specjalizacja of this.specjalizacjeLekarza){
      if(specjalizacja.nazwa_specjalizacji === filter || specjalizacja.nazwa_specjalizacji === filter.toUpperCase()){
        this.filteredSpecjlizacje.push(specjalizacja);
      }
    }
    return this.filteredSpecjlizacje;
  }

}
