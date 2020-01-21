package com.szbd.hospital.service;

import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.Lekarz;

import java.util.List;

public interface DiagnozaService {

    List<Diagnoza> findAll();
    Diagnoza findDiagnozaById(int id);
    void saveDiagnoza(Diagnoza diagnoza);
    void deleteDiagnozaById(int id);

    public List<Lekarz> getAvailableLekarze(int id_karty);

}
