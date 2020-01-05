# HospitalApp
[![Build Status](https://travis-ci.org/marcel-lipczynski/HospitalApp.svg?branch=backend)](https://travis-ci.org/marcel-lipczynski/HospitalApp)

Hospital managment application created using Spring Boot, Angular and Oracle Database.

## Requests
  ### Pacjent
  * [GET] /pacjent -> returns all Pacjents objects
  * [GET] /pacjent/{id} -> returns Pacjent with given id
  ### Karta Pobytu
  * [GET] /karty -> returns all Karta Pobytu objects
  * [GET] /karty/{id} -> returns Karta Pobytu with given id
  ### Pielegniarka
  * [GET] /pielegniarka -> returns all Pielegniarka objects
  * [GET] /pielegniarka/{id} -> returns Pielegniarka with given id
  * [GET] /pielegniarka/{id}/sala -> returns all Sala of Pielegniarka with given id
  ### Sala
  * [GET] /sala -> returns all Sala objects
  * [GET] /sala/{nr_sali} -> returns Sala with given nr_sali
  * [GET] /sala/{nr_sali}/pielegniarka -> returns all Pielegniarka working in Sala with given nr_sali
  ### Lekarz
  * [GET] /lekarz -> returns all Lekarz objects
  * [GET] /lekarz/{id} -> returns Lekarz with given id 
  * [GET] /lekarz/{id}/specjalizacje -> returns all Specjalizacje that lekarz with given id has
  ### Specjalizacje
  * [GET] /specjalizacje -> returns all Specjalizacja objects
  * [GET] /specjalizacje/{id} -> returns Specjalizacja with given id 
  ### Lek
  * [GET] /lek -> returns all Lek objects
  * [GET] /lek/{id} -> returns Lek with given id 
  ### Recepta
  ### Diagnoza
  ### Operacja
  

