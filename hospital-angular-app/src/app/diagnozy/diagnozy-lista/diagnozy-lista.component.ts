import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DiagnozaService} from "../diagnoza.service";
import {Diagnoza} from "../diagnoza.model";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {KartaService} from "../../karty/karta.service";
import {Lekarz} from "../../lekarze/lekarz.model";
import {Karta} from "../../karty/karta.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-diagnozy-lista',
  templateUrl: './diagnozy-lista.component.html',
  styleUrls: ['./diagnozy-lista.component.css']
})
export class DiagnozyListaComponent implements OnInit {

  currentKarta: Karta;
  diagnozy: Diagnoza[] = [];
  lekarze: Lekarz[] = [];
  lekarzeAvailable: Lekarz[] = [];
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


    this.pesel = this.route.snapshot.params['pesel'];
    this.id_karty = +this.route.snapshot.params['id_karty'];

    this.reloadData();
    this.setupForm();
  }

  reloadData() {

    this.diagnozaService.findAllDiagnozyForKartaPobytu(this.pesel, this.id_karty).subscribe(diagnozy => {

      this.findAllLekarzeOnKarta();
      this.findLekarzWhoCanAddDiagnoza();
      this.diagnozy = diagnozy;
      this.isLoading = false;
    });
  }

  findKartaPobytu() {
    this.kartaService.findKartaById(this.id_karty).subscribe(karta => {
      this.isLoading = true;
      this.currentKarta = karta;
      this.isLoading = false;
    });
  }

  findLekarzWhoCanAddDiagnoza() {
    return this.diagnozaService.findLekarzWhoCanAddDiagnoza(this.id_karty).subscribe(lekarzeAvailable => {
      this.isLoading = true;
      this.lekarzeAvailable = lekarzeAvailable;
      this.isLoading = false;
    });
  }

  findAllLekarzeOnKarta() {
    return this.kartaService.findAllLekarzeOnKarta(this.id_karty).subscribe(lekarze => {
      this.isLoading = true;
      this.lekarze = lekarze;
      this.isLoading = false;
    })
  }

  setupForm() {
    this.formAddDiagnoza = new FormGroup({
      opis: new FormControl(null, [Validators.required, Validators.maxLength(2000)]),
      data_wystawienia: new FormControl(null, [Validators.required], [this.checkIfDataWystawieniaNotLessThanDataPrzyjecia.bind(this)]),
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
    this.reloadData();
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


  // checkIfDataWystawieniaNotLessThanDataPrzyjecia(control: AbstractControl): { [dateNotLess: string]: boolean } {
  //   const dataWystawieniaDiagnozy = new Date(control.value);
  //   const data_przyjecia = new Date(this.currentKarta.data_przyjecia);
  //   if (data_przyjecia.getUTCFullYear() > dataWystawieniaDiagnozy.getUTCFullYear()) {
  //     return {'dateNotLess': true};
  //   }
  //   if (data_przyjecia.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
  //     && data_przyjecia.getUTCMonth() > dataWystawieniaDiagnozy.getUTCMonth()) {
  //     return {'dateNotLess': true};
  //   }
  //   if (data_przyjecia.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
  //     && data_przyjecia.getUTCMonth() === dataWystawieniaDiagnozy.getUTCMonth()
  //     && data_przyjecia.getDate() > dataWystawieniaDiagnozy.getDate()) {
  //     return {'dateNotLess': true};
  //
  //   }
  //   return null;
  // }

  checkIfDataWystawieniaNotLessThanDataPrzyjecia(control: AbstractControl): Promise<any> | Observable<any>{
    return new Promise<any>((resolve) => {
      this.kartaService.findKartaById(this.id_karty).subscribe(karta => {
        const dataWystawieniaDiagnozy = new Date(control.value);
        const data_przyjecia = new Date(karta.data_przyjecia);
        if (data_przyjecia.getUTCFullYear() > dataWystawieniaDiagnozy.getUTCFullYear()) {
          resolve({'dateNotLess': true});
        } else if (data_przyjecia.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
          && data_przyjecia.getUTCMonth() > dataWystawieniaDiagnozy.getUTCMonth()) {
          resolve({'dateNotLess': true});
        } else if (data_przyjecia.getUTCFullYear() === dataWystawieniaDiagnozy.getUTCFullYear()
          && data_przyjecia.getUTCMonth() === dataWystawieniaDiagnozy.getUTCMonth()
          && data_przyjecia.getDate() > dataWystawieniaDiagnozy.getDate()) {
          resolve({'dateNotLess': true});
        } else {
          resolve(null);
        }

      });
    });

  }

}
