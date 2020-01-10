import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Pielegniarka} from "./pielegniarka.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PacjentService {

  readonly PIELEGNIARKI_API_URL = '/api/pielegniarki';

  constructor(private http: HttpClient){}

  findAllPielegniarki(): Observable<Pielegniarka[]> {
    return this.http.get<Pielegniarka[]>(this.PIELEGNIARKI_API_URL);
  }

  findPielegniarkaById(id_pielegniarki : number): Observable<Pielegniarka> {
    return this.http.get<Pielegniarka>(this.PIELEGNIARKI_API_URL + `/${id_pielegniarki}`);
  }

  saveOrUpdatePielegniarka(pielegniarka: Pielegniarka){
    return this.http.post<Pielegniarka>(this.PIELEGNIARKI_API_URL, pielegniarka);
  }

  deletePielegniarkaById(id_pielegniarki: number){
    return this.http.delete(this.PIELEGNIARKI_API_URL + `/${id_pielegniarki}`);
  }


}
