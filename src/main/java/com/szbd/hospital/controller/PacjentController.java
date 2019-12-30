package com.szbd.hospital.controller;


import com.szbd.hospital.entity.Pacjent;
import com.szbd.hospital.service.PacjentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{pesel}")
    public Pacjent findById(@PathVariable String pesel){
        return pacjentService.findById(pesel);
    }

    @PostMapping("")
    public void save(@RequestBody Pacjent pacjent){

        pacjentService.save(pacjent);
    }

    @PutMapping("")
    public void update(@RequestBody Pacjent pacjent){
        pacjentService.save(pacjent);
    }

    @DeleteMapping("/{pesel}")
    public void deleteById(@PathVariable String pesel){
        pacjentService.deleteById(pesel);
    }

}
