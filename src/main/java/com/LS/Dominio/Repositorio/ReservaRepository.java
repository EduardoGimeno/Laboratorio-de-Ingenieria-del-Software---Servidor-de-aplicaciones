/*
 * ReservaRepository.java 1.0 30/03/2020
 */

/**
 * Esta interfaz es el repositorio de Reserva
 * utilizado para mantener persistencia de la entidad Reserva
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Repositorio;

import com.LS.Dominio.Entidad.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, String> {

    List<Reserva> findByIdEspacio(String idEspacio);

    // ??
    @Query(value = "SELECT r FROM Reserva r WHERE r.idEspacio = ?1 " +
            "AND r.horaInicio > ?2 AND r.horaInicio < ?3")
    List<Reserva> findByIdEspacioYEntreDosFechas(String idEspacio, Timestamp dia, Timestamp diaSiguiente);

    @Query(value = "SELECT r FROM Reserva r WHERE r.idEspacio = ?1 " +
            "AND r.fechaInicio <= ?2 AND r.fechaFin >= ?2 AND ?3 MEMBER OF r.dias")
    List<Reserva> findByIdEspacioYDia(String idEspacio, Timestamp dia, Integer diaSemana);
}
