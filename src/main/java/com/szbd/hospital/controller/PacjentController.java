package com.szbd.hospital.controller;


import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pacjent;
import com.szbd.hospital.service.PacjentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacjenci")
@CrossOrigin(origins = "http://localhost:4200")
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

    @DeleteMapping("/{pesel}")
    public void deleteById(@PathVariable String pesel){
        pacjentService.deleteById(pesel);
    }

    @GetMapping("/{pesel}/karty")
    public List<KartaPobytu> findAllKartyPobytuOfPacjent(@PathVariable String pesel) {
        return pacjentService.findAllKartyPobytuOfPacjent(pesel);
    }

    @PostMapping("/{pesel}/karty")
    public void saveKartaPobytuForPacjent(@RequestBody KartaPobytu kartaPobytu, @PathVariable String pesel) {
        pacjentService.saveKartaPobytuForPacjent(kartaPobytu, pesel);
    }

    @DeleteMapping("/{pesel}/karty/{id_karty}")
    public void deleteKartaPobytuFromPacjent(@PathVariable String pesel, @PathVariable int id_karty) {
        pacjentService.deleteKartaPobytuFromPacjent(pesel, id_karty);
    }



}
