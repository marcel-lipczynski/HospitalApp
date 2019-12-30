package com.szbd.hospital.controller;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.service.KartaPobytuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("karty")
public class KartaPobytuController {

    private KartaPobytuService kartaPobytuService;

    @Autowired
    public KartaPobytuController(KartaPobytuService kartaPobytuService) {
        this.kartaPobytuService = kartaPobytuService;
    }

    @GetMapping("")
    public List<KartaPobytu> findAll(){
        return kartaPobytuService.findAll();
    }




}
