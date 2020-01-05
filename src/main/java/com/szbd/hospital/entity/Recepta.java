package com.szbd.hospital.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "recepta")
public class Recepta {

    //recepta_id_recepty_seq
    @Id
    @SequenceGenerator(name = "seq5", sequenceName = "recepta_id_recepty_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq5")
    @Column(name = "id_recepty")
    private int id_recepty;

    @Column(name = "data_wystawienia", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date data_wystawienia;

    @Column(name = "id_lekarza")
    private int id_lekarza;

    @Column(name = "id_karty")
    private int id_karty;


    //mapowanie ManyToMany LEK
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "recepty_leki",
            joinColumns = @JoinColumn(name = "id_recepty"),
            inverseJoinColumns = @JoinColumn(name = "nazwa_leku")
    )
    private List<Lek> leki;



    //mapowanie ManyToOne LEKARZ
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_lekarza", insertable = false, updatable = false)
    private Lekarz lekarz;


    //mapowanie ManyToOne KARTA_POBYTU
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_karty", insertable = false, updatable = false)
    private KartaPobytu kartaPobytu;



    //methods for adding and removing from leki list
    public void addLek(Lek lek) {
        if (leki == null) {
            leki = new ArrayList<>();
        }
        leki.add(lek);
    }

    public void removeLek(Lek lek) {
        leki.remove(lek);
        lek.getRecepty().remove(this);
    }


}
