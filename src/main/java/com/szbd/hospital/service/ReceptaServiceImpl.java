package com.szbd.hospital.service;

import com.szbd.hospital.dao.ReceptaDAO;
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
    public void saveRecepta(Recepta recepta) {
        receptaDAO.saveRecepta(recepta);
    }

    @Override
    @Transactional
    public void deleteReceptaById(int id) {
        receptaDAO.deleteReceptaById(id);
    }
}
