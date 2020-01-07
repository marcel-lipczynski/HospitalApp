package com.szbd.hospital.service;

import com.szbd.hospital.entity.Diagnoza;

import java.util.List;

public interface DiagnozaService {

    List<Diagnoza> findAll();
    Diagnoza findDiagnozaById(int id);
    void saveDiagnoza(Diagnoza diagnoza);
    void deleteDiagnozaById(int id);

}
