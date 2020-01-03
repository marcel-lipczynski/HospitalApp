package com.szbd.hospital.service;

import com.szbd.hospital.dao.SpecjalizacjeDAO;
import com.szbd.hospital.entity.Specjalizacje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecjalizacjeServiceImpl implements SpecjalizacjeService {

    private SpecjalizacjeDAO specjalizacjeDAO;

    @Autowired
    public SpecjalizacjeServiceImpl(SpecjalizacjeDAO specjalizacjeDAO) {
        this.specjalizacjeDAO = specjalizacjeDAO;
    }


    @Override
    @Transactional
    public List<Specjalizacje> findAll() {
        return specjalizacjeDAO.findAll();
    }

    @Override
    @Transactional
    public Specjalizacje findById(String id) {
        return specjalizacjeDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveSpecjalizacje(Specjalizacje specjalizacje) {
        specjalizacjeDAO.saveSpecjalizacje(specjalizacje);
    }

    @Override
    @Transactional
    public void deleteSpecjalizacjeById(int id) {
        specjalizacjeDAO.deleteSpecjalizacjeById(id);
    }
}
