package com.szbd.hospital.dao;

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
        return entityManager.createQuery("from Specjalizacje", Specjalizacje.class).getResultList();
    }

    @Override
    public Specjalizacje findById(int id) {
        return entityManager.find(Specjalizacje.class, id);
    }

    @Override
    public void saveSpecjalizacje(Specjalizacje specjalizacje) {
//        Specjalizacje specjalizacjeDB = entityManager.find(Specjalizacje.class, specjalizacje.getNazwa_specjalizacji());
        entityManager.merge(specjalizacje);
    }

    @Override
    public void deleteSpecjalizacjeById(int id) {
        Specjalizacje specjalizacje = entityManager.find(Specjalizacje.class, id);
        if (specjalizacje != null) {
            entityManager.remove(specjalizacje);
        }
    }

}
