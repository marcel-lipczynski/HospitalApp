package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "diagnoza")
public class Diagnoza {

    @Id
    @SequenceGenerator(name = "seq6", sequenceName = "diagnoza_id_diagnozy_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq6")
    @Column(name = "id_diagnozy")
    private int id_diagnozy;


    @Column(name = "data_wystawienia", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date data_wystawienia;


    @Column(name = "opis")
    private String opis;

    @Column(name = "id_lekarza")
    private int id_lekarza;

    @Column(name = "id_karty")
    private int id_karty;


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_karty", insertable = false, updatable = false)
    private KartaPobytu kartaPobytu;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_lekarza", insertable = false, updatable = false)
    private Lekarz lekarz;


    public Diagnoza() {
    }


    public Diagnoza(Date data_wystawienia, String opis, int id_lekarza, int id_karty, KartaPobytu kartaPobytu, Lekarz lekarz) {
        this.data_wystawienia = data_wystawienia;
        this.opis = opis;
        this.id_lekarza = id_lekarza;
        this.id_karty = id_karty;
        this.kartaPobytu = kartaPobytu;
        this.lekarz = lekarz;
    }


}
