import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sala} from "./sala.model";
import {Lek} from "../leki/lek.model";
import {Pielegniarka} from "../pielegniarki/pielegniarka.model";

@Injectable({
  providedIn: 'root'
})
export class SalaService{

  constructor(private http: HttpClient){}

  readonly SALE_API_URL = '/api/sale';

  findAllSale(): Observable<Sala[]>{
    return this.http.get<Sala[]>(this.SALE_API_URL);
  }

  findSalaByNRSali(nr_sali: number): Observable<Sala>{
    return this.http.get<Sala>(this.SALE_API_URL + `/${nr_sali}`);
  }

  saveOrUpdateSala(sala: Sala){
    return this.http.post<Sala>(this.SALE_API_URL, sala);
  }

  deleteSalaByNrSali(nr_sali : number){
    return this.http.delete(this.SALE_API_URL + `/${nr_sali}`);
  }

  //pielegniarki

  findAllPielegniarkiInSala(nr_sali: number): Observable<Pielegniarka[]> {
    return this.http.get<Pielegniarka[]>('/api/sale' + `/${nr_sali}` + '/pielegniarki');
  }

  addPielegniarkaToSala(nr_sali: number, id_pielegniarki: number){
    return this.http.post('/api/sale' + `/${nr_sali}` + '/pielegniarki' + `/${id_pielegniarki}`, null);
  }

  deletePielegniarkaFromSala(nr_sali: number, id_pielegniarki: number){
    return this.http.delete('/api/sale' + `/${nr_sali}` + '/pielegniarki' + `/${id_pielegniarki}`);
  }



}
