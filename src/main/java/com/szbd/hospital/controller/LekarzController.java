package com.szbd.hospital.controller;


import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.service.LekarzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lekarz")
public class LekarzController {

    private LekarzService lekarzService;

    @Autowired
    public LekarzController(LekarzService lekarzService) {
        this.lekarzService = lekarzService;
    }

    @GetMapping("")
    public List<Lekarz> findAll(){
        return lekarzService.findAll();
    }

    @GetMapping("/{id}")
    public Lekarz findById(@PathVariable int id){
        return lekarzService.findById(id);
    }

    @PostMapping("")
    public void saveLekarz(@RequestBody Lekarz lekarz){
        lekarzService.saveLekarz(lekarz);
    }

    @DeleteMapping("/{id}")
    public void deleteLekarzById(@PathVariable int id){
        lekarzService.deleteLekarzById(id);
    }



}
