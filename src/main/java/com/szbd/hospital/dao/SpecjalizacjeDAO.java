package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Specjalizacje;

import java.util.List;

public interface SpecjalizacjeDAO {

    List<Specjalizacje> findAll();
    Specjalizacje findById(int id);
    void saveSpecjalizacje(Specjalizacje specjalizacje);
    void deleteSpecjalizacjeById(int id);

}
