package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Lek;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.entity.Recepta;
import com.szbd.hospital.service.ReceptaService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recepty")
@CrossOrigin(origins = "http://localhost:4200")
public class ReceptaController {

    private ReceptaService receptaService;

    @Autowired
    public ReceptaController(ReceptaService receptaService) {
        this.receptaService = receptaService;
    }

    @GetMapping("")
    public List<Recepta> findAll() {
        return receptaService.findAll();
    }

    @GetMapping("/{id}")
    public Recepta findById(@PathVariable int id) {
        return receptaService.findById(id);
    }

    @GetMapping("/{id_recepty}/leki")
    public List<Lek> findLekiOfRecepta(@PathVariable int id_recepty) {
        return receptaService.findLekiOfRecepta(id_recepty);
    }

    @PostMapping("")
    public void saveRecepta(@RequestBody Recepta recepta) {
        receptaService.saveRecepta(recepta);
    }

    @PostMapping("/{id_recepty}/leki/{nazwa_leku}")
    public void addLekToRecepta(@PathVariable int id_recepty, @PathVariable String nazwa_leku) {
        receptaService.addLekToRecepta(id_recepty, nazwa_leku.toUpperCase());
    }

    @DeleteMapping("/{id}")
    public void deleteReceptaById(@PathVariable int id) {
        receptaService.deleteReceptaById(id);
    }


    @DeleteMapping("/{id_recepty}/leki/{nazwa_leku}")
    public void deleteLekFromRecepta(@PathVariable int id_recepty, @PathVariable String nazwa_leku) {
        receptaService.deleteLekFromRecepta(id_recepty, nazwa_leku.toUpperCase());
    }

    @GetMapping("{id_karty}/lekarze")
    public List<Lekarz> getAvailableLekarze(@PathVariable int id_karty){
        return receptaService.getAvailableLekarze(id_karty);
    }


}
