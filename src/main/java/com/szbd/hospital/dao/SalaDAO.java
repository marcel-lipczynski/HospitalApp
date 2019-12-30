package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;

import java.util.List;

public interface SalaDAO {
    List<Sala> findAll();
    Sala findById(int id);
    List<Pielegniarka> findAllPielegniarkaOfSala(int id);
    void saveSala(Sala sala);
    void saveSalaWithIdPielegniarki(int idPielegniarki, int nr_sali);
    void deletePielegniarkaFromSala(int idPielegniarki, int nrSali);
    void deleteById(int id);
}
