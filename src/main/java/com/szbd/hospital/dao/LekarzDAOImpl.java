package com.szbd.hospital.dao;


import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Specjalizacje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LekarzDAOImpl implements LekarzDAO {

    private EntityManager entityManager;

    @Autowired
    public LekarzDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Lekarz> findAll() {
        return entityManager.createQuery("from Lekarz", Lekarz.class).getResultList();
    }

    @Override
    public Lekarz findById(int id) {
        return entityManager.find(Lekarz.class, id);
    }

    @Override
    public List<Specjalizacje> findAllSpecjalizacjeOfLekarz(int id) {
        return entityManager.find(Lekarz.class, id).getSpecjalizacje();
    }

    @Override
    public void saveLekarz(@RequestBody Lekarz lekarz) {

        Lekarz lekarzDB = entityManager.find(Lekarz.class, lekarz.getId_lekarza());

        //jesli nie ma takiego lekarza to sprawdzamy czy jest taki
        //co ma takie samo imie i nazwisko, wtedy nie dodajemy go
        if (lekarzDB == null || lekarzDB.getId_lekarza() == lekarz.getId_lekarza()) {
            for (Lekarz lek : findAll()) {
                if (lek.getImie().equals(lekarz.getImie()) &&
                        lek.getNazwisko().equals(lekarz.getNazwisko()) &&
                        lek.getId_lekarza() != lekarz.getId_lekarza()) {
                    return;
                }
            }
        }

        entityManager.merge(lekarz);
    }

    @Override
    public void saveSpecjalizacjaForLekarz(int id_lekarza, String nazwa_specjalizacji) {
        Lekarz lekarz = entityManager.find(Lekarz.class, id_lekarza);
        Specjalizacje specjalizacje = entityManager.find(Specjalizacje.class, nazwa_specjalizacji);

        if (lekarz != null && specjalizacje != null) {
            for (Specjalizacje specj : lekarz.getSpecjalizacje()) {
                if (specj.getNazwa_specjalizacji().toUpperCase().equals(nazwa_specjalizacji)) {
                    return;
                }
            }
            lekarz.addSpecjalizacja(specjalizacje);
            specjalizacje.addLekarz(lekarz);
        }

    }

    @Override
    public void deleteLekarzById(int id) {
        Lekarz lekarz = entityManager.find(Lekarz.class, id);
        if (lekarz != null) {
            entityManager.remove(lekarz);
        }
    }

    @Override
    public void deleteSpecjalizacjaFromLekarz(int id_lekarza, String nazwa_specjalizacji) {

        Lekarz lekarz = entityManager.find(Lekarz.class, id_lekarza);
        Specjalizacje specjalizacje = entityManager.find(Specjalizacje.class, nazwa_specjalizacji);

        if (lekarz != null && specjalizacje != null && lekarz.getSpecjalizacje().indexOf(specjalizacje) != -1) {
            lekarz.removeSpecjalizacje(specjalizacje);
        }

    }
}
