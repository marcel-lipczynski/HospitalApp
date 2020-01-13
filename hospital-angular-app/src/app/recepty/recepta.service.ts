import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Recepta} from "./recepta.model";


@Injectable({
  providedIn: 'root'
})
export class ReceptaService{

  readonly RECEPTY_API_URL = '/api/pacjenci';

  constructor(private http: HttpClient){}

  findAllReceptyForKartaPobytu(pesel: string, id_karty: number): Observable<Recepta[]> {
    return this.http.get<Recepta[]>(this.RECEPTY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/recepty');
  }

  saveOrUpdateReceptaInKartaPobytu(recepta: Recepta, pesel: string, id_karty: number) {
    return this.http.post<Recepta>(this.RECEPTY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/recepty', recepta);
  }

  deleteReceptaByIdFromKartaPobytu(id_recepty: number, pesel: string, id_karty: number) {
    return this.http.delete(this.RECEPTY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/diagnozy' + `/${id_recepty}`);
  }


  //adding and deleting lek from recepta

  addLekToRecepta(id_recepty: number, nazwa_leku: string){
    return this.http.post('/recepty' + `/${id_recepty}` + '/leki' + `/${nazwa_leku}`, null);
  }

  deleteLekFromRecepta(id_recepty: number, nazwa_leku: string){
    return this.http.delete('/recepty' + `/${id_recepty}` + '/leki' + `/${nazwa_leku}`, null);
  }

}
