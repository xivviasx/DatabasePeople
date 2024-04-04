package org.example.controller;

import org.example.model.Adres;
import org.example.service.AdresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adres")
public class AdresController {

    private final AdresService adresService;
    @Autowired
    public AdresController(AdresService adresService) {
        this.adresService = adresService;
    }

    @GetMapping
    public Iterable<Adres> wszystkieAdresy() {
        return adresService.wszystkieAdresy();
    }

    @PostMapping
    public ResponseEntity<String> dodajAdres(@RequestBody Adres adres) {
        if (adresService.dodajAdres(adres) != null) {
            return ResponseEntity.ok("Adres został dodany");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdres(@PathVariable Long id, @RequestBody Adres updatedAdres) {
        if (adresService.updateAdres(id, updatedAdres) != null) {
            return ResponseEntity.ok("Adres został zmieniony");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> usunAdres(@PathVariable Long id) {
        if (adresService.usunAdres(id)) {
            return ResponseEntity.ok("Adres usunięty pomyślnie");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
