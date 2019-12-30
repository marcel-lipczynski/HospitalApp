package com.szbd.hospital.service;

import com.szbd.hospital.dao.KartaPobytuDAO;
import com.szbd.hospital.entity.KartaPobytu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KartaPobytuServiceImpl implements KartaPobytuService {


    private KartaPobytuDAO kartaPobytuDAO;

    @Autowired
    public KartaPobytuServiceImpl(KartaPobytuDAO kartaPobytuDAO) {
        this.kartaPobytuDAO = kartaPobytuDAO;
    }

    @Override
    @Transactional
    public List<KartaPobytu> findAll() {
        return kartaPobytuDAO.findAll();
    }

    @Override
    @Transactional
    public KartaPobytu findById(int id) {
        return kartaPobytuDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveKarta(KartaPobytu kartaPobytu) {
        kartaPobytuDAO.saveKarta(kartaPobytu);
    }

    @Override
    @Transactional
    public void deleteKartaById(int id) {
        kartaPobytuDAO.deleteKartaById(id);
    }
}
