package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Specjalizacje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SpecjalizacjeDAOImpl implements SpecjalizacjeDAO {

    private EntityManager entityManager;

    @Autowired
    public SpecjalizacjeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Specjalizacje> findAll() {
        return entityManager.createQuery("from Specjalizacje S ORDER BY S.nazwa_specjalizacji", Specjalizacje.class).getResultList();
    }

    @Override
    public Specjalizacje findById(String id) {
        return entityManager.find(Specjalizacje.class, id);
    }

    @Override
    public void saveSpecjalizacje(Specjalizacje specjalizacje) {
//        Specjalizacje specjalizacjeDB = entityManager.find(Specjalizacje.class, specjalizacje.getNazwa_specjalizacji());
        entityManager.merge(specjalizacje);
    }

    @Override
    public void deleteSpecjalizacjeById(String id) {
        Specjalizacje specjalizacje = entityManager.find(Specjalizacje.class, id);
        List<Lekarz> lekarze = entityManager.createQuery("from Lekarz", Lekarz.class).getResultList();

        for(Lekarz lekarzDB: lekarze){
            if(lekarzDB.getSpecjalizacje().indexOf(specjalizacje) != -1){
                lekarzDB.getSpecjalizacje().remove(specjalizacje);
            }
        }

        if (specjalizacje != null) {
            entityManager.remove(specjalizacje);
        }
    }

}
