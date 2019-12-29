package com.szbd.hospital.dao;

import com.szbd.hospital.entity.Pacjent;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PacjentDAO {

    List<Pacjent> findAll();

}
