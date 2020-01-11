package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pacjent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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

    @Override
    public List<KartaPobytu> findAllKartyPobytuOfPacjent(String pesel) {
        return entityManager.find(Pacjent.class, pesel).getKartyPobytu();
    }

    @Override
    public void saveKartaPobytuForPacjent(KartaPobytu kartaPobytu, String pesel) {
        Pacjent pacjent = entityManager.find(Pacjent.class, pesel);
        if (pacjent != null) {
            kartaPobytu.setPesel(pesel);
            kartaPobytu.setPacjent(pacjent);
            pacjent.addKartaPobytu(kartaPobytu);
            entityManager.merge(kartaPobytu);
        }

    }

    @Override
    public void deleteKartaPobytuFromPacjent(String pesel, int id_karty) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        Pacjent pacjent = entityManager.find(Pacjent.class, pesel);
        if (kartaPobytu != null && pacjent != null) {
            entityManager.remove(kartaPobytu);
        }

    }

    @Override
    public List<Diagnoza> findAllDiagnozaForKartaPobytu(int id_karty) {
        return entityManager.find(KartaPobytu.class, id_karty).getDiagnozy();
    }

    @Override
    public void saveDiagnozaForKartaPobytu(Diagnoza diagnoza, int id_karty) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        if (kartaPobytu != null) {
            diagnoza.setId_karty(id_karty);
            diagnoza.setKartaPobytu(kartaPobytu);
            kartaPobytu.addDiagnoza(diagnoza);
            entityManager.merge(diagnoza);
        }
    }

    @Override
    public void deleteDiagnozaFromKartaPobytu(int id_karty, int id_diagnozy) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        Diagnoza diagnoza = entityManager.find(Diagnoza.class, id_diagnozy);
        if(kartaPobytu != null & diagnoza != null){
            entityManager.remove(diagnoza);
        }
    }
}
