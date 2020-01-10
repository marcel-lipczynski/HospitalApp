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

  findLekById(id_leku: number): Observable<Lek> {
    return this.http.get<Lek>(this.LEKI_API_URL + `/${id_leku}`)
  }

  saveOrUpdateLek(lek: Lek){
    return this.http.post<Lek>(this.LEKI_API_URL, lek);
  }

  deleteLekById(id_leku: number){
    return this.http.delete(this.LEKI_API_URL + `/${id_leku}`);1
  }


}
