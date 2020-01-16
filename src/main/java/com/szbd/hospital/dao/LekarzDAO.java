package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Specjalizacje;

import java.util.List;

public interface LekarzDAO {

    List<Lekarz> findAll();
    Lekarz findById(int id);
    List<Specjalizacje> findAllSpecjalizacjeOfLekarz(int id);
    void saveLekarz(Lekarz lekarz);
    void saveSpecjalizacjaForLekarz(int id_lekarza, String nazwa_specjalizacji);
    void deleteLekarzById(int id);
    void deleteSpecjalizacjaFromLekarz(int id_lekarza, String nazwa_specjalizacji);

    List<Specjalizacje> findAvailableSpecjalizacjeForLekarz(int id_lekarza);

}
