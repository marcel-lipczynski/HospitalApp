package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Lek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LekDAOImpl implements LekDAO {

    private EntityManager entityManager;

    @Autowired
    public LekDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Lek> findAll() {
        return entityManager.createQuery("from Lek L ORDER BY L.nazwa_leku", Lek.class).getResultList();
    }

    @Override
    public Lek findLekById(String id) {
        return entityManager.find(Lek.class, id);
    }

    @Override
    public void saveLek(Lek lek) {
        entityManager.merge(lek);
    }

    @Override
    public void deleteLekById(String id) {
        Lek lek = entityManager.find(Lek.class, id);
        if (lek != null) {
            entityManager.remove(lek);
        }
    }

}
