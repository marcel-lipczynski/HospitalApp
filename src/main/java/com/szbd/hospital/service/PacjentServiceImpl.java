package com.szbd.hospital.service;

import com.szbd.hospital.dao.PacjentDAO;
import com.szbd.hospital.entity.Pacjent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacjentServiceImpl implements PacjentService {

    private PacjentDAO pacjentDAO;

    @Autowired
    public PacjentServiceImpl(PacjentDAO pacjentDAO) {
        this.pacjentDAO = pacjentDAO;
    }

    @Override
    @Transactional
    public List<Pacjent> findAll() {
        return pacjentDAO.findAll();
    }
}
