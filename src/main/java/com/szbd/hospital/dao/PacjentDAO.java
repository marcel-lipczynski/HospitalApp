package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pacjent;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PacjentDAO {

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

}
