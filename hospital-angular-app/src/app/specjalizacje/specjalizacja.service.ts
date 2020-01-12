import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Specjalizacja} from "./specjalizacja.model";

@Injectable({
  providedIn: 'root'
})
export class SpecjalizacjaService {

  readonly SPECJALIZACJE_API_URL = '/api/specjalizacje'

  constructor(private http: HttpClient) {
  }

  findAllSpecjalizacje(): Observable<Specjalizacja[]> {
    return this.http.get<Specjalizacja[]>(this.SPECJALIZACJE_API_URL);
  }

  findSpecjalizacjaByNazwa(nazwa_specjalizacji: string): Observable<Specjalizacja> {
    return this.http.get<Specjalizacja>(this.SPECJALIZACJE_API_URL + `/${nazwa_specjalizacji}`);
  }

  saveOrUpdateSpecjalizacja(specjalizacja: Specjalizacja) {
    return this.http.post<Specjalizacja>(this.SPECJALIZACJE_API_URL, specjalizacja);
  }

  deleteSpecjalizacjaByNazwa(nazwa_specjalizacji: string){
    return this.http.delete(this.SPECJALIZACJE_API_URL + `/${nazwa_specjalizacji}`);
  }


}
