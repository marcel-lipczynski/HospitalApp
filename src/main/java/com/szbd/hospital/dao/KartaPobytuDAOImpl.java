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

    @Override
    public KartaPobytu findById(int id) {
        return entityManager.find(KartaPobytu.class,id);
    }

    @Override
    public void saveKarta(KartaPobytu kartaPobytu) {
        //Koniecznie sprawdz czy Karta nie ma podobnej godziny, daty i peselu do ktorejs
        // z istniejacych kart. Jedno z tych pol musi sie roznic!!!
        entityManager.merge(kartaPobytu);

    }

    @Override
    public void deleteKartaById(int id) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class,id);
        if(kartaPobytu != null){
            entityManager.remove(kartaPobytu);
        }

    }
}
