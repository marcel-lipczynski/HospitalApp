package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "operacja")
public class Operacja {


    @Id
    @SequenceGenerator(name = "seq7", sequenceName = "operacja_id_operacji_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq7")
    @Column(name = "id_operacji")
    private int id_operacji;


    @Column(name = "nazwa_operacji")
    private String nazwa_operacji;


    @Column(name = "termin", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date termin;


    @Column(name = "id_lekarza")
    private int id_lekarza;

    @Column(name = "id_karty")
    private int id_karty;


    @JsonBackReference(value = "karty_operacje")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_karty", insertable = false, updatable = false)
    private KartaPobytu kartaPobytu;

    @JsonBackReference(value = "lekarze_operacje")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_lekarza", insertable = false, updatable = false)
    private Lekarz lekarz;


    public Operacja() {
    }

    public Operacja(String nazwa_operacji, Date termin, int id_lekarza, int id_karty, KartaPobytu kartaPobytu, Lekarz lekarz) {
        this.nazwa_operacji = nazwa_operacji;
        this.termin = termin;
        this.id_lekarza = id_lekarza;
        this.id_karty = id_karty;
        this.kartaPobytu = kartaPobytu;
        this.lekarz = lekarz;
    }
}
