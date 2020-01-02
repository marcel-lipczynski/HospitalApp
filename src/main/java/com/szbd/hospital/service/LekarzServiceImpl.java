package com.szbd.hospital.service;

import com.szbd.hospital.dao.LekarzDAO;
import com.szbd.hospital.entity.Lekarz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LekarzServiceImpl implements LekarzService {

    private LekarzDAO lekarzDAO;

    @Autowired
    public LekarzServiceImpl(LekarzDAO lekarzDAO) {
        this.lekarzDAO = lekarzDAO;
    }


    @Override
    public List<Lekarz> findAll() {
        return lekarzDAO.findAll();
    }

    @Override
    public Lekarz findById(int id) {
        return lekarzDAO.findById(id);
    }

    @Override
    public void saveLekarz(Lekarz lekarz) {
        lekarzDAO.saveLekarz(lekarz);
    }

    @Override
    public void deleteLekarzById(int id) {
        lekarzDAO.deleteLekarzById(id);
    }
}
