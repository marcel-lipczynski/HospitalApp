package com.szbd.hospital.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    //mapowanie OneToMany LEKARZ

    //mapowanie OneToMany KARTA_POBYTU


}
