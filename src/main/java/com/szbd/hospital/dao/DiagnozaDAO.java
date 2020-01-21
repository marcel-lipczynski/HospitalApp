package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.Lekarz;

import java.util.List;

public interface DiagnozaDAO {

    List<Diagnoza> findAll();
    Diagnoza findDiagnozaById(int id);
    void saveDiagnoza(Diagnoza diagnoza);
    void deleteDiagnozaById(int id);

    public List<Lekarz> getAvailableLekarze(int id_karty);

}
