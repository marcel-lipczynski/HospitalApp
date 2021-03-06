package com.szbd.hospital.controller;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import com.szbd.hospital.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200")
public class SalaController {

    private SalaService salaService;

    @Autowired
    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping("")
    public List<Sala> findAll() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    public Sala findById(@PathVariable int id) {
        return salaService.findById(id);
    }

    @GetMapping("/{id}/pielegniarki")
    public List<Pielegniarka> findAllPielegniarkaOfSala(@PathVariable int id) {
        return salaService.findAllPielegniarkaOfSala(id);
    }

    @PostMapping("")
    public void saveSala(@RequestBody Sala sala) {
        salaService.saveSala(sala);
    }

    @PostMapping("/{nr_sali}/pielegniarki/{idPielegniarki}")
    public void saveSalaWithIdPielegniarki(@PathVariable int idPielegniarki,
                                           @PathVariable int nr_sali) {
        salaService.saveSalaWithIdPielegniarki(idPielegniarki,nr_sali);
    }

    @DeleteMapping("/{nr_sali}/pielegniarki/{idPielegniarki}")
    public void deletePielegniarkaFromSala(@PathVariable int idPielegniarki,
                                           @PathVariable int nr_sali) {
        salaService.deletePielegniarkaFromSala(idPielegniarki, nr_sali);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        salaService.deleteById(id);
    }

    @GetMapping("/{nr_sali}/karty")
    public List<KartaPobytu> findActiveKartyForSala(@PathVariable int nr_sali){
        return salaService.findActiveKartyForSala(nr_sali);
    }

    @GetMapping("/available")
    public List<Sala> findAvailableSale(){
        return salaService.findAllAvailableSale();
    }

    @GetMapping("/{nr_sali}/pielegniarki/available")
    public List<Pielegniarka> findAvailablePielegniarkiForSala(@PathVariable int nr_sali) {
        return salaService.findAvailablePielegniarkiForSala(nr_sali);
    }


}

