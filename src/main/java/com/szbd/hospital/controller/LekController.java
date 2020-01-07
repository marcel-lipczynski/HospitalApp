package com.szbd.hospital.controller;

import com.szbd.hospital.entity.Lek;
import com.szbd.hospital.service.LekService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leki")
@CrossOrigin(origins = "http://localhost:4200")
public class LekController {

    private LekService lekService;

    @Autowired
    public LekController(LekService lekService) {
        this.lekService = lekService;
    }

//
//    List<Lek> findAll()
//    Lek findLekById(String id)
//    void saveLek(Lek lek)
//    void deleteLekById(String id)


    @GetMapping("")
    public List<Lek> findAll(){
        return lekService.findAll();
    }

    @GetMapping("/{id}")
    public Lek findLekById(@PathVariable String id){
        return lekService.findLekById(id.toUpperCase());
    }

    @PostMapping("")
    public void saveLek(@RequestBody Lek lek){
        lek.setNazwa_leku(lek.getNazwa_leku().toUpperCase());
        lekService.saveLek(lek);
    }

    @DeleteMapping("/{id}")
    public void deleteLekById(@PathVariable String id){
        lekService.deleteLekById(id.toUpperCase());
    }



}
