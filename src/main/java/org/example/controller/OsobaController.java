package org.example.controller;

import org.example.model.Adres;
import org.example.model.Osoba;
import org.example.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//ODBIERA ZAPYTANIA GET IT. I WYRZUCA ERRORY LUB CO SN EKRAN STRONY
//NIE WYRZUCA NICZEGO BEZPOSREDNIO DO BAZY, ZOSTAWIA TO SERWISOWI
@RestController
@RequestMapping("/osoba")
public class OsobaController {

    private final OsobaService osobaService;
    @Autowired
    public OsobaController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }

    //ZAPYTANIE STRONNICOWANE, ZNAJDUJE ADRESY WSZYSTKICH OSOB
    @GetMapping("/adres/{page}")
    public ResponseEntity<Page<Adres>> getAdresyOsob(@PathVariable int page) {
        int size = 1;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Adres> adresyPage = osobaService.findAdresyOsob(pageRequest);
        if (adresyPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(adresyPage);
        }
    }
    //ZAPYTANIE QUERY, ZNAJDUJE NUMER TELEFONU OSOBY O PODANYM IMIENIU I NAZWISKU
    //http://localhost:8080/osoba/numer-telefonu?imie=Anna&nazwisko=Nowak
    @GetMapping("/numer-telefonu")
    public ResponseEntity<String> getNumerTelefonuByImieINazwisko(
            @RequestParam String imie,
            @RequestParam String nazwisko) {

        String numerTelefonu = osobaService.findNumerTelefonuByImieAndNazwisko(imie, nazwisko);

        if (numerTelefonu != null) {
            return ResponseEntity.ok("Numer telefonu: " + numerTelefonu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<Osoba> wszystkieOsoby() {
        return osobaService.wszystkieOsoby();
    }

    @PostMapping
    public ResponseEntity<String> createOsoba(@RequestBody Osoba osoba) {
        if (osobaService.dodajOsobe(osoba) != null) {
            return ResponseEntity.ok("Osoba została dodana");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Osoba> updateOsoba(@PathVariable Long id, @RequestBody Osoba updatedOsoba) {
        Osoba savedOsoba = osobaService.updateOsoba(id, updatedOsoba);
        if (savedOsoba==null) {
                return ResponseEntity.notFound().build();
        } else {
                return ResponseEntity.ok(savedOsoba);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOsoba(@PathVariable Long id) {
        if ( osobaService.usunOsobe(id)) {
            return ResponseEntity.ok("Osoba usunięta pomyślnie");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Osoba o podanym ID nie istnieje");
        }
    }
}

