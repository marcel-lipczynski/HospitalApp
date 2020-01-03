package com.szbd.hospital.service;

import com.szbd.hospital.entity.Specjalizacje;

import java.util.List;

public interface SpecjalizacjeService {

    List<Specjalizacje> findAll();
    Specjalizacje findById(String id);
    void saveSpecjalizacje(Specjalizacje specjalizacje);
    void deleteSpecjalizacjeById(int id);

}
