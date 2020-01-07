package com.szbd.hospital.service;

import com.szbd.hospital.dao.LekDAO;
import com.szbd.hospital.entity.Lek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LekServiceImpl implements LekService{

    private LekDAO lekDAO;

    @Autowired
    public LekServiceImpl(LekDAO lekDAO) {
        this.lekDAO = lekDAO;
    }


    @Override
    @Transactional
    public List<Lek> findAll() {
        return lekDAO.findAll();
    }

    @Override
    @Transactional
    public Lek findLekById(String id) {
        return lekDAO.findLekById(id);
    }

    @Override
    @Transactional
    public void saveLek(Lek lek) {
        lekDAO.saveLek(lek);
    }

    @Override
    @Transactional
    public void deleteLekById(String id) {
        lekDAO.deleteLekById(id);
    }
}
