package org.example.repository;
import org.example.model.Adres;
import org.example.model.Osoba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OsobaRepository extends CrudRepository<Osoba, Long> {
    @Query("SELECT nt.numer FROM Osoba o " +
            "JOIN o.numerTelefonu nt " +
            "WHERE o.imie = :imie AND o.nazwisko = :nazwisko")
    String findNumerTelefonuByImieAndNazwisko(@Param("imie") String imie, @Param("nazwisko") String nazwisko);

    @Query("SELECT o.adres FROM Osoba o")
    Page<Adres> findAdresyOsob(Pageable pageable);
}


