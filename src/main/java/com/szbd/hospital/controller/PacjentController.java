package com.szbd.hospital.controller;


import com.szbd.hospital.entity.*;
import com.szbd.hospital.service.PacjentService;
import oracle.jdbc.proxy.annotation.Post;
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
    public List<Pacjent> findAll() {
        return pacjentService.findAll();
    }

    @GetMapping("/{pesel}")
    public Pacjent findById(@PathVariable String pesel) {
        return pacjentService.findById(pesel);
    }

    @PostMapping("")
    public void save(@RequestBody Pacjent pacjent) {
        pacjentService.save(pacjent);
    }

    @DeleteMapping("/{pesel}")
    public void deleteById(@PathVariable String pesel) {
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

    //diagnozy

    @GetMapping("/{pesel}/karty/{id_karty}/diagnozy")
    public List<Diagnoza> findAllDiagnozaForKartaPobytu(@PathVariable String pesel, @PathVariable int id_karty) {
        return pacjentService.findAllDiagnozaForKartaPobytu(id_karty);
    }

    @PostMapping("/{pesel}/karty/{id_karty}/diagnozy")
    public void saveDiagnozaForKartaPobytu(@RequestBody Diagnoza diagnoza, @PathVariable int id_karty, @PathVariable String pesel) {
        pacjentService.saveDiagnozaForKartaPobytu(diagnoza, id_karty);
    }

    @DeleteMapping("/{pesel}/karty/{id_karty}/diagnozy/{id_diagnozy}")
    public void deleteDiagnozaFromKartaPobytu(@PathVariable String pesel, @PathVariable int id_karty, @PathVariable int id_diagnozy) {
        pacjentService.deleteDiagnozaFromKartaPobytu(id_karty, id_diagnozy);
    }


    //operacje

    @GetMapping("/{pesel}/karty/{id_karty}/operacje")
    public List<Operacja> findAllOperacjeForKartaPobytu(@PathVariable String pesel, @PathVariable int id_karty) {
        return pacjentService.findAllOperacjeForKartaPobytu(id_karty);
    }

    @PostMapping("/{pesel}/karty/{id_karty}/operacje")
    public void saveOperacjaForKartaPobytu(@RequestBody Operacja operacja, @PathVariable int id_karty, @PathVariable String pesel) {
        pacjentService.saveOperacjaForKartaPobytu(operacja, id_karty);
    }

    @DeleteMapping("/{pesel}/karty/{id_karty}/operacje/{id_operacji}")
    public void deleteOperacjaFromKartaPobytu(@PathVariable String pesel, @PathVariable int id_karty, @PathVariable int id_operacji) {
        pacjentService.deleteOperacjaFromKartaPobytu(id_karty, id_operacji);
    }

    //recepty

    @GetMapping("/{pesel}/karty/{id_karty}/recepty")
    public List<Recepta> findAllReceptyForKartaPobytu(@PathVariable String pesel, @PathVariable int id_karty) {
        return pacjentService.findAllReceptyForKartaPobytu(id_karty);
    }

    @PostMapping("/{pesel}/karty/{id_karty}/recepty")
    public void saveReceptaForKartaPobytu(@RequestBody Recepta recepta, @PathVariable int id_karty, @PathVariable String pesel) {
        pacjentService.saveReceptaForKartaPobytu(recepta, id_karty);
    }

    @DeleteMapping("/{pesel}/karty/{id_karty}/recepty/{id_recepty}")
    public void deleteReceptaFromKartaPobytu(@PathVariable String pesel, @PathVariable int id_karty, @PathVariable int id_recepty) {
        pacjentService.deleteReceptaFromKartaPobytu(id_karty, id_recepty);
    }

}
