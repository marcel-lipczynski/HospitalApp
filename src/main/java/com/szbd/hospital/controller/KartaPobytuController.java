package com.szbd.hospital.controller;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.service.KartaPobytuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public KartaPobytu findById(@PathVariable int id){
        return kartaPobytuService.findById(id);
    }

    @PostMapping("")
    public void saveKarta(@RequestBody KartaPobytu kartaPobytu){
        kartaPobytuService.saveKarta(kartaPobytu);
    }

    @PutMapping("")
    public void updateKarta(@RequestBody KartaPobytu kartaPobytu){
        kartaPobytuService.saveKarta(kartaPobytu);
    }

    @DeleteMapping("/{id}")
    public void deleteKartaById(@PathVariable int id){
        kartaPobytuService.deleteKartaById(id);
    }




}
