package com.szbd.hospital.service;

import com.szbd.hospital.dao.ReceptaDAO;
import com.szbd.hospital.entity.Lek;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Recepta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceptaServiceImpl implements ReceptaService {

    private ReceptaDAO receptaDAO;

    @Autowired
    public ReceptaServiceImpl(ReceptaDAO receptaDAO) {
        this.receptaDAO = receptaDAO;
    }


    @Override
    @Transactional
    public List<Recepta> findAll() {
        return receptaDAO.findAll();
    }

    @Override
    @Transactional
    public Recepta findById(int id) {
        return receptaDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Lek> findLekiOfRecepta(int id_recepty) {
        return receptaDAO.findLekiOfRecepta(id_recepty);
    }

    @Override
    @Transactional
    public void saveRecepta(Recepta recepta) {
        receptaDAO.saveRecepta(recepta);
    }

    @Override
    @Transactional
    public void addLekToRecepta(int id_recepty, String nazwa_leku) {
        receptaDAO.addLekToRecepta(id_recepty, nazwa_leku);
    }

    @Override
    @Transactional
    public void deleteReceptaById(int id) {
        receptaDAO.deleteReceptaById(id);
    }

    @Override
    @Transactional
    public void deleteLekFromRecepta(int id_recepty, String nazwa_leku) {
        receptaDAO.deleteLekFromRecepta(id_recepty, nazwa_leku);
    }

    @Override
    @Transactional
    public List<Lekarz> getAvailableLekarze(int id_karty) {
        return receptaDAO.getAvailableLekarze(id_karty);
    }
}
