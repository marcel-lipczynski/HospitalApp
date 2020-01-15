package com.szbd.hospital.service;

import com.szbd.hospital.dao.DiagnozaDAO;
import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.Lekarz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiagnozaServiceImpl implements DiagnozaService {

    private DiagnozaDAO diagnozaDAO;

    @Autowired
    public DiagnozaServiceImpl(DiagnozaDAO diagnozaDAO) {
        this.diagnozaDAO = diagnozaDAO;
    }

    @Override
    @Transactional
    public List<Diagnoza> findAll() {
        return diagnozaDAO.findAll();
    }

    @Override
    @Transactional
    public Diagnoza findDiagnozaById(int id) {
        return diagnozaDAO.findDiagnozaById(id);
    }

    @Override
    @Transactional
    public void saveDiagnoza(Diagnoza diagnoza) {
        diagnozaDAO.saveDiagnoza(diagnoza);
    }

    @Override
    @Transactional
    public void deleteDiagnozaById(int id) {
        diagnozaDAO.deleteDiagnozaById(id);
    }

    @Override
    @Transactional
    public List<Lekarz> getAvailableLekarze(int id_karty) {
        return diagnozaDAO.getAvailableLekarze(id_karty);
    }
}
