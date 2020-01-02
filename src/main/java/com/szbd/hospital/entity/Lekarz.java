package com.szbd.hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lekarz")
public class Lekarz {


    @Id
    @SequenceGenerator(name = "seq4", sequenceName = "lekarz_id_lekarza_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq4")
    @Column(name = "id_lekarza")
    private int id_lekarza;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "placa_pod")
    private int placa_pod;


    public Lekarz() {
    }

    public Lekarz(int id_lekarza, String imie, String nazwisko, int placa_pod) {
        this.id_lekarza = id_lekarza;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.placa_pod = placa_pod;
    }



}
