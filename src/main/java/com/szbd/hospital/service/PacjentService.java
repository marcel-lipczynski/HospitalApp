package com.szbd.hospital.service;


import com.szbd.hospital.entity.*;

import java.util.List;

public interface PacjentService {


    //Pacjenci
    List<Pacjent> findAll();
    Pacjent findById(String id);
    void save(Pacjent pacjent);
    void deleteById(String id);

    //karty pobytu
    List<KartaPobytu> findAllKartyPobytuOfPacjent(String pesel);
    void saveKartaPobytuForPacjent(KartaPobytu kartaPobytu, String pesel);
    void deleteKartaPobytuFromPacjent(String pesel, int id_karty);

    //diagnozy
    List<Diagnoza> findAllDiagnozaForKartaPobytu(int id_karty);
    void saveDiagnozaForKartaPobytu(Diagnoza diagnoza, int id_karty);
    void deleteDiagnozaFromKartaPobytu(int id_karty, int id_diagnozy);

    //operacje
    List<Operacja> findAllOperacjeForKartaPobytu(int id_karty);
    void saveOperacjaForKartaPobytu(Operacja operacja, int id_karty);
    void deleteOperacjaFromKartaPobytu(int id_karty, int id_operacji);

    //recepty
    List<Recepta> findAllReceptyForKartaPobytu(int id_karty);
    void saveReceptaForKartaPobytu(Recepta recepta, int id_karty);
    void deleteReceptaFromKartaPobytu(int id_karty, int id_recepty);
}
