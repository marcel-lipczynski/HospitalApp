package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Sala {

    @Id
    @SequenceGenerator(name = "seq3", sequenceName = "sala_nr_sali_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
    @Column(name = "nr_sali", nullable = false, unique = true)
    private int nr_sali;

    @Column(name = "pojemnosc")
    private int pojemnosc;

    @Column(name = "oddzial")
    private String oddzial;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "pielegniarki_sale",
            joinColumns = @JoinColumn(name = "nr_sali"),
            inverseJoinColumns = @JoinColumn(name = "id_pielegniarki")
    )
    private List<Pielegniarka> pielegniarki;


    public Sala() {
    }

    public Sala(int pojemnosc, String oddzial, List<Pielegniarka> pielegniarki) {
        this.pojemnosc = pojemnosc;
        this.oddzial = oddzial;
        this.pielegniarki = pielegniarki;
    }

    public void addPielegniarka(Pielegniarka pielegniarka){
        if(pielegniarki == null){
            pielegniarki = new ArrayList<>();
        }
        pielegniarki.add(pielegniarka);
    }

    public void removePielegniarka(Pielegniarka pielegniarka){
        pielegniarki.remove(pielegniarka);
        pielegniarka.getSale().remove(this);
    }


}
