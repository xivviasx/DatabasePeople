package org.example.service;

import org.example.model.Adres;
import org.example.repository.AdresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;

@Service
public class AdresService {
    private final AdresRepository adresRepository;
    @Autowired
    public AdresService(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }
    public Iterable<Adres> wszystkieAdresy() {
        return adresRepository.findAll();
    }

    public Adres dodajAdres(Adres adres) {
        return adresRepository.save(adres);
    }

    public Adres updateAdres(Long id, Adres updatedAdres) {
        Optional<Adres> existingAdres = adresRepository.findById(id);
        if (existingAdres.isPresent()) {
            Adres adresToUpdate = existingAdres.get();
            // ulica
            if (updatedAdres.getUlica() != null) {adresToUpdate.setMiasto(updatedAdres.getUlica());}
            // miasto
            if (updatedAdres.getMiasto() != null) {adresToUpdate.setMiasto(updatedAdres.getMiasto());}
            return adresRepository.save(adresToUpdate);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public Boolean usunAdres(Long id) {
        Optional<Adres> adresToDelete = adresRepository.findById(id);
        if (adresToDelete.isPresent()) {
            adresRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
