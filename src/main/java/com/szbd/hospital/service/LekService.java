package com.szbd.hospital.service;

import com.szbd.hospital.entity.Lek;

import java.util.List;

public interface LekService {

    List<Lek> findAll();
    Lek findLekById(String id);
    void saveLek(Lek lek);
    void deleteLekById(String id);

}
