package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PielegniarkaDAOImpl implements PielegniarkaDAO {

    private EntityManager entityManager;

    @Autowired
    public PielegniarkaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Pielegniarka> findAll() {
        return entityManager.createQuery("from Pielegniarka P ORDER BY P.nazwisko", Pielegniarka.class).getResultList();
    }

    @Override
    public Pielegniarka findById(int id) {
        //zwraca pacjenta jesli go znajdzie, jesli go nie ma
        //zwroci pouste response body
        return entityManager.find(Pielegniarka.class, id);
    }


    //zwroc wszystkie sale danej pielegniarki
    @Override
    public List<Sala> findAllSalaOfPielegniarka(int id) {
        return entityManager.find(Pielegniarka.class, id).getSale();
    }

    @Override
    public void savePielegniarka(Pielegniarka pielegniarka) {
        entityManager.merge(pielegniarka);
    }


    //Dodaj sale dla pielegniarki w szczegolach danej pielegniarki
    @Override
    public void savePielegniarkaWitNrSali(int idPielegniarki, int nr_sali) {
        Pielegniarka pielegniarka = entityManager.find(Pielegniarka.class, idPielegniarki);
        Sala sala = entityManager.find(Sala.class, nr_sali);

        if (pielegniarka != null && sala != null) {
            //nie dodajemy sali ktora juz jest!
            for (Sala salePiel : pielegniarka.getSale()) {
                if (salePiel.getNr_sali() == nr_sali) {
                    return;
                }
            }
            pielegniarka.addSala(sala);
            sala.addPielegniarka(pielegniarka);
        }
    }

    @Override
    public void deleteSalaFromPielegniarka(int idPielegniarki, int nrSali) {
        Pielegniarka pielegniarka = entityManager.find(Pielegniarka.class, idPielegniarki);
        Sala sala = entityManager.find(Sala.class, nrSali);
        if (pielegniarka != null && sala != null && pielegniarka.getSale().indexOf(sala) != -1) {
            pielegniarka.removeSala(sala);
            sala.removePielegniarka(pielegniarka);
        }
    }


    @Override
    public void deleteById(int id) {
        Pielegniarka pielegniarka = entityManager.find(Pielegniarka.class, id);
        List<Sala> sale = entityManager.createQuery("from Sala", Sala.class).getResultList();
        for (Sala salaDB : sale) {
            if(pielegniarka.getSale().indexOf(salaDB) != -1)
            pielegniarka.getSale().remove(salaDB);
            salaDB.getPielegniarki().remove(pielegniarka);

        }
        entityManager.remove(pielegniarka);



    }

    @Override
    public List<Sala> findAvailableSaleForPielegniarka(int id_pielegniarki) {
        List<Sala> saleInDatabase = entityManager.createQuery("from Sala S ORDER BY S.nr_sali", Sala.class).getResultList();
        List<Sala> salaOnPielegniarka = entityManager.find(Pielegniarka.class, id_pielegniarki).getSale();

        for(Sala sala: salaOnPielegniarka){
            saleInDatabase.remove(sala);
        }

        return saleInDatabase;
    }
}
