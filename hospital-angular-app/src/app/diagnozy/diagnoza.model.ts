import {Lekarz} from "../lekarze/lekarz.model";

export interface Diagnoza{
  id_diagnozy: number,
  opis: string;
  data_wystawienia: string;
  id_lekarza : number;
  id_karty: number;
}
