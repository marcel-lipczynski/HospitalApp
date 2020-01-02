package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lekarz;

import java.util.List;

public interface LekarzDAO {

    List<Lekarz> findAll();
    Lekarz findById(int id);
    void saveLekarz(Lekarz lekarz);
    void deleteLekarzById(int id);

}
