package org.example.service;
//KORZYSTA Z ZAPYTAN DO BAZY DANYC BEDACYCH W REPOISITORY, NADPISUJE JE I USTALA KOLEJNOSC W JAKIEJ MAJĄ SIE ODBYWAC
import org.example.model.Adres;
import org.example.model.Osoba;
import org.example.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OsobaService {

    @Autowired
    private OsobaRepository osobaRepository;

    public String findNumerTelefonuByImieAndNazwisko(String imie, String nazwisko) {
        return osobaRepository.findNumerTelefonuByImieAndNazwisko(imie, nazwisko);
    }

    public Page<Adres> findAdresyOsob(Pageable pageable) {
        return osobaRepository.findAdresyOsob(pageable);
    }

    public Iterable<Osoba> wszystkieOsoby(){
        return osobaRepository.findAll();
    }
    public Osoba dodajOsobe(Osoba osoba) {
        return osobaRepository.save(osoba);
    }
    public Osoba updateOsoba(Long id, Osoba updatedOsoba) {
        Optional<Osoba> existingOsoba = osobaRepository.findById(id);

        if (existingOsoba.isPresent()) {
            Osoba osobaToUpdate = existingOsoba.get();

            //imie
            if (updatedOsoba.getImie() != null) {osobaToUpdate.setImie(updatedOsoba.getImie());}
            //nazwisko
            if (updatedOsoba.getNazwisko() != null) {osobaToUpdate.setNazwisko(updatedOsoba.getNazwisko());}
            //dataurodzenia
            if (updatedOsoba.getDataUrodzenia() != null) {osobaToUpdate.setDataUrodzenia(updatedOsoba.getDataUrodzenia());}
            //nr tel
            if (updatedOsoba.getNumerTelefonu() != null && updatedOsoba.getNumerTelefonu().getId() != null) {osobaToUpdate.setNumerTelefonu(updatedOsoba.getNumerTelefonu());}
            //praca
            if (updatedOsoba.getPraca() != null && updatedOsoba.getPraca().getId() != null) {osobaToUpdate.setPraca(updatedOsoba.getPraca());}
            //adres
            if (updatedOsoba.getAdres() != null && updatedOsoba.getAdres().getId() != null) {osobaToUpdate.setAdres(updatedOsoba.getAdres());}

            Osoba savedOsoba = osobaRepository.save(osobaToUpdate);
            return savedOsoba;
        }
        else{
            return null;
        }
    }

    public boolean usunOsobe(Long id) {
        if (osobaRepository.existsById(id)) {
            osobaRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}

