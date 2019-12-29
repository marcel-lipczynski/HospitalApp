package com.szbd.hospital.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pacjent")
public class Pacjent {

    @Id
    @Column(name = "pesel", nullable = false)
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Pacjent{" +
                "pesel=" + pesel +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
