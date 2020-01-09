import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PacjentService} from "../pacjent.service";
import {Pacjent} from "../pacjent.model";
import {Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-pacjenci-lista',
  templateUrl: './pacjenci-lista.component.html',
  styleUrls: ['./pacjenci-lista.component.css']
})
export class PacjenciListaComponent implements OnInit {

  // @Output() pacjentPesel = new EventEmitter<string>();
  pacjenci: Pacjent[];
  isLoading: boolean = false;
  formPacjent: FormGroup;
  formPacjentEdit: FormGroup;



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
      this.isLoading = false;
    })
  }

  //saving or updating pacjent
  saveOrUpdatePacjent(pacjent: Pacjent){
    this.pacjentService.saveOrUpdatePacjent(pacjent).subscribe(() =>
    this.reloadData());
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
    this.formPacjentEdit.reset();
  }

  setupForm(){
    this.formPacjent = new FormGroup({
      imie: new FormControl(null,Validators.required),
      nazwisko: new FormControl(null,Validators.required),
      pesel: new FormControl(null, Validators.required)
    });

    this.formPacjentEdit = new FormGroup({
      imie: new FormControl(null,Validators.required),
      nazwisko: new FormControl(null,Validators.required),
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

  onSetPeselInForm(event){
    console.log(event.target.value);
    this.formPacjentEdit.patchValue({
      'pesel': event.target.value
    });
  }
  ////(change)="onSetPeselInForm($event)"

}
