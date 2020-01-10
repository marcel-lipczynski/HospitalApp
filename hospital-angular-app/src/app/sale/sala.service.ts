import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sala} from "./sala.model";

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



}
