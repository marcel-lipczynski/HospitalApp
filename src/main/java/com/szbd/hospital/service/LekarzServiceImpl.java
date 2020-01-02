package com.szbd.hospital.service;

import com.szbd.hospital.dao.LekarzDAO;
import com.szbd.hospital.entity.Lekarz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LekarzServiceImpl implements LekarzService {

    private LekarzDAO lekarzDAO;

    @Autowired
    public LekarzServiceImpl(LekarzDAO lekarzDAO) {
        this.lekarzDAO = lekarzDAO;
    }


    @Override
    @Transactional
    public List<Lekarz> findAll() {
        return lekarzDAO.findAll();
    }

    @Override
    @Transactional
    public Lekarz findById(int id) {
        return lekarzDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveLekarz(Lekarz lekarz) {
        lekarzDAO.saveLekarz(lekarz);
    }

    @Override
    @Transactional
    public void deleteLekarzById(int id) {
        lekarzDAO.deleteLekarzById(id);
    }
}
