package org.example.service;

import org.example.model.Adres;
import org.example.model.Osoba;
import org.example.model.Praca;
import org.example.repository.PracaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class PracaService {

    private final PracaRepository pracaRepository;

    @Autowired
    public PracaService(PracaRepository pracaRepository) {
        this.pracaRepository = pracaRepository;
    }

    public Iterable<Osoba> znajdzPracownikowNaStanowisku(String stanowisko) {
        Praca praca = pracaRepository.znajdzPraceNaStanowisku(stanowisko);
        if (praca != null) {
            return pracaRepository.znajdzPracownikowNaStanowisku(stanowisko);
        } else {
            return null;
        }
    }

    public Iterable<Praca> wszystkiePrace() {
        return pracaRepository.findAll();
    }

    public Praca dodajPrace(Praca praca) {
        Praca nowaPraca  = pracaRepository.save(praca);
        return nowaPraca;
    }

    public ResponseEntity<Praca> updatePraca(Long id, Praca updatedPraca) {
        Optional<Praca> existingPraca = pracaRepository.findById(id);
        if (existingPraca.isPresent()) {
            Praca pracaToUpdate = existingPraca.get();
            // ulica
            if (updatedPraca.getNazwaStanowiska() != null) {pracaToUpdate.setNazwaStanowiska(updatedPraca.getNazwaStanowiska());}
            Praca savedPraca = pracaRepository.save(pracaToUpdate);
            return ResponseEntity.ok(savedPraca);
        } else {
            return null;
        }
    }

    public Boolean usunPrace(Long id) {
        Optional<Praca> pracaToDelete = pracaRepository.findById(id);
        if (pracaToDelete.isPresent()) {
            pracaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
