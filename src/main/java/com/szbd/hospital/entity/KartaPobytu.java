package com.szbd.hospital.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "karta_pobytu")
public class KartaPobytu {

    @Id
    @SequenceGenerator(name = "seq1", sequenceName = "karta_pobytu_id_karty_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
    @Column(name = "id_karty", nullable = false, unique = true)
    private int id_karty;

    @Column(name = "data_przyjecia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_przyjecia;

    @Column(name = "godzina_przyjecia",unique = true)
    private String godzina_przyjecia;

    @Column(name = "data_wypisu")
    @Temporal(TemporalType.DATE)
    private Date data_wypisu;

    //TODO
    //Add mapping for SALA and PESEL
}
