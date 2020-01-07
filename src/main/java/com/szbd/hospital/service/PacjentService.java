package com.szbd.hospital.service;


import com.szbd.hospital.entity.Pacjent;

import java.util.List;

public interface PacjentService {

    List<Pacjent> findAll();
    Pacjent findById(String id);
    void save(Pacjent pacjent);
    void deleteById(String id);
}
