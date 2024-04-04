package org.example.controller;

import org.example.model.NumerTelefonu;
import org.example.service.NumerTelefonuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/numer-telefonu")
public class NumerTelefonuController {
    private final NumerTelefonuService numerTelefonuService;
    @Autowired
    public NumerTelefonuController(NumerTelefonuService numerTelefonuService) {
        this.numerTelefonuService = numerTelefonuService;
    }

    @GetMapping()
    public Iterable<NumerTelefonu> wszystkieNumeryTelefonu() {
        return numerTelefonuService.wszystkieNumeryTelefonu();
    }

    @PostMapping
    public NumerTelefonu dodajNumerTelefonu(@RequestBody NumerTelefonu numerTelefonu) {
        return numerTelefonuService.dodajNumerTelefonu(numerTelefonu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumerTelefonu> updateNumerTelefonu(@PathVariable Long id, @RequestBody NumerTelefonu updatedNumerTelefonu) {
        NumerTelefonu updatedNumer = numerTelefonuService.updateNumerTelefonu(id, updatedNumerTelefonu);

        if (updatedNumer != null) {
            return ResponseEntity.ok(updatedNumer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> usunNumerTelefonu(@PathVariable Long id) {
        String deletedNumer = numerTelefonuService.usunNumerTelefonu(id);
        if (deletedNumer != null) {
            return ResponseEntity.ok(deletedNumer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
