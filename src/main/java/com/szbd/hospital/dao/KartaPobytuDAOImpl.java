package com.szbd.hospital.dao;

import com.szbd.hospital.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KartaPobytuDAOImpl implements KartaPobytuDAO {

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
        return entityManager.find(KartaPobytu.class, id);
    }

    @Override
    public List<Lekarz> findLekarzeOnKartaPobytu(int id_karty) {
        return entityManager.find(KartaPobytu.class, id_karty).getLekarze();
    }

    @Override
    public void saveKarta(KartaPobytu kartaPobytu) {
        //Koniecznie sprawdz czy Karta nie ma podobnej godziny, daty i peselu do ktorejs
        // z istniejacych kart. Jedno z tych pol musi sie roznic!!!
        KartaPobytu kartaPobytuDB = entityManager.find(KartaPobytu.class, kartaPobytu.getId_karty());
        Pacjent pacjent = entityManager.find(Pacjent.class, kartaPobytu.getPesel());
        if (kartaPobytuDB == null) {
            if (pacjent == null) {
                //nie ma pacjenta o peselu podanym na karcie!
                return;
            }

            List<KartaPobytu> kartyPobytu = entityManager.createQuery(
                    "from KartaPobytu", KartaPobytu.class).getResultList();

            //sprawdzanie czy istnieje karta o takiej samej godzinie, dacie i peselu jednoczesnie
            for (KartaPobytu kar : kartyPobytu) {
                if (kar.getData_przyjecia() == kartaPobytu.getData_przyjecia()
                        && kar.getPesel().equals(kartaPobytu.getPesel())
                        && kar.getGodzina_przyjecia().equals(kartaPobytu.getGodzina_przyjecia())) {
                    return;
                }
            }
        }
        //ustaw pacjenta o takim peselu jaki jest w karcie
        kartaPobytu.setPacjent(pacjent);
        //dodaj temu pacjentowi ta karte pobytu
        pacjent.addKartaPobytu(kartaPobytu);

        entityManager.merge(kartaPobytu);

    }

    @Override
    public void addLekarzToKartaPobytu(int id_karty, int id_lekarza) {

        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        Lekarz lekarz = entityManager.find(Lekarz.class, id_lekarza);

        if (kartaPobytu != null && lekarz != null) {
            for (Lekarz lekKarta : kartaPobytu.getLekarze()) {
                if (lekKarta.getId_lekarza() == id_lekarza) {
                    return;
                }
            }
            kartaPobytu.addLekarz(lekarz);
            lekarz.addKartaPobytu(kartaPobytu);
        }

    }

    @Override
    public void deleteKartaById(int id) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id);
        if (kartaPobytu != null) {
            entityManager.remove(kartaPobytu);
        }

    }

    @Override
    public void deleteLekarzFromKarta(int id_karty, int id_lekarza) {
        System.out.println("id_karty: " + id_karty + " id_lekarza: " + id_lekarza);
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        Lekarz lekarz = entityManager.find(Lekarz.class, id_lekarza);
        if (kartaPobytu != null && lekarz != null && kartaPobytu.getLekarze().indexOf(lekarz) != -1) {


            List<Diagnoza> diagnozy = new ArrayList<>(kartaPobytu.getDiagnozy());
            List<Operacja> operacje = new ArrayList<>(kartaPobytu.getOperacje());
            List<Recepta> recepty = new ArrayList<>(kartaPobytu.getRecepty());


//            List<Diagnoza> diagnozy = entityManager.createQuery("from Diagnoza", Diagnoza.class).getResultList();
            for (Diagnoza diagnoza : diagnozy) {
                if (diagnoza.getId_lekarza() == id_lekarza && diagnoza.getId_karty() == id_karty) {
                    entityManager.remove(diagnoza);
                }
            }
//
//            List<Recepta> recepty = entityManager.createQuery("from Recepta", Recepta.class).getResultList();
            for(Recepta recepta: recepty){
                if (recepta.getId_lekarza() == id_lekarza && recepta.getId_karty() == id_karty) {
                    entityManager.remove(recepta);
                }
            }
//
//            List<Operacja> operacje = entityManager.createQuery("from Operacja", Operacja.class).getResultList();
            for(Operacja operacja: operacje){
                if (operacja.getId_lekarza() == id_lekarza && operacja.getId_karty() == id_karty) {
                    entityManager.remove(operacja);
                }
            }

            kartaPobytu.removeLekarz(lekarz);
            lekarz.removeKartaPobytu(kartaPobytu);

        }
    }
}
