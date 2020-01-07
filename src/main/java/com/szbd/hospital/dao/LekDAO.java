package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lek;

import java.util.List;

public interface LekDAO {

    List<Lek> findAll();
    Lek findLekById(String id);
    void saveLek(Lek lek);
    void deleteLekById(String id);

}
