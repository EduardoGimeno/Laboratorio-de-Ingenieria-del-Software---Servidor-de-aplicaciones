/*
 * EspacioRepository.java 1.0 30/03/2020
 */

/**
 * Esta interfaz es el repositorio de Espacio
 * utilizado para mantener persistencia de la entidad Espacio
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.Repositorio;

import com.LS.Dominio.Entidad.Espacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface EspacioRepository extends CrudRepository<Espacio, String> {

    Optional<Espacio> findById (String id);

    @Query(value = "SELECT e FROM Espacio e")
    List<Espacio> obtenerTodos ();

    List<Espacio> findByUbicacionEdificioAndTipo (String edificio, String tipo);

    List<Espacio> findByUbicacionEdificio (String edificio);

    List<Espacio> findByTipo (String tipo);

    @Query(value = "SELECT e FROM Espacio e WHERE e.capacidad > ?1")
    List<Espacio> findByCapacidadMinima (int capacidad);

    @Query(value = "SELECT e FROM Espacio e WHERE e.ubicacion.edificio = ?1 AND " +
            "e.capacidad > ?2")
    List<Espacio> findByUbicacionEdificioAndCapacidadMinima (String edificio, int capacidad);

    @Query(value = "SELECT e FROM Espacio e WHERE e.tipo = ?1 AND " +
            "e.capacidad > ?2")
    List<Espacio> findByTipoAndCapacidadMinima (String tipo, int capacidad);


    @Query(value = "SELECT e FROM Espacio e WHERE e.ubicacion.edificio = ?1 AND " +
            "e.tipo = ?2 AND e.capacidad > ?3")
    List<Espacio> findByUbicacionEdificioAndTipoAndCapacidadMinima (String edificio, String tipo, int capacidad);
}
