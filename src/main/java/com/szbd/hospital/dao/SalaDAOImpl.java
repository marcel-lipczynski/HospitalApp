package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Pielegniarka> pielegniarki = entityManager.find(Sala.class, id).getPielegniarki();
        Collections.sort(pielegniarki);
        return pielegniarki;
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

    @Override
    public List<Sala> findAllAvailableSale() {
        int counter = 0;
        List<Sala> availableSale = new ArrayList<>();
        List<Sala> allSale = entityManager.createQuery("from Sala S ORDER BY S.nr_sali",Sala.class).getResultList();

        List<KartaPobytu> kartyPobytu = entityManager.createQuery("from KartaPobytu",KartaPobytu.class).getResultList();

        for(Sala sala: allSale){
            for(KartaPobytu kartaPobytu: kartyPobytu){
                if(kartaPobytu.getData_wypisu() == null && kartaPobytu.getNr_sali() == sala.getNr_sali()){
                    counter++;
                }
            }
            if(sala.getPojemnosc() > counter){
                availableSale.add(sala);
            }
            counter = 0;
        }

        return availableSale;

    }

    @Override
    public List<Pielegniarka> findAvailablePielegniarkiForSala(int nr_sali) {
        List<Pielegniarka> pielegniarkaInDatabase = entityManager.createQuery("from Pielegniarka P ORDER BY P.nazwisko", Pielegniarka.class).getResultList();
        List<Pielegniarka> pielegniarkaInSala = entityManager.find(Sala.class, nr_sali).getPielegniarki();

        for(Pielegniarka pielegniarka: pielegniarkaInSala){
            pielegniarkaInDatabase.remove(pielegniarka);
        }

        return pielegniarkaInDatabase;
    }
}
