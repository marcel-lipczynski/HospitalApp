package com.szbd.hospital.service;

import com.szbd.hospital.entity.Lekarz;

import java.util.List;

public interface LekarzService {

    List<Lekarz> findAll();
    Lekarz findById(int id);
    void saveLekarz(Lekarz lekarz);
    void deleteLekarzById(int id);

}
