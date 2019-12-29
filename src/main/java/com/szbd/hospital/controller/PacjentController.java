package com.szbd.hospital.controller;


import com.szbd.hospital.entity.Pacjent;
import com.szbd.hospital.service.PacjentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pacjent")
public class PacjentController {

    private PacjentService pacjentService;

    @Autowired
    public PacjentController(PacjentService pacjentService) {
        this.pacjentService = pacjentService;
    }

    @GetMapping("")
    public List<Pacjent> findAll(){
        return pacjentService.findAll();
    }

}
