package com.szbd.hospital.dao;

import com.szbd.hospital.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        return entityManager.createQuery("from Pacjent P ORDER BY P.nazwisko", Pacjent.class).getResultList();
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
//        return entityManager.find(Pacjent.class, pesel).getKartyPobytu();

        TypedQuery<KartaPobytu> query = entityManager.createQuery("FROM KartaPobytu K WHERE K.pesel = :pesel ORDER BY K.data_przyjecia DESC, godzina_przyjecia DESC ", KartaPobytu.class);
        query.setParameter("pesel", pesel);
        return query.getResultList();
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
//        return entityManager.find(KartaPobytu.class, id_karty).getDiagnozy();
        TypedQuery<Diagnoza> query = entityManager.createQuery("FROM Diagnoza D WHERE D.id_karty = :id_karty ORDER BY D.data_wystawienia DESC ", Diagnoza.class);
        query.setParameter("id_karty", id_karty);
        return query.getResultList();
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
        if (kartaPobytu != null & diagnoza != null) {
            entityManager.remove(diagnoza);
        }
    }

    @Override
    public List<Operacja> findAllOperacjeForKartaPobytu(int id_karty) {
//        return entityManager.find(KartaPobytu.class, id_karty).getOperacje();
        TypedQuery<Operacja> query = entityManager.createQuery("FROM Operacja O WHERE O.id_karty = :id_karty ORDER BY O.termin DESC", Operacja.class);
        query.setParameter("id_karty", id_karty);
        return query.getResultList();

    }

    @Override
    public void saveOperacjaForKartaPobytu(Operacja operacja, int id_karty) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        if (kartaPobytu != null) {
            operacja.setId_karty(id_karty);
            operacja.setKartaPobytu(kartaPobytu);
            kartaPobytu.addOperacja(operacja);
            entityManager.merge(operacja);
        }
    }

    @Override
    public void deleteOperacjaFromKartaPobytu(int id_karty, int id_operacji) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        Operacja operacja = entityManager.find(Operacja.class, id_operacji);
        if (kartaPobytu != null & operacja != null) {
            entityManager.remove(operacja);
        }
    }

    @Override
    public List<Recepta> findAllReceptyForKartaPobytu(int id_karty) {
//        return entityManager.find(KartaPobytu.class, id_karty).getRecepty();
        TypedQuery<Recepta> query = entityManager.createQuery("FROM Recepta R WHERE R.id_karty = :id_karty ORDER BY R.data_wystawienia DESC ", Recepta.class);
        query.setParameter("id_karty", id_karty);
        return query.getResultList();
    }

    @Override
    public void saveReceptaForKartaPobytu(Recepta recepta, int id_karty) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        if (kartaPobytu != null) {
            recepta.setId_karty(id_karty);
            recepta.setKartaPobytu(kartaPobytu);
            kartaPobytu.addRecepta(recepta);
            entityManager.merge(recepta);
        }
    }

    @Override
    public void deleteReceptaFromKartaPobytu(int id_karty, int id_recepty) {
        KartaPobytu kartaPobytu = entityManager.find(KartaPobytu.class, id_karty);
        Recepta recepta = entityManager.find(Recepta.class, id_recepty);
        if (kartaPobytu != null & recepta != null) {
            entityManager.remove(recepta);
        }
    }
}
