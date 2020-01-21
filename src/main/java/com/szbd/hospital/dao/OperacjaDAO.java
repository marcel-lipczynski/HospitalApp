package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Operacja;

import javax.persistence.EntityManager;
import java.util.List;

public interface OperacjaDAO {

    List<Operacja> findAll();
    Operacja findOperacjaById(int id);
    void saveOperacja(Operacja operacja);
    void deleteOperacjaById(int id);

    List<Lekarz> getAvailableLekarze(int id_karty);

}
