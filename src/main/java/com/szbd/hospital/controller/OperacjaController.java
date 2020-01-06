package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Operacja;
import com.szbd.hospital.service.OperacjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operacja")
public class OperacjaController {

    private OperacjaService operacjaService;

    @Autowired
    public OperacjaController(OperacjaService operacjaService) {
        this.operacjaService = operacjaService;
    }

    @GetMapping("")
    public List<Operacja> findAll(){
        return operacjaService.findAll();
    }

    @GetMapping("/{id}")
    public Operacja findOperacjaById(@PathVariable int id){
        return operacjaService.findOperacjaById(id);
    }

    @PostMapping("")
    public void saveOperacja(@RequestBody Operacja operacja){
        operacjaService.saveOperacja(operacja);
    }


    @DeleteMapping("/{id}")
    public void deleteOperacjaById(@PathVariable int id){
        operacjaService.deleteOperacjaById(id);
    }
    
}
