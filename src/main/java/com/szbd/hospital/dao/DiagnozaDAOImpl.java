package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Lekarz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DiagnozaDAOImpl implements DiagnozaDAO {

    private EntityManager entityManager;

    @Autowired
    public DiagnozaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Diagnoza> findAll() {
        return entityManager.createQuery("from Diagnoza", Diagnoza.class).getResultList();
    }

    @Override
    public Diagnoza findDiagnozaById(int id) {
        return entityManager.find(Diagnoza.class, id);
    }

    @Override
    public void saveDiagnoza(Diagnoza diagnoza) {

        Lekarz lekarz = entityManager.find(Lekarz.class, diagnoza.getId_lekarza());
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, diagnoza.getId_karty());

        //nie ma takiej karty lub takiego lekarza wiec nie da sie wystawic recepty
        if (kartaPobytu == null || lekarz == null) {
            return;
        }

        List<Diagnoza> diagnozy = entityManager.createQuery("from Diagnoza", Diagnoza.class).getResultList();
        //jesli znajde recepte o takim samym id lekarza i id karty to nie dodaje ani nie updatuje bo po co do tego samego
        for (Diagnoza diag : diagnozy) {
            if (diag.getId_lekarza() == diagnoza.getId_lekarza() &&
                    diag.getId_karty() == diagnoza.getId_karty()) {
                return;
            }
        }


        boolean isLekarzOnKartaPobytu = false;
//        sprawdz czy dany lekarz obsluguje w ogole karte pobytu!
        for (Lekarz lekarzDB : kartaPobytu.getLekarze()) {
            if (lekarzDB.getId_lekarza() == lekarz.getId_lekarza()) {
                isLekarzOnKartaPobytu = true;
                break;
            }
        }

        if (isLekarzOnKartaPobytu) {
            lekarz.addDiagnoza(diagnoza);
            kartaPobytu.addDiagnoza(diagnoza);

            entityManager.merge(diagnoza);
        }


    }

    @Override
    public void deleteDiagnozaById(int id) {
        Diagnoza diagnoza = entityManager.find(Diagnoza.class, id);
        if (diagnoza != null) {
            entityManager.remove(diagnoza);
        }
    }

}
