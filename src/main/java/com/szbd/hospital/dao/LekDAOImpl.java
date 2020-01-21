package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Lek;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Recepta;
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
        List<Recepta> recepty = entityManager.createQuery("from Recepta", Recepta.class).getResultList();
        for (Recepta receptaDB : recepty) {
            if (lek.getRecepty().indexOf(receptaDB) != -1) {
                lek.getRecepty().remove(receptaDB);
                receptaDB.getLeki().remove(lek);

            }
            entityManager.remove(lek);


        }
    }

}
