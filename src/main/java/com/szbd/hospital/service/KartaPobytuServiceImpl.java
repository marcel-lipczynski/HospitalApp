package com.szbd.hospital.service;

import com.szbd.hospital.dao.KartaPobytuDAO;
import com.szbd.hospital.entity.KartaPobytu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KartaPobytuServiceImpl implements KartaPobytuService {


    private KartaPobytuDAO kartaPobytuDAO;

    @Autowired
    public KartaPobytuServiceImpl(KartaPobytuDAO kartaPobytuDAO) {
        this.kartaPobytuDAO = kartaPobytuDAO;
    }

    @Override
    public List<KartaPobytu> findAll() {
        return kartaPobytuDAO.findAll();
    }
}
