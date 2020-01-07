import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent} from "./header/header.component";
import { HomeComponent } from './home/home.component';
import { BackgroundComponent } from './home/background/background.component';
import { TilesComponent } from './home/tiles/tiles.component';
import { KartyComponent } from './karty/karty.component';
import { KartyListaComponent } from './karty/karty-lista/karty-lista.component';
import {AppRoutingModule} from "./app-routing.module";
import { PacjenciComponent } from './pacjenci/pacjenci.component';
import { PacjenciListaComponent } from './pacjenci/pacjenci-lista/pacjenci-lista.component';
import { LekarzeComponent } from './lekarze/lekarze.component';
import { LekarzeListaComponent } from './lekarze/lekarze-lista/lekarze-lista.component';
import { PielegniarkiComponent } from './pielegniarki/pielegniarki.component';
import { PielegniarkiListaComponent } from './pielegniarki/pielegniarki-lista/pielegniarki-lista.component';
import { SaleComponent } from './sale/sale.component';
import { SaleListaComponent } from './sale/sale-lista/sale-lista.component';
import { OperacjeComponent } from './operacje/operacje.component';
import { OperacjeListaComponent } from './operacje/operacje-lista/operacje-lista.component';
import { DiagnozyComponent } from './diagnozy/diagnozy.component';
import { DiagnozyListaComponent } from './diagnozy/diagnozy-lista/diagnozy-lista.component';
import { ReceptyComponent } from './recepty/recepty.component';
import { ReceptyLekiComponent } from './recepty/recepty-leki/recepty-leki.component';
import { LekiComponent } from './leki/leki.component';
import { LekiListaComponent } from './leki/leki-lista/leki-lista.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    BackgroundComponent,
    TilesComponent,
    KartyComponent,
    KartyListaComponent,
    PacjenciComponent,
    PacjenciListaComponent,
    LekarzeComponent,
    LekarzeListaComponent,
    PielegniarkiComponent,
    PielegniarkiListaComponent,
    SaleComponent,
    SaleListaComponent,
    OperacjeComponent,
    OperacjeListaComponent,
    DiagnozyComponent,
    DiagnozyListaComponent,
    ReceptyComponent,
    ReceptyLekiComponent,
    LekiComponent,
    LekiListaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
