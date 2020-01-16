package com.szbd.hospital.service;

import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;

import java.util.List;

public interface PielegniarkaService {
    List<Pielegniarka> findAll();
    Pielegniarka findById(int id);
    List<Sala> findAllSalaOfPielegniarka(int id);
    void savePielegniarka(Pielegniarka pielegniarka);
    void savePielegniarkaWitNrSali(int idPielegniarki, int nr_sali);
    void deleteSalaFromPielegniarka(int idPielegniarki, int nrSali);
    void deleteById(int id);

    List<Sala> findAvailableSaleForPielegniarka(int id_pielegniarki);
}
