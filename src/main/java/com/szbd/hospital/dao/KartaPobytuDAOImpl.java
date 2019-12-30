package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class KartaPobytuDAOImpl implements KartaPobytuDAO{

    private EntityManager entityManager;

    @Autowired
    public KartaPobytuDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<KartaPobytu> findAll() {
        return entityManager.createQuery("from KartaPobytu", KartaPobytu.class).getResultList();
    }
}
