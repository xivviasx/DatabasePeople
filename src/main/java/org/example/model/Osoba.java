package org.example.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imie;
    private String nazwisko;
    private ZonedDateTime dataUrodzenia;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "numer_telefonu_id")
    private NumerTelefonu numerTelefonu;

    @ManyToOne
    @JoinColumn(name = "adres_id")
    private Adres adres;

    @ManyToOne
    @JoinColumn(name = "praca_id")
    private Praca praca;


    //get set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public ZonedDateTime getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(ZonedDateTime dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public NumerTelefonu getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(NumerTelefonu numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Praca getPraca() {
        return praca;
    }

    public void setPraca(Praca praca) {
        this.praca = praca;
    }

    // getters and setters
}
