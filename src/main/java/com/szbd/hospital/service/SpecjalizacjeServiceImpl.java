package com.szbd.hospital.service;

import com.szbd.hospital.dao.SpecjalizacjeDAO;
import com.szbd.hospital.entity.Specjalizacje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecjalizacjeServiceImpl implements SpecjalizacjeService {

    private SpecjalizacjeDAO specjalizacjeDAO;

    @Autowired
    public SpecjalizacjeServiceImpl(SpecjalizacjeDAO specjalizacjeDAO) {
        this.specjalizacjeDAO = specjalizacjeDAO;
    }


    @Override
    public List<Specjalizacje> findAll() {
        specjalizacjeDAO.findAll();
    }

    @Override
    public Specjalizacje findById(int id) {
        return specjalizacjeDAO.findById(id);
    }

    @Override
    public void saveSpecjalizacje(Specjalizacje specjalizacje) {
        specjalizacjeDAO.saveSpecjalizacje(specjalizacje);
    }

    @Override
    public void deleteSpecjalizacjeById(int id) {
        specjalizacjeDAO.deleteSpecjalizacjeById(id);
    }
}
