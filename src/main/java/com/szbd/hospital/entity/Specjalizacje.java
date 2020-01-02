package com.szbd.hospital.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "specjalizacje")
public class Specjalizacje {


    @Id
    @Column(name = "nazwa_specjalizacji")
    private String nazwa_specjalizacji;

    @Column(name = "placa_min")
    private int placa_min;

    @Column(name = "placa_max")
    private int placa_max;

    public Specjalizacje() {
    }

    public Specjalizacje(String nazwa_specjalizacji, int placa_min, int placa_max) {
        this.nazwa_specjalizacji = nazwa_specjalizacji;
        this.placa_min = placa_min;
        this.placa_max = placa_max;
    }


}
