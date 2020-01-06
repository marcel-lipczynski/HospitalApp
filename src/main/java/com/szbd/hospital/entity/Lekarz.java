package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "lekarze_specjalizacjelekarskie",
            joinColumns = @JoinColumn(name = "id_lekarza"),
            inverseJoinColumns = @JoinColumn(name = "nazwa_specjalizacji")
    )
    private List<Specjalizacje> specjalizacje;


    @JsonManagedReference
    @OneToMany(
            mappedBy = "lekarz",
            cascade = CascadeType.ALL)
    private List<Recepta> recepty;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "lekarz",
            cascade = CascadeType.ALL)
    private List<Operacja> operacje;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "lekarz",
            cascade = CascadeType.ALL)
    private List<Diagnoza> diagnozy;


    public Lekarz() {
    }


    //update constructor!!!!
    public Lekarz(String imie, String nazwisko, int placa_pod, List<Specjalizacje> specjalizacje, List<Recepta> recepty) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.placa_pod = placa_pod;
        this.specjalizacje = specjalizacje;
        this.recepty = recepty;
    }

    public void addSpecjalizacja(Specjalizacje specjalizacja) {
        if (specjalizacje == null) {
            specjalizacje = new ArrayList<>();
        }
        specjalizacje.add(specjalizacja);
    }

    public void removeSpecjalizacje(Specjalizacje specjalizacja) {
        specjalizacje.remove(specjalizacja);
        specjalizacja.getLekarze().remove(this);
    }


    public void addRecepta(Recepta recepta) {
        if (recepty == null) {
            recepty = new ArrayList<>();
        }
        recepty.add(recepta);
        recepta.setLekarz(this);
    }

    public void addOperacja(Operacja operacja) {
        if (operacje == null) {
            operacje = new ArrayList<>();
        }
        operacje.add(operacja);
        operacja.setLekarz(this);
    }

    public void addDiagnoza(Diagnoza diagnoza) {
        if (diagnozy == null) {
            diagnozy = new ArrayList<>();
        }
        diagnozy.add(diagnoza);
        diagnoza.setLekarz(this);
    }


}
