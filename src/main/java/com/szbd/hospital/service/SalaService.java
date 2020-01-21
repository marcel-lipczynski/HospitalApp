package com.szbd.hospital.service;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;

import java.util.List;

public interface SalaService {

    List<Sala> findAll();
    Sala findById(int id);
    List<Pielegniarka> findAllPielegniarkaOfSala(int id);
    void saveSala(Sala sala);
    void saveSalaWithIdPielegniarki(int idPielegniarki, int nr_sali);
    void deletePielegniarkaFromSala(int idPielegniarki, int nrSali);
    void deleteById(int id);


    List<KartaPobytu> findActiveKartyForSala(int nr_sali);
    List<Sala> findAllAvailableSale();
    List<Pielegniarka> findAvailablePielegniarkiForSala(int nr_sali);
}
