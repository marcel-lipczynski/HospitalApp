package com.szbd.hospital.service;

import com.szbd.hospital.entity.Recepta;

import java.util.List;

public interface ReceptaService  {

    List<Recepta> findAll();
    Recepta findById(int id);
    void saveRecepta(Recepta recepta);
    void deleteReceptaById(int id);

}
