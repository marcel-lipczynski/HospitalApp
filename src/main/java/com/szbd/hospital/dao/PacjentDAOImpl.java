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
}
