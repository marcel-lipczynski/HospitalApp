import {Injectable} from "@angular/core";
import {Lek} from "./lek.model";
import {FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LekService {

  readonly LEKI_API_URL = '/api/leki';

  constructor(private http: HttpClient) {
  }

  findAllLeki(): Observable<Lek[]> {
    return this.http.get<Lek[]>(this.LEKI_API_URL);
  }

  findLekById(nazwa_leku: string): Observable<Lek> {
    return this.http.get<Lek>(this.LEKI_API_URL + `/${nazwa_leku}`)
  }

  saveOrUpdateLek(lek: Lek){
    return this.http.post<Lek>(this.LEKI_API_URL, lek);
  }

  deleteLekByNazwa(nazwa_leku: string){
    return this.http.delete(this.LEKI_API_URL + `/${nazwa_leku}`);1
  }


}
