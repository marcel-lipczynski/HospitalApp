package com.szbd.hospital.dao;


import com.szbd.hospital.entity.Lekarz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LekarzaDAOImpl implements LekarzDAO {

    private EntityManager entityManager;

    @Autowired
    public LekarzaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Lekarz> findAll() {
        return entityManager.createQuery("from Lekarz",Lekarz.class).getResultList();
    }

    @Override
    public Lekarz findById(int id) {
        return entityManager.find(Lekarz.class,id);
    }

    @Override
    public void saveLekarz(@RequestBody Lekarz lekarz) {

        Lekarz lekarzDB = entityManager.find(Lekarz.class,lekarz.getId_lekarza());

        //jesli nie ma takiego lekarza to sprawdzamy czy jest taki
        //co ma takie samo imie i nazwisko, wtedy nie dodajemy go
        if(lekarzDB == null){
            for(Lekarz lek: findAll()){
                if(lek.getImie().equals(lekarz.getImie()) &&
                        lek.getNazwisko().equals(lekarz.getNazwisko())){
                    return;
                }
            }
        }

        entityManager.merge(lekarz);
    }

    @Override
    public void deleteLekarzById(int id) {
        Lekarz lekarz = entityManager.find(Lekarz.class,id);
        if(lekarz != null){
            entityManager.remove(lekarz);
        }
    }
}
