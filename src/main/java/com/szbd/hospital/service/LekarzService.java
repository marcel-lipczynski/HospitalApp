package com.szbd.hospital.service;

import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Specjalizacje;

import java.util.List;

public interface LekarzService {

    List<Lekarz> findAll();
    Lekarz findById(int id);
    List<Specjalizacje> findAllSpecjalizacjeOfLekarz(int id);
    void saveLekarz(Lekarz lekarz);
    void saveSpecjalizacjaForLekarz(int id_lekarza, String nazwa_specjalizacji);
    void deleteLekarzById(int id);
    void deleteSpecjalizacjaFromLekarz(int id_lekarza, String nazwa_specjalizacji);

}
