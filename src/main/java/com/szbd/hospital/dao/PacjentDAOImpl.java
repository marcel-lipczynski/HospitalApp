package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Pacjent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PacjentDAOImpl implements PacjentDAO {

    private final EntityManager entityManager;

    @Autowired
    public PacjentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Pacjent> findAll() {
        return entityManager.createQuery("from Pacjent", Pacjent.class).getResultList();
    }

    @Override
    public Pacjent findById(String pesel) {
//        Pacjent pacjent = entityManager.find(Pacjent.class, pesel);
        return entityManager.find(Pacjent.class, pesel);
    }

    @Override
    public void save(Pacjent pacjent) {
        entityManager.merge(pacjent);
    }

    @Override
    public void deleteById(String id) {

        Pacjent pacjent = entityManager.find(Pacjent.class, id);
        if (pacjent != null) {
            entityManager.remove(pacjent);
        }

    }
}
