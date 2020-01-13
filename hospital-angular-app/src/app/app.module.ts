import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import * as $AB from "jquery";
import * as bootstrap from "bootstrap";

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
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import { SpecjalizacjeComponent } from './specjalizacje/specjalizacje.component';
import { SpecjalizacjeListaComponent } from './specjalizacje/specjalizacje-lista/specjalizacje-lista.component';
import { ReceptyListaComponent } from './recepty/recepty-lista/recepty-lista.component';
import { LekarzeSpecjalizacjeComponent } from './lekarze/lekarze-specjalizacje/lekarze-specjalizacje.component';
import { SalePielegniarkiComponent } from './sale/sale-pielegniarki/sale-pielegniarki.component';
import { PielegniarkiSaleComponent } from './pielegniarki/pielegniarki-sale/pielegniarki-sale.component';


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
    SpecjalizacjeComponent,
    SpecjalizacjeListaComponent,
    ReceptyListaComponent,
    LekarzeSpecjalizacjeComponent,
    SalePielegniarkiComponent,
    PielegniarkiSaleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
