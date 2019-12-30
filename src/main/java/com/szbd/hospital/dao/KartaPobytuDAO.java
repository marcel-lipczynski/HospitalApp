package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;

import java.util.List;

public interface KartaPobytuDAO {

    List<KartaPobytu> findAll();
    KartaPobytu findById(int id);
    void saveKarta(KartaPobytu kartaPobytu);
    void deleteKartaById(int id);

}
