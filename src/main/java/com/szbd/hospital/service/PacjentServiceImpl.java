package com.szbd.hospital.service;

import com.szbd.hospital.dao.PacjentDAO;
import com.szbd.hospital.entity.*;
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

    @Override
    @Transactional
    public Pacjent findById(String id) {
        return pacjentDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Pacjent pacjent) {
        pacjentDAO.save(pacjent);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        pacjentDAO.deleteById(id);
    }

    @Override
    @Transactional
    public List<KartaPobytu> findAllKartyPobytuOfPacjent(String pesel) {
        return pacjentDAO.findAllKartyPobytuOfPacjent(pesel);
    }

    @Override
    @Transactional
    public void saveKartaPobytuForPacjent(KartaPobytu kartaPobytu, String pesel) {
        pacjentDAO.saveKartaPobytuForPacjent(kartaPobytu, pesel);
    }

    @Override
    @Transactional
    public void deleteKartaPobytuFromPacjent(String pesel, int id_karty) {
        pacjentDAO.deleteKartaPobytuFromPacjent(pesel, id_karty);
    }

    @Override
    @Transactional
    public List<Diagnoza> findAllDiagnozaForKartaPobytu(int id_karty) {
        return pacjentDAO.findAllDiagnozaForKartaPobytu(id_karty);
    }

    @Override
    @Transactional
    public void saveDiagnozaForKartaPobytu(Diagnoza diagnoza, int id_karty) {
        pacjentDAO.saveDiagnozaForKartaPobytu(diagnoza, id_karty);
    }

    @Override
    @Transactional
    public void deleteDiagnozaFromKartaPobytu(int id_karty, int id_diagnozy) {
        pacjentDAO.deleteDiagnozaFromKartaPobytu(id_karty, id_diagnozy);
    }

    @Override
    @Transactional
    public List<Operacja> findAllOperacjeForKartaPobytu(int id_karty) {
        return pacjentDAO.findAllOperacjeForKartaPobytu(id_karty);
    }

    @Override
    @Transactional
    public void saveOperacjaForKartaPobytu(Operacja operacja, int id_karty) {
        pacjentDAO.saveOperacjaForKartaPobytu(operacja, id_karty);
    }

    @Override
    @Transactional
    public void deleteOperacjaFromKartaPobytu(int id_karty, int id_operacji) {
        pacjentDAO.deleteOperacjaFromKartaPobytu(id_karty, id_operacji);
    }

    @Override
    @Transactional
    public List<Recepta> findAllReceptyForKartaPobytu(int id_karty) {
        return pacjentDAO.findAllReceptyForKartaPobytu(id_karty);
    }

    @Override
    @Transactional
    public void saveReceptaForKartaPobytu(Recepta recepta, int id_karty) {
        pacjentDAO.saveReceptaForKartaPobytu(recepta, id_karty);
    }

    @Override
    @Transactional
    public void deleteReceptaFromKartaPobytu(int id_karty, int id_recepty) {
        pacjentDAO.deleteReceptaFromKartaPobytu(id_karty, id_recepty);
    }
}
