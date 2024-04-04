package org.example.controller;

import org.example.model.Adres;
import org.example.model.Osoba;
import org.example.model.Praca;
import org.example.repository.PracaRepository;

import org.example.service.OsobaService;
import org.example.service.PracaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/praca")
public class PracaController {

    private final PracaService pracaService;
    @Autowired
    public PracaController(PracaService pracaService) {
        this.pracaService = pracaService;
    }

    //ZAPYTANIE QUERYY, ZNAJDUJE WSZYSTKICH PRACOWNIKOW NA DANYM STANOWISKU
    @GetMapping("/{stanowisko}/pracownicy")
    public Iterable<Osoba> znajdzPracownikowNaStanowisku(@PathVariable String stanowisko) {
        return pracaService.znajdzPracownikowNaStanowisku(stanowisko);
    }

    @GetMapping
    public Iterable<Praca> wszystkiePrace() {
        return pracaService.wszystkiePrace();
    }

    @PostMapping
    public ResponseEntity<String> dodajPrace(@RequestBody Praca praca) {
        if(pracaService.dodajPrace(praca)!=null){
            return ResponseEntity.ok("Dodano prace");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePraca(@PathVariable Long id, @RequestBody Praca updatedPraca) {
        if(pracaService.updatePraca(id, updatedPraca)!=null){
            return ResponseEntity.ok("Zmieniono prace");
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> usunPrace(@PathVariable Long id) {
        if (pracaService.usunPrace(id)) {
            return ResponseEntity.ok("Praca usunięta pomyślnie");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

