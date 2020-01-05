package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Recepta;
import com.szbd.hospital.service.ReceptaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recepta")
public class ReceptaController {

    private ReceptaService receptaService;

    @Autowired
    public ReceptaController(ReceptaService receptaService) {
        this.receptaService = receptaService;
    }

    @GetMapping("")
    public List<Recepta> findAll(){
        return receptaService.findAll();
    }

    @GetMapping("/{id}")
    public Recepta findById(@PathVariable int id){
        return receptaService.findById(id);
    }

    @PostMapping("")
    public void saveRecepta(@RequestBody Recepta recepta){
        receptaService.saveRecepta(recepta);
    }

    @DeleteMapping("/{id}")
    public void deleteReceptaById(@PathVariable int id){
        receptaService.deleteReceptaById(id);
    }




}
