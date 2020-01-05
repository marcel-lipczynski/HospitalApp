package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "operacja")
public class Operacja {


//    operacja_id_operacji_seq

    @Id
    @SequenceGenerator(name = "seq7", sequenceName = "operacja_id_operacji_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq7")
    @Column(name = "id_diagnozy")
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



}
