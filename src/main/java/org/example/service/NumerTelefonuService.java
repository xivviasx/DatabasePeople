package org.example.service;

import org.example.model.NumerTelefonu;
import org.example.repository.NumerTelefonuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NumerTelefonuService {
    private final NumerTelefonuRepository numerTelefonuRepository;
    @Autowired
    public NumerTelefonuService(NumerTelefonuRepository numerTelefonuRepository) {
        this.numerTelefonuRepository = numerTelefonuRepository;
    }
    public Iterable<NumerTelefonu> wszystkieNumeryTelefonu() {
        return numerTelefonuRepository.findAll();
    }

    public NumerTelefonu dodajNumerTelefonu(NumerTelefonu numerTelefonu) {
            return numerTelefonuRepository.save(numerTelefonu);
        }

    public NumerTelefonu updateNumerTelefonu(Long id, NumerTelefonu updatedNumerTelefonu) {
        Optional<NumerTelefonu> existingNumerTelefonu = numerTelefonuRepository.findById(id);

        if (existingNumerTelefonu.isPresent()) {
            NumerTelefonu numerTelefonuToUpdate = existingNumerTelefonu.get();

            // Numer telefonu
            if (updatedNumerTelefonu.getNumer() != null) {
                numerTelefonuToUpdate.setNumer(updatedNumerTelefonu.getNumer());
            }

            // Dodaj inne aktualizacje pól w razie potrzeby

            return numerTelefonuRepository.save(numerTelefonuToUpdate);
        } else {
            return null; // Możesz zwrócić null, aby wskazać, że nie udało się znaleźć rekordu do aktualizacji
        }
    }

    public String usunNumerTelefonu(Long id) {
        Optional<NumerTelefonu> numerTelefonuToDelete = numerTelefonuRepository.findById(id);

        if (numerTelefonuToDelete.isPresent()) {
            numerTelefonuRepository.deleteById(id);
            return "Numer telefonu usunięty pomyślnie";
        } else {
            return null;
        }
    }
}
