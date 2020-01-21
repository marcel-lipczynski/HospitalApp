package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Lekarz;

import java.util.List;

public interface KartaPobytuDAO {

    List<KartaPobytu> findAll();
    KartaPobytu findById(int id);
    List<Lekarz> findLekarzeOnKartaPobytu(int id_karty);
    List<Lekarz> findAvailableLekarze(int id_karty);
    void saveKarta(KartaPobytu kartaPobytu);
    void addLekarzToKartaPobytu(int id_karty, int id_lekarza);
    void deleteKartaById(int id);
    void deleteLekarzFromKarta(int id_karty, int id_lekarza);

    void addWypisToKarta(int id_karty);

}
