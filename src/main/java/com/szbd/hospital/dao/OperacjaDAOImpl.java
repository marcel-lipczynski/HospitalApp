package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Operacja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OperacjaDAOImpl implements OperacjaDAO {

    private EntityManager entityManager;

    @Autowired
    public OperacjaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Operacja> findAll() {
        return entityManager.createQuery("from Operacja", Operacja.class).getResultList();
    }

    @Override
    public Operacja findOperacjaById(int id) {
        return entityManager.find(Operacja.class, id);
    }

    @Override
    public void saveOperacja(Operacja operacja) {

        Lekarz lekarz = entityManager.find(Lekarz.class, operacja.getId_lekarza());
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, operacja.getId_karty());

        //nie ma takiej karty lub takiego lekarza wiec nie da sie wystawic recepty
        if (kartaPobytu == null || lekarz == null) {
            return;
        }

        List<Operacja> operacje = entityManager.createQuery("from Operacja", Operacja.class).getResultList();
        //jesli znajde recepte o takim samym id lekarza i id karty to nie dodaje ani nie updatuje bo po co do tego samego
        for (Operacja ope : operacje) {
            if (ope.getId_lekarza() == operacja.getId_lekarza() &&
                    ope.getId_karty() == operacja.getId_karty()) {
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
            lekarz.addOperacja(operacja);
            kartaPobytu.addOperacja(operacja);

            entityManager.merge(operacja);
        }
    }

    @Override
    public void deleteOperacjaById(int id) {
        Operacja operacja = entityManager.find(Operacja.class, id);
        if(operacja != null){
            entityManager.remove(operacja);
        }
    }
}
