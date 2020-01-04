package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "lek")
public class Lek {

    @Id
    @Column(name = "nazwa_leku")
    private String nazwa_leku;

    @Column(name = "rodzaj_leku")
    private String rodzaj_leku;


    //MAPOWANIE ManyToMany - Recepta
    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "leki")
    private List<Recepta> recepty;

}
