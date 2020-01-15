package com.szbd.hospital.dao;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Lek;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Recepta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceptaDAOImpl implements ReceptaDAO {

    private EntityManager entityManager;

    @Autowired
    public ReceptaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Recepta> findAll() {
        return entityManager.createQuery("from Recepta", Recepta.class).getResultList();
    }

    @Override
    public Recepta findById(int id) {
        return entityManager.find(Recepta.class, id);
    }

    @Override
    public List<Lek> findLekiOfRecepta(int id_recepty) {
        return entityManager.find(Recepta.class, id_recepty).getLeki();
    }

    @Override
    public void saveRecepta(Recepta recepta) {
        Lekarz lekarz = entityManager.find(Lekarz.class, recepta.getId_lekarza());
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, recepta.getId_karty());

        //nie ma takiej karty lub takiego lekarza wiec nie da sie wystawic recepty
        if (kartaPobytu == null || lekarz == null) {
            return;
        }

        List<Recepta> recepty = entityManager.createQuery("from Recepta", Recepta.class).getResultList();
        //jesli znajde recepte o takim samym id lekarza i id karty to nie dodaje ani nie updatuje bo po co do tego samego
        for (Recepta rec : recepty) {
            if (rec.getId_lekarza() == recepta.getId_lekarza() &&
                    rec.getId_karty() == recepta.getId_karty()) {
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
            lekarz.addRecepta(recepta);
            kartaPobytu.addRecepta(recepta);

            entityManager.merge(recepta);
        }

    }


    @Override
    public void addLekToRecepta(int id_recepty, String nazwa_leku) {
        Recepta recepta = entityManager.find(Recepta.class, id_recepty);
        Lek lek = entityManager.find(Lek.class, nazwa_leku);

        if (recepta != null && lek != null) {
            for (Lek recLek : recepta.getLeki()) {
                if (recLek.getNazwa_leku().equals(lek.getNazwa_leku())) {
                    return;
                }
            }
            recepta.addLek(lek);
            lek.addRecepta(recepta);
        }
    }

    @Override
    public void deleteReceptaById(int id) {
        Recepta recepta = entityManager.find(Recepta.class, id);
        if (recepta != null) {
            entityManager.remove(recepta);
        }
    }

    @Override
    public void deleteLekFromRecepta(int id_recepty, String nazwa_leku) {
        Recepta recepta = entityManager.find(Recepta.class, id_recepty);
        Lek lek = entityManager.find(Lek.class, nazwa_leku);

        if (recepta != null && lek != null && recepta.getLeki().indexOf(lek) != -1) {
            recepta.removeLek(lek);
            lek.removeRecepta(recepta);
        }

    }

    @Override
    public List<Lekarz> getAvailableLekarze(int id_karty) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        List<Lekarz> allLekarze = kartaPobytu.getLekarze();
        List<Lekarz> availableLekarze = new ArrayList<>(allLekarze);

        for(Lekarz lekarz: allLekarze){
            for(Recepta recepta: lekarz.getRecepty()){
                if(recepta.getId_karty() == id_karty){
                    availableLekarze.remove(lekarz);
                }
            }
        }
        return availableLekarze;
    }
}
