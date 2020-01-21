package com.szbd.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NamedStoredProcedureQuery(
        name = "DodajWypis",
        procedureName = "DodajWypis",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "id_kartyPobytu")
        }
)


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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date data_przyjecia;

    @Column(name = "godzina_przyjecia", nullable = false)
    private String godzina_przyjecia;

    @Column(name = "data_wypisu")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    private Date data_wypisu;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "nr_sali")
    private int nr_sali;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "kartypobytu_lekarze",
            joinColumns = @JoinColumn(name = "id_karty"),
            inverseJoinColumns = @JoinColumn(name = "id_lekarza")
    )
    private List<Lekarz> lekarze;


    @JsonBackReference(value = "pacjenci_karty")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "pesel", insertable = false, updatable = false)
    private Pacjent pacjent;

    @JsonBackReference(value = "sale_karty")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "nr_sali", insertable = false, updatable = false)
    private Sala sala;


    @JsonManagedReference(value = "karty_recepty")
    @OneToMany(
            mappedBy = "kartaPobytu",
            cascade = CascadeType.ALL)
    private List<Recepta> recepty;

    @JsonManagedReference(value = "karty_operacje")
    @OneToMany(
            mappedBy = "kartaPobytu",
            cascade = CascadeType.ALL)
    private List<Operacja> operacje;

    @JsonManagedReference(value = "karty_diagnozy")
    @OneToMany(
            mappedBy = "kartaPobytu",
            cascade = CascadeType.ALL)
    private List<Diagnoza> diagnozy;


    public KartaPobytu() {
    }

    public KartaPobytu(Date data_przyjecia, String godzina_przyjecia, Date data_wypisu, String pesel, int nr_sali) {
        this.data_przyjecia = data_przyjecia;
        this.godzina_przyjecia = godzina_przyjecia;
        this.data_wypisu = data_wypisu;
        this.pesel = pesel;
        this.nr_sali = nr_sali;
    }

    public KartaPobytu(Date data_przyjecia, String godzina_przyjecia, Date data_wypisu, String pesel, int nr_sali,
                       List<Lekarz> lekarze, Pacjent pacjent, Sala sala, List<Recepta> recepty,
                       List<Operacja> operacje, List<Diagnoza> diagnozy) {
        this.data_przyjecia = data_przyjecia;
        this.godzina_przyjecia = godzina_przyjecia;
        this.data_wypisu = data_wypisu;
        this.pesel = pesel;
        this.nr_sali = nr_sali;
        this.lekarze = lekarze;
        this.pacjent = pacjent;
        this.sala = sala;
        this.recepty = recepty;
        this.operacje = operacje;
        this.diagnozy = diagnozy;
    }


    //TODO


    //WAZNE!
    //Zmienic w bazie klucze unikatowe dla godziny,daty i peselu. Poniewaz
    //Podczas updatowania daty wypisu nie da sie tego zrobic bo zapisuje sie
    //te pola z takimi samymi wartosciami. Trzeba to w backu kontrolowac i tyle :)


    //metoda dodajaca do listy nowa recepta
    //do recepty karte pobytu!
    public void addLekarz(Lekarz lekarz) {
        if (lekarze == null) {
            lekarze = new ArrayList<>();
        }
        lekarze.add(lekarz);
    }

    public void removeLekarz(Lekarz lekarz) {
        lekarze.remove(lekarz);
        lekarz.getKartyPobytu().remove(this);
    }


    public void addRecepta(Recepta recepta) {
        if (recepty == null) {
            recepty = new ArrayList<>();
        }
        recepty.add(recepta);
        recepta.setKartaPobytu(this);
    }

    public void addOperacja(Operacja operacja) {
        if (operacje == null) {
            operacje = new ArrayList<>();
        }
        operacje.add(operacja);
        operacja.setKartaPobytu(this);
    }

    public void addDiagnoza(Diagnoza diagnoza) {
        if (diagnozy == null) {
            diagnozy = new ArrayList<>();
        }
        diagnozy.add(diagnoza);
        diagnoza.setKartaPobytu(this);
    }


}
