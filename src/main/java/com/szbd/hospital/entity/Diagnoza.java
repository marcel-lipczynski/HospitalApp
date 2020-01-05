package com.szbd.hospital.entity;

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


}
