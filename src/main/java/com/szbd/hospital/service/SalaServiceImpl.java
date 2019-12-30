package com.szbd.hospital.service;

import com.szbd.hospital.dao.SalaDAO;
import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaServiceImpl implements SalaService {

    private SalaDAO salaDAO;

    @Autowired
    public SalaServiceImpl(SalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    @Override
    @Transactional
    public List<Sala> findAll() {
        return salaDAO.findAll();
    }

    @Override
    @Transactional
    public Sala findById(int id) {
        return salaDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Pielegniarka> findAllPielegniarkaOfSala(int id) {
        return salaDAO.findAllPielegniarkaOfSala(id);
    }

    @Override
    @Transactional
    public void saveSala(Sala sala) {
        salaDAO.saveSala(sala);
    }

    @Override
    @Transactional
    public void saveSalaWithIdPielegniarki(int idPielegniarki, int nr_sali) {
        salaDAO.saveSalaWithIdPielegniarki(idPielegniarki,nr_sali);
    }

    @Override
    @Transactional
    public void deletePielegniarkaFromSala(int idPielegniarki, int nrSali) {
        salaDAO.deletePielegniarkaFromSala(idPielegniarki, nrSali);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        salaDAO.deleteById(id);
    }
}
