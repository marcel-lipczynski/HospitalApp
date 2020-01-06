package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Diagnoza;

import java.util.List;

public interface DiagnozaDAO {

    List<Diagnoza> findAll();
    Diagnoza findDiagnozaById(int id);
    void saveDiagnoza(Diagnoza diagnoza);
    void deleteDiagnozaById(int id);

}
