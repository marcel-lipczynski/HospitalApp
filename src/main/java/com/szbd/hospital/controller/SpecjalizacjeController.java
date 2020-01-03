package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Specjalizacje;
import com.szbd.hospital.service.SpecjalizacjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specjalizacje")
public class SpecjalizacjeController {

    private SpecjalizacjeService specjalizacjeService;

    @Autowired
    public SpecjalizacjeController(SpecjalizacjeService specjalizacjeService) {
        this.specjalizacjeService = specjalizacjeService;
    }

    @GetMapping("")
    public List<Specjalizacje> findAll() {
        return specjalizacjeService.findAll();
    }

   @GetMapping("/{id}")
    public Specjalizacje findById(@PathVariable String id) {
        return specjalizacjeService.findById(id.toUpperCase());
    }

    @PostMapping("")
    public void saveSpecjalizacje(@RequestBody Specjalizacje specjalizacje) {
        specjalizacjeService.saveSpecjalizacje(specjalizacje);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecjalizacjeById(@PathVariable String id) {
        specjalizacjeService.deleteSpecjalizacjeById(id.toUpperCase());
    }





}
