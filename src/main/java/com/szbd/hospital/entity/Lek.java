package com.szbd.hospital.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lek")
public class Lek {

    @Id
    @Column(name = "nazwa_leku")
    private String nazwa_leku;

    @Column(name = "rodzaj_leku")
    private String rodzaj_leku;


    //MAPOWANIE ManyToMany - LEK
    
}
