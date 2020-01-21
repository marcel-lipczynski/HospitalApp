package com.szbd.hospital.service;

import com.szbd.hospital.dao.PielegniarkaDAO;
import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PielegniarkaServiceImpl implements PielegniarkaService {

    private PielegniarkaDAO pielegniarkaDAO;

    @Autowired
    public PielegniarkaServiceImpl(PielegniarkaDAO pielegniarkaDAO) {
        this.pielegniarkaDAO = pielegniarkaDAO;
    }

    @Override
    @Transactional
    public List<Pielegniarka> findAll() {
        return pielegniarkaDAO.findAll();
    }

    @Override
    @Transactional
    public Pielegniarka findById(int id) {
        return pielegniarkaDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Sala> findAllSalaOfPielegniarka(int id) {
        return pielegniarkaDAO.findAllSalaOfPielegniarka(id);
    }

    @Override
    @Transactional
    public void savePielegniarka(Pielegniarka pielegniarka) {
        pielegniarkaDAO.savePielegniarka(pielegniarka);
    }


    @Override
    @Transactional
    public void savePielegniarkaWitNrSali(int idPielegniarki, int nr_sali) {
        pielegniarkaDAO.savePielegniarkaWitNrSali(idPielegniarki,nr_sali);
    }

    @Override
    @Transactional
    public void deleteSalaFromPielegniarka(int idPielegniarki, int nrSali) {
        pielegniarkaDAO.deleteSalaFromPielegniarka(idPielegniarki,nrSali);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        pielegniarkaDAO.deleteById(id);
    }

    @Override
    @Transactional
    public List<Sala> findAvailableSaleForPielegniarka(int id_pielegniarki) {
        return pielegniarkaDAO.findAvailableSaleForPielegniarka(id_pielegniarki);
    }
}
