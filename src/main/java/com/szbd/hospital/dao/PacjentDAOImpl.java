package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Pacjent;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PacjentDAOImpl implements PacjentDAO {

    private EntityManager entityManager;

    @Autowired
    public PacjentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Pacjent> findAll() {

        Session session = entityManager.unwrap(Session.class);

        Query<Pacjent> pacjentQuery = session.createQuery("from Pacjent", Pacjent.class);

        List<Pacjent> pacjentList = pacjentQuery.getResultList();

        return pacjentList;

    }

    @Override
    public Pacjent findById(String pesel) {
        Session session = entityManager.unwrap(Session.class);
        Pacjent pacjent = session.get(Pacjent.class, pesel);

        if(pacjent == null){
            throw new RuntimeException("Pacjent z następującym peselem nie został znaleziony: " + pesel);
        }
        return session.get(Pacjent.class, pesel);
    }

    @Override
    public void save(Pacjent pacjent) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(pacjent);
    }

    @Override
    public void deleteById(String id) {
        Session session = entityManager.unwrap(Session.class);

        //delete object with primary key
        Pacjent pacjent = session.get(Pacjent.class, id);
        if(pacjent == null){
            throw new RuntimeException("Nie znaleziono pacjenta o podanym peselu: " + id);
        }
        session.delete(pacjent);
    }
}
