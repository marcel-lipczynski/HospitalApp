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

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'pacjenci/:pesel/karty/:id_karty/operacje', component:OperacjeComponent},
  {path: 'pacjenci/:pesel/karty/:id_karty/diagnozy', component:DiagnozyComponent},
  {path: 'pacjenci/:pesel/karty/:id_karty/recepty', component:ReceptyComponent},
  {path: 'pacjenci/:pesel/karty', component: KartyComponent},
  {path: 'pacjenci', component: PacjenciComponent},
  {path: 'lekarze', component: LekarzeComponent},
  {path: 'pielegniarki', component: PielegniarkiComponent},
  {path: 'sale', component: SaleComponent},
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
