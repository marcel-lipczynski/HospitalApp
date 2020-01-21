package com.szbd.hospital.service;

import com.szbd.hospital.dao.OperacjaDAO;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Operacja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperacjaServiceImpl implements OperacjaService {

    private OperacjaDAO operacjaDAO;

    @Autowired
    public OperacjaServiceImpl(OperacjaDAO operacjaDAO) {
        this.operacjaDAO = operacjaDAO;
    }


    @Override
    @Transactional
    public List<Operacja> findAll() {
        return operacjaDAO.findAll();
    }

    @Override
    @Transactional
    public Operacja findOperacjaById(int id) {
        return operacjaDAO.findOperacjaById(id);
    }

    @Override
    @Transactional
    public void saveOperacja(Operacja operacja) {
        operacjaDAO.saveOperacja(operacja);
    }

    @Override
    @Transactional
    public void deleteOperacjaById(int id) {
        operacjaDAO.deleteOperacjaById(id);
    }

    @Override
    @Transactional
    public List<Lekarz> getAvailableLekarze(int id_karty) {
        return operacjaDAO.getAvailableLekarze(id_karty);
    }
}
