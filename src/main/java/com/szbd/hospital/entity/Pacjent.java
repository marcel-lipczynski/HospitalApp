package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    //One Pacjent has few KartaPobytu
    //CascadeType.ALL -> usuniecie pacjenta = usuniecie jego kart;

    @JsonManagedReference(value = "pacjenci_karty")
    @OneToMany(
            mappedBy = "pacjent",
            cascade = CascadeType.ALL)
    private List<KartaPobytu> kartyPobytu;


    public void addKartaPobytu(KartaPobytu tempKartaPobytu){
        if(kartyPobytu == null){
            kartyPobytu = new ArrayList<>();
        }
        kartyPobytu.add(tempKartaPobytu);
        tempKartaPobytu.setPacjent(this);
    }

}
