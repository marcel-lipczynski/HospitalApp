package com.szbd.hospital.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pielegniarka")
public class Pielegniarka {

    @Id
    @SequenceGenerator(name = "seq2", sequenceName = "pielegniarka_id_pielegniarki", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
    @Column(name = "id_pielegniarki", nullable = false, unique = true)
    private int id_pielegniarki;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "placa")
    private int placa;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "pielegniarki_sale",
            joinColumns = @JoinColumn(name = "id_pielegniarki"),
            inverseJoinColumns = @JoinColumn(name = "nr_sali")
    )
    private List<Sala> sale;


    public Pielegniarka() {
    }

    public Pielegniarka(String imie, String nazwisko, int placa, List<Sala> sale) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.placa = placa;
        this.sale = sale;
    }

    public void addSala(Sala sala){
        if(sale == null){
            sale = new ArrayList<>();
        }
        sale.add(sala);
    }





}
