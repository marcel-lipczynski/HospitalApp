package com.szbd.hospital.entity;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "pacjent")
public class Pacjent {

    @Id
    @Column(name = "pesel", nullable = false, unique = true)
    private String pesel;

    @Column(name = "imie", nullable = false)
    private String imie;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    public Pacjent() {
    }

    public Pacjent(String pesel, String imie, String nazwisko) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

}
