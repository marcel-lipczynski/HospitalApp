package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "karta_pobytu")
public class KartaPobytu {

    @Id
    @SequenceGenerator(name = "seq1", sequenceName = "karta_pobytu_id_karty_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
    @Column(name = "id_karty", nullable = false, unique = true)
    private int id_karty;

    @Column(name = "data_przyjecia", nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date data_przyjecia;

    @Column(name = "godzina_przyjecia",nullable = false)
    private String godzina_przyjecia;

    @Column(name = "data_wypisu")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date data_wypisu;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "nr_sali")
    private int nr_sali;


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "pesel", insertable = false, updatable = false)
    private Pacjent pacjent;

    //TODO
    //Add mapping for SALA (One to many)

    //WAZNE!
    //Zmienic w bazie klucze unikatowe dla godziny,daty i peselu. Poniewaz
    //Podczas updatowania daty wypisu nie da sie tego zrobic bo zapisuje sie
    //te pola z takimi samymi wartosciami. Trzeba to w backu kontrolowac i tyle :)
}
