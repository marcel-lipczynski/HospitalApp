package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Diagnoza;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.service.DiagnozaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnozy")
@CrossOrigin(origins = "http://localhost:4200")
public class DiagnozaController {

    private DiagnozaService diagnozaService;

    @Autowired
    public DiagnozaController(DiagnozaService diagnozaService) {
        this.diagnozaService = diagnozaService;
    }

    @GetMapping("")
    public List<Diagnoza> findAll(){
        return diagnozaService.findAll();
    }

    @GetMapping("/{id}")
    public Diagnoza findDiagnozaById(@PathVariable int id){
        return diagnozaService.findDiagnozaById(id);
    }

    @PostMapping("")
    public void saveDiagnoza(@RequestBody Diagnoza diagnoza){
        diagnozaService.saveDiagnoza(diagnoza);
    }

    @DeleteMapping("/{id}")
    public void deleteDiagnozaById(@PathVariable int id){
        diagnozaService.deleteDiagnozaById(id);
    }

    @GetMapping("/{id_karty}/lekarze")
    public List<Lekarz> getAvailableLekarze(@PathVariable int id_karty){
        return diagnozaService.getAvailableLekarze(id_karty);
    }


}
