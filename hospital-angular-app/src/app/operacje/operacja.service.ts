import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Diagnoza} from "../diagnozy/diagnoza.model";
import {Operacja} from "./operacja.model";


@Injectable({
  providedIn: 'root'
})
export class OperacjaService {

  readonly OPERACJE_API_URL = '/api/pacjenci';

  constructor(private http: HttpClient) {
  }


  findAllOperacjeForKartaPobytu(pesel: string, id_karty: number): Observable<Operacja[]> {
    return this.http.get<Operacja[]>(this.OPERACJE_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/operacje');
  }

  saveOrUpdatOperacjaInKartaPobytu(operacja: Operacja, pesel: string, id_karty: number) {
    return this.http.post<Operacja>(this.OPERACJE_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/operacje', operacja);
  }

  deletOperacjaByIdFromKartaPobytu(id_operacji: number, pesel: string, id_karty: number) {
    return this.http.delete(this.OPERACJE_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/operacje' + `/${id_operacji}`);
  }

}
