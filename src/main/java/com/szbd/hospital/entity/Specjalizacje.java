package com.szbd.hospital.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "specjalizacje")
public class Specjalizacje {


    @Id
    @Column(name = "nazwa_specjalizacji", nullable = false, unique = true)
    private String nazwa_specjalizacji;

    @Column(name = "placa_min")
    private int placa_min;

    @Column(name = "placa_max")
    private int placa_max;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "specjalizacje")
    private List<Lekarz> lekarze;


    public Specjalizacje() {
    }

    public Specjalizacje(String nazwa_specjalizacji, int placa_min, int placa_max, List<Lekarz> lekarze) {
        this.nazwa_specjalizacji = nazwa_specjalizacji;
        this.placa_min = placa_min;
        this.placa_max = placa_max;
        this.lekarze = lekarze;
    }

    public void addLekarz(Lekarz lekarz) {
        if (lekarze == null) {
            lekarze = new ArrayList<>();
        }
        lekarze.add(lekarz);
    }

    public void removeLekarz(Lekarz lekarz) {
        lekarze.remove(lekarz);
        lekarz.getSpecjalizacje().remove(this);
    }


}
