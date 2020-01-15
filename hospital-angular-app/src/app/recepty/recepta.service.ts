import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Recepta} from "./recepta.model";
import {Lek} from "../leki/lek.model";
import {Lekarz} from "../lekarze/lekarz.model";


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
    return this.http.delete(this.RECEPTY_API_URL + `/${pesel}` + '/karty' + `/${id_karty}` + '/recepty' + `/${id_recepty}`);
  }


  //adding and deleting lek from recepty

  findAllLekiOnRecepta(id_recepty: number): Observable<Lek[]> {
    return this.http.get<Lek[]>('/api/recepty' + `/${id_recepty}` + '/leki');
  }

  addLekToRecepta(id_recepty: number, nazwa_leku: string){
    console.log(nazwa_leku);
    return this.http.post('/api/recepty' + `/${id_recepty}` + '/leki' + `/${nazwa_leku}`, null);
  }

  deleteLekFromRecepta(id_recepty: number, nazwa_leku: string){
    return this.http.delete('/api/recepty' + `/${id_recepty}` + '/leki' + `/${nazwa_leku}`);
  }

  findLekarzWhoCanAddRecepta(id_karty:number): Observable<Lekarz[]>{
    return this.http.get<Lekarz[]>('/api/recepty' + `/${id_karty}` + '/lekarze');
  }

}
