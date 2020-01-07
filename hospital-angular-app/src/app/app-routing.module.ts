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

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'karty', component: KartyComponent},
  {path: 'pacjenci', component: PacjenciComponent},
  {path: 'lekarze', component: LekarzeComponent},
  {path: 'pielegniarki', component: PielegniarkiComponent},
  {path: 'sale', component: SaleComponent},
  {path: 'operacje', component:OperacjeComponent},
  {path: 'diagnozy', component:DiagnozyComponent},
  {path: 'recepty', component:ReceptyComponent},
  {path: 'leki', component:LekiComponent}

];

@NgModule({
  imports:[
    RouterModule.forRoot(appRoutes)
  ],
  exports:[
    RouterModule
  ]

})

export class AppRoutingModule{

}
