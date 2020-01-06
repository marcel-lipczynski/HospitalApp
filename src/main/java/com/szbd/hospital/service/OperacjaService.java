package com.szbd.hospital.service;

import com.szbd.hospital.entity.Operacja;

import java.util.List;

public interface OperacjaService {

    List<Operacja> findAll();
    Operacja findOperacjaById(int id);
    void saveOperacja(Operacja operacja);
    void deleteOperacjaById(int id);

}
