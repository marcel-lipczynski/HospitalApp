package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Recepta;

import java.util.List;

public interface ReceptaDAO {

    List<Recepta> findAll();
    Recepta findById(int id);
    void saveRecepta(Recepta recepta);
    void deleteReceptaById(int id);
}
