import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {KartyComponent} from "./karty/karty.component";
import {HomeComponent} from "./home/home.component";
import {PacjenciComponent} from "./pacjenci/pacjenci.component";
import {LekarzeComponent} from "./lekarze/lekarze.component";
import {PielegniarkiComponent} from "./pielegniarki/pielegniarki.component";
import {SaleComponent} from "./sale/sale.component";
import {OperacjeComponent} from "./operacje/operacje.component";
import {DiagnozyComponent} from "./diagnozy/diagnozy.component";
import {ReceptyComponent} from "./recepty/recepty.component";
import {LekiComponent} from "./leki/leki.component";
import {SpecjalizacjeComponent} from "./specjalizacje/specjalizacje.component";
import {ReceptyListaComponent} from "./recepty/recepty-lista/recepty-lista.component";
import {ReceptyLekiComponent} from "./recepty/recepty-leki/recepty-leki.component";
import {LekarzeListaComponent} from "./lekarze/lekarze-lista/lekarze-lista.component";
import {LekarzeSpecjalizacjeComponent} from "./lekarze/lekarze-specjalizacje/lekarze-specjalizacje.component";
import {SaleListaComponent} from "./sale/sale-lista/sale-lista.component";
import {SalePielegniarkiComponent} from "./sale/sale-pielegniarki/sale-pielegniarki.component";
import {PielegniarkiListaComponent} from "./pielegniarki/pielegniarki-lista/pielegniarki-lista.component";
import {PielegniarkiSaleComponent} from "./pielegniarki/pielegniarki-sale/pielegniarki-sale.component";
import {KartyListaComponent} from "./karty/karty-lista/karty-lista.component";
import {KartyLekarzeComponent} from "./karty/karty-lekarze/karty-lekarze.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'pacjenci/:pesel/karty/:id_karty/operacje', component:OperacjeComponent},
  {path: 'pacjenci/:pesel/karty/:id_karty/diagnozy', component:DiagnozyComponent},
  {path: 'pacjenci/:pesel/karty/:id_karty/recepty', component:ReceptyComponent,
    children: [
      {path: '', component: ReceptyListaComponent, pathMatch: 'full'},
      {path: ':id_recepty/leki', component: ReceptyLekiComponent}
    ]},
  {path: 'pacjenci/:pesel/karty', component: KartyComponent,
    children: [
      {path: '', component: KartyListaComponent, pathMatch: 'full'},
      {path: ':id_karty/lekarze', component: KartyLekarzeComponent}
    ]},
  {path: 'pacjenci', component: PacjenciComponent},
  {path: 'lekarze', component: LekarzeComponent,
    children:[
      {path: '', component: LekarzeListaComponent, pathMatch: 'full'},
      {path: ':id_lekarza/specjalizacje', component: LekarzeSpecjalizacjeComponent},
    ]},
  {path: 'pielegniarki', component: PielegniarkiComponent,
    children:[
      {path: '', component: PielegniarkiListaComponent, pathMatch: 'full'},
      {path: ':id_pielegniarki/sale', component: PielegniarkiSaleComponent}
    ]},
  {path: 'sale', component: SaleComponent,
    children:[
      {path: '', component: SaleListaComponent, pathMatch: 'full'},
      {path: ':nr_sali/pielegniarki', component: SalePielegniarkiComponent}
    ]},
  {path: 'leki', component:LekiComponent},
  {path: 'specjalizacje', component:SpecjalizacjeComponent}

];

@NgModule({
  imports:[
    RouterModule.forRoot(appRoutes)
  ],
  exports:[
    RouterModule,
  ]

})

export class AppRoutingModule{

}
