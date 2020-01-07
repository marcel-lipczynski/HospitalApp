package com.szbd.hospital.controller;


import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Specjalizacje;
import com.szbd.hospital.service.LekarzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lekarze")
@CrossOrigin(origins = "http://localhost:4200")
public class LekarzController {

    private LekarzService lekarzService;

    @Autowired
    public LekarzController(LekarzService lekarzService) {
        this.lekarzService = lekarzService;
    }

    @GetMapping("")
    public List<Lekarz> findAll() {
        return lekarzService.findAll();
    }

    @GetMapping("/{id}")
    public Lekarz findById(@PathVariable int id) {
        return lekarzService.findById(id);
    }

    @GetMapping("/{id_lekarza}/specjalizacje")
    public List<Specjalizacje> findAllSpecjalizacjeOfLekarz(@PathVariable int id_lekarza) {
        return lekarzService.findAllSpecjalizacjeOfLekarz(id_lekarza);
    }


    @PostMapping("")
    public void saveLekarz(@RequestBody Lekarz lekarz) {
        lekarzService.saveLekarz(lekarz);
    }

    @PostMapping("/{id_lekarza}/specjalizacje/{nazwa_specjalizacji}")
    public void saveSpecjalizacjaForLekarz(@PathVariable int id_lekarza, @PathVariable String nazwa_specjalizacji) {
        lekarzService.saveSpecjalizacjaForLekarz(id_lekarza, nazwa_specjalizacji.toUpperCase());
    }


    @DeleteMapping("/{id}")
    public void deleteLekarzById(@PathVariable int id) {
        lekarzService.deleteLekarzById(id);
    }

    @DeleteMapping("/{id_lekarza}/specjalizacje/{nazwa_specjalizacji}")
    public void deleteSpecjalizacjaFromLekarz(@PathVariable int id_lekarza, @PathVariable String nazwa_specjalizacji) {
        lekarzService.deleteSpecjalizacjaFromLekarz(id_lekarza, nazwa_specjalizacji.toUpperCase());
    }
}
