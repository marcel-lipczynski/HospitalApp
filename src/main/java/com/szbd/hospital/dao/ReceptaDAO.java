package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lek;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Recepta;

import java.util.List;

public interface ReceptaDAO {

    List<Recepta> findAll();
    Recepta findById(int id);
    List<Lek> findLekiOfRecepta(int id_recepty);
    void saveRecepta(Recepta recepta);
    void addLekToRecepta(int id_recepty, String nazwa_leku);
    void deleteReceptaById(int id);
    void deleteLekFromRecepta(int id_recepty, String nazwa_leku);

    List<Lekarz> getAvailableLekarze(int id_karty);
    List<Lek> findAvailableLeki(int id_recepty);
}
