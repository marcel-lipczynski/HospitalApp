import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Karta} from "./karta.model";
import {Lekarz} from "../lekarze/lekarz.model";

@Injectable({
  providedIn: 'root'
})
export class KartaService {

  readonly KARTY_API_URL = '/api/pacjenci';

  constructor(private http: HttpClient) {
  }

  findAllKartyOfPacjent(pesel: string): Observable<Karta[]> {
    return this.http.get<Karta[]>(this.KARTY_API_URL + `/${pesel}` + '/karty');
  }

  saveOrUpdateKartaForPacjent(karta: Karta, pesel: string) {
    return this.http.post<Karta>(this.KARTY_API_URL + `/${pesel}` + '/karty', karta);
  }

  deleteKartaFromPacjent(id_karty : number, pesel: string){
    return this.http.delete(this.KARTY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}`)
  }

  findAllLekarzeOnKarta(id_karty: number): Observable<Lekarz[]>{
    return this.http.get<Lekarz[]>('/api/karty' + `/${id_karty}` + '/lekarze');
  }


}
