import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Lekarz} from "./lekarz.model";
import {Specjalizacja} from "../specjalizacje/specjalizacja.model";


@Injectable({
  providedIn: 'root'
})
export class LekarzService{

  readonly LEKARZE_API_URL = '/api/lekarze'

  constructor(private http: HttpClient){}

  findAllLekarze(): Observable<Lekarz[]>{
    return this.http.get<Lekarz[]>(this.LEKARZE_API_URL);
  }

  findLekarzById(id_lekarza : number): Observable<Lekarz>{
    return this.http.get<Lekarz>(this.LEKARZE_API_URL + `/${id_lekarza}`)
  }

  saveOrUpdateLekarz(lekarz: Lekarz){
    return this.http.post<Lekarz>(this.LEKARZE_API_URL, lekarz);
  }

  deleteLekarzById(id_lekarza: number){
    return this.http.delete(this.LEKARZE_API_URL + `/${id_lekarza}`)
  }

  //specjalizacje

  findAllSpecjalizacjeOfLekarz(id_lekarza:number) : Observable<Specjalizacja[]>{
    return this.http.get<Specjalizacja[]>('/api/lekarze' + `/${id_lekarza}` + '/specjalizacje');
  }

  findAvailableSpecjalizacjeForLekarz(id_lekarza:number) : Observable<Specjalizacja[]>{
    return this.http.get<Specjalizacja[]>('/api/lekarze' + `/${id_lekarza}` + '/specjalizacje/available');
  }

  addSpecjalizacjaToLekarz(id_lekarza: number, nazwa_specjalizacji: string){
    return this.http.post('/api/lekarze' + `/${id_lekarza}` + '/specjalizacje' + `/${nazwa_specjalizacji}`, null);
  }

  deleteSpecjalizacjaFromLekarz(id_lekarza: number, nazwa_specjalizacji: string){
    return this.http.delete('/api/lekarze' + `/${id_lekarza}` + '/specjalizacje' + `/${nazwa_specjalizacji}`);
  }


}
