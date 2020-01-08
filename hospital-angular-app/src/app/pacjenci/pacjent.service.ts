import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pacjent} from "./pacjent.model";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class PacjentService{

  readonly PACJENCI_API_URL = '/api/pacjenci';

  constructor(private http: HttpClient){}

  findAllPacjenci(): Observable<Pacjent[]>{
    return this.http.get<Pacjent[]>(this.PACJENCI_API_URL);
  }

  saveOrUpdatePacjent(pacjent: Pacjent): Observable<Pacjent>{
    return this.http.post<Pacjent>(this.PACJENCI_API_URL, pacjent);
  }

  deletePacjentByPesel(pesel: string): Observable<Pacjent>{
    return this.http.delete<Pacjent>(this.PACJENCI_API_URL + `/${pesel}`)
  }



}
