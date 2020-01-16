package com.szbd.hospital.service;

import com.szbd.hospital.dao.LekarzDAO;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Specjalizacje;
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
    public List<Specjalizacje> findAllSpecjalizacjeOfLekarz(int id) {
        return lekarzDAO.findAllSpecjalizacjeOfLekarz(id);
    }

    @Override
    @Transactional
    public void saveLekarz(Lekarz lekarz) {
        lekarzDAO.saveLekarz(lekarz);
    }

    @Override
    @Transactional
    public void saveSpecjalizacjaForLekarz(int id_lekarza, String nazwa_specjalizacji) {
        lekarzDAO.saveSpecjalizacjaForLekarz(id_lekarza, nazwa_specjalizacji);
    }

    @Override
    @Transactional
    public void deleteLekarzById(int id) {
        lekarzDAO.deleteLekarzById(id);
    }

    @Override
    @Transactional
    public void deleteSpecjalizacjaFromLekarz(int id_lekarza, String nazwa_specjalizacji) {
        lekarzDAO.deleteSpecjalizacjaFromLekarz(id_lekarza, nazwa_specjalizacji);
    }

    @Override
    @Transactional
    public List<Specjalizacje> findAvailableSpecjalizacjeForLekarz(int id_lekarza) {
        return lekarzDAO.findAvailableSpecjalizacjeForLekarz(id_lekarza);
    }
}
