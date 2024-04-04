package org.example.repository;

import org.example.model.Osoba;
import org.example.model.Praca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PracaRepository extends CrudRepository<Praca, Long> {
    @Query("SELECT p FROM Praca p WHERE p.nazwaStanowiska = :stanowisko")
    Praca znajdzPraceNaStanowisku(@Param("stanowisko") String stanowisko);
    @Query("SELECT p.osoby FROM Praca p WHERE p.nazwaStanowiska = :stanowisko")
    Iterable<Osoba> znajdzPracownikowNaStanowisku(@Param("stanowisko") String stanowisko);

}

