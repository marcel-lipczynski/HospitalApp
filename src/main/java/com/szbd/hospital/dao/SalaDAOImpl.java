package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SalaDAOImpl implements SalaDAO {

    private EntityManager entityManager;

    @Autowired
    public SalaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Sala> findAll() {
        return entityManager.createQuery("from Sala S ORDER BY S.nr_sali", Sala.class).getResultList();
    }

    @Override
    public Sala findById(int id) {
        return entityManager.find(Sala.class, id);
    }

    @Override
    public List<Pielegniarka> findAllPielegniarkaOfSala(int id) {
        return entityManager.find(Sala.class, id).getPielegniarki();
    }

    @Override
    public void saveSala(Sala sala) {
        entityManager.merge(sala);
    }

    @Override
    public void saveSalaWithIdPielegniarki(int idPielegniarki, int nr_sali) {
        Pielegniarka pielegniarka = entityManager.find(Pielegniarka.class, idPielegniarki);
        Sala sala = entityManager.find(Sala.class, nr_sali);

        if (pielegniarka != null && sala != null) {
            //nie dodajemy pielegniarki ktora juz jest!
            for (Pielegniarka salPiel : sala.getPielegniarki()) {
                if (salPiel.getId_pielegniarki() == idPielegniarki) {
                    return;
                }
            }
            pielegniarka.addSala(sala);
            sala.addPielegniarka(pielegniarka);
        }

    }

    @Override
    public void deletePielegniarkaFromSala(int idPielegniarki, int nrSali) {
        Sala sala = entityManager.find(Sala.class, nrSali);
        Pielegniarka pielegniarka = entityManager.find(Pielegniarka.class, idPielegniarki);
        if (pielegniarka != null && sala != null && sala.getPielegniarki().indexOf(pielegniarka) != -1) {
            sala.removePielegniarka(pielegniarka);
        }
    }

    @Override
    public void deleteById(int id) {
        Sala sala = entityManager.find(Sala.class, id);
        if (sala != null) {
            entityManager.remove(sala);
        }

    }

    @Override
    public List<KartaPobytu> findActiveKartyForSala(int nr_sali) {

        TypedQuery<KartaPobytu> query = entityManager.createQuery("FROM KartaPobytu K WHERE K.nr_sali = :nr_sali AND K.data_wypisu IS NULL", KartaPobytu.class);
        query.setParameter("nr_sali", nr_sali);
        return query.getResultList();

    }
}
