import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Lekarz} from "./lekarz.model";


@Injectable({
  providedIn: 'root'
})
export class LekarzService{

  readonly LEKARZE_API_URL = '/api/lekarze'

  constructor(private http: HttpClient){}

  findAllLekarze(): Observable<Lekarz[]>{
    return this.http.get<Lekarz[]>(this.LEKARZE_API_URL);
  }

  findLekarzById(id_lekarza : number): Observable<Lekarz>{
    return this.http.get<Lekarz>(this.LEKARZE_API_URL + `/${id_lekarza}`)
  }

  saveOrPostLekarz(lekarz: Lekarz){
    return this.http.post<Lekarz>(this.LEKARZE_API_URL, lekarz);
  }

  deleteLekarzById(id_lekarza: string){
    return this.http.delete(this.LEKARZE_API_URL + `/${id_lekarza}`)
  }


}
