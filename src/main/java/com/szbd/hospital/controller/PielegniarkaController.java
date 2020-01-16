package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Pielegniarka;
import com.szbd.hospital.entity.Sala;
import com.szbd.hospital.service.PielegniarkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pielegniarki")
@CrossOrigin(origins = "http://localhost:4200")
public class PielegniarkaController {

    private PielegniarkaService pielegniarkaService;

    @Autowired
    public PielegniarkaController(PielegniarkaService pielegniarkaService) {
        this.pielegniarkaService = pielegniarkaService;
    }


    @GetMapping("")
    public List<Pielegniarka> findAll() {
        return pielegniarkaService.findAll();
    }

    @GetMapping("/{id}")
    public Pielegniarka findById(@PathVariable int id) {
        return pielegniarkaService.findById(id);
    }

    //zapisz pielegniarke bez informacji o sali!
    @PostMapping("")
    public void savePielegniarka(@RequestBody Pielegniarka pielegniarka) {
        pielegniarkaService.savePielegniarka(pielegniarka);
    }

    //w detailsach pobierz wszystkie sale
    @GetMapping("/{idPielegniarki}/sale")
    public List<Sala> findAllSalaOfPielegniarka(@PathVariable int idPielegniarki) {
        return pielegniarkaService.findAllSalaOfPielegniarka(idPielegniarki);
    }

    //podajemy jako parametr nr_sali istniejacej

    @PostMapping("/{idPielegniarki}/sale/{nr_sali}")
    public void savePielegniarkaWitNrSali(@PathVariable int idPielegniarki, @PathVariable int nr_sali) {
        pielegniarkaService.savePielegniarkaWitNrSali(idPielegniarki, nr_sali);
    }

    @DeleteMapping("/{idPielegniarki}/sale/{nrSali}")
    public void deleteSalaFromPielegniarka(@PathVariable int idPielegniarki,@PathVariable int nrSali) {
        pielegniarkaService.deleteSalaFromPielegniarka(idPielegniarki,nrSali);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        pielegniarkaService.deleteById(id);
    }

    @GetMapping("/{id_pielegniarki}/sale/available")
    public List<Sala> findAvailableSaleForPielegniarka(@PathVariable int id_pielegniarki) {
        return pielegniarkaService.findAvailableSaleForPielegniarka(id_pielegniarki);
    }


}
