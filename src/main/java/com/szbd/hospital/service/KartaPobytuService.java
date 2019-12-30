package com.szbd.hospital.service;

import com.szbd.hospital.entity.KartaPobytu;

import java.util.List;

public interface KartaPobytuService{

    List<KartaPobytu> findAll();
    KartaPobytu findById(int id);
    void saveKarta(KartaPobytu kartaPobytu);
    void deleteKartaById(int id);

}
