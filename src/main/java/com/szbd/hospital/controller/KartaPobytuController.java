package com.szbd.hospital.controller;

import com.szbd.hospital.entity.KartaPobytu;
import com.szbd.hospital.entity.Lekarz;
import com.szbd.hospital.service.KartaPobytuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/karty")
public class KartaPobytuController {

    private KartaPobytuService kartaPobytuService;

    @Autowired
    public KartaPobytuController(KartaPobytuService kartaPobytuService) {
        this.kartaPobytuService = kartaPobytuService;
    }

    @GetMapping("")
    public List<KartaPobytu> findAll() {
        return kartaPobytuService.findAll();
    }

    @GetMapping("/{id}")
    public KartaPobytu findById(@PathVariable int id) {
        return kartaPobytuService.findById(id);
    }

    @GetMapping("/{id_karty}/lekarz")
    public List<Lekarz> findLekarzeOnKartaPobytu(@PathVariable int id_karty) {
        return kartaPobytuService.findLekarzeOnKartaPobytu(id_karty);
    }


    @PostMapping("")
    public void saveKarta(@RequestBody KartaPobytu kartaPobytu) {
        kartaPobytuService.saveKarta(kartaPobytu);
    }


    @PostMapping("/{id_karty}/lekarz/{id_lekarza}")
    public void addLekarzToKartaPobytu(@PathVariable int id_karty, @PathVariable int id_lekarza) {
        kartaPobytuService.addLekarzToKartaPobytu(id_karty, id_lekarza);
    }


    @DeleteMapping("/{id_karty}/lekarz/{id_lekarza}")
    public void deleteLekarzFromKarta(@PathVariable int id_karty, @PathVariable int id_lekarza) {
        kartaPobytuService.deleteLekarzFromKarta(id_karty, id_lekarza);
    }


    @DeleteMapping("/{id}")
    public void deleteKartaById(@PathVariable int id) {
        kartaPobytuService.deleteKartaById(id);
    }


}
