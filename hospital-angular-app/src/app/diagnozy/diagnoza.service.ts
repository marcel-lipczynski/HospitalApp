import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Diagnoza} from "./diagnoza.model";
import {Lekarz} from "../lekarze/lekarz.model";

@Injectable({
  providedIn: "root"
})
export class DiagnozaService {

  readonly DIAGNOZY_API_URL = '/api/pacjenci';

  constructor(private http: HttpClient) {
  }

  findAllDiagnozyForKartaPobytu(pesel: string, id_karty: number): Observable<Diagnoza[]> {
    return this.http.get<Diagnoza[]>(this.DIAGNOZY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/diagnozy');
  }

  saveOrUpdateDiagnozaInKartaPobytu(diagnoza: Diagnoza, pesel: string, id_karty: number) {
    return this.http.post<Diagnoza>(this.DIAGNOZY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/diagnozy', diagnoza);
  }

  deleteDiagnozaByIdFromKartaPobytu(id_diagnozy: number, pesel: string, id_karty: number) {
    return this.http.delete(this.DIAGNOZY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/diagnozy' + `/${id_diagnozy}`);
  }

  findLekarzWhoCanAddDiagnoza(id_karty:number): Observable<Lekarz[]>{
    return this.http.get<Lekarz[]>('/api/diagnozy' + `/${id_karty}` + '/lekarze');
  }

}
