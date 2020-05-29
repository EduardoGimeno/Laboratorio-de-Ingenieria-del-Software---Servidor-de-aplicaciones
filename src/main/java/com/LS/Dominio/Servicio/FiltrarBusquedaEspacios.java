package com.LS.Dominio.Servicio;

import DTO.BusquedaDTO;
import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.ObjetoValor.Equipamiento;
import com.LS.Dominio.Parser.ObjetosValorParser;
import com.LS.Dominio.Repositorio.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiltrarBusquedaEspacios {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    ObjetosValorParser objetosValorParser;

    public Collection<Espacio> filtrar (BusquedaDTO busquedaDTO) {
        List<Espacio> espacios = new ArrayList<>();
        if (busquedaDTO.getEdificio().equals("null")) {
            if (busquedaDTO.getTipoEspacio().equals("null")) {
                if (busquedaDTO.getCapacidad() < 0) {
                    espacios = espacioRepository.obtenerTodos();
                } else {
                    espacios = espacioRepository.findByCapacidadMinima(busquedaDTO.getCapacidad());
                }
            } else {
                if (busquedaDTO.getCapacidad() < 0) {
                    espacios = espacioRepository.findByTipo(busquedaDTO.getTipoEspacio());
                } else {
                    espacios = espacioRepository.findByTipoAndCapacidadMinima(
                            busquedaDTO.getTipoEspacio(), busquedaDTO.getCapacidad());
                }
            }
        } else {
            if (busquedaDTO.getTipoEspacio().equals("null")) {
                if (busquedaDTO.getCapacidad() < 0) {
                    espacios = espacioRepository.findByUbicacionEdificio(busquedaDTO.getEdificio());
                } else {
                    espacios = espacioRepository.findByUbicacionEdificioAndCapacidadMinima(
                            busquedaDTO.getEdificio(), busquedaDTO.getCapacidad());
                }
            } else {
                if (busquedaDTO.getCapacidad() < 0) {
                    espacios = espacioRepository.findByUbicacionEdificioAndTipo(
                            busquedaDTO.getEdificio(), busquedaDTO.getTipoEspacio());
                } else {
                    espacios = espacioRepository.findByUbicacionEdificioAndTipoAndCapacidadMinima(
                            busquedaDTO.getEdificio(), busquedaDTO.getTipoEspacio(), busquedaDTO.getCapacidad());
                }
            }
        }
        if (!busquedaDTO.getEquipamiento().isEmpty()) {
            espacios = filtrarPorEquipamiento(espacios,
                    busquedaDTO.getEquipamiento()
                            .stream()
                            .map(objetosValorParser::equipamientoDTOAOV)
                            .collect(Collectors.toList()));
        }
        return espacios;
    }

    public List<Espacio> filtrarPorEquipamiento(List<Espacio> espacios, List<Equipamiento> equipamientos) {
        List<Espacio> espaciosFiltrados = new ArrayList<>();
        for (Espacio espacio: espacios) {
            boolean correcto = true;
            for (Equipamiento equipamiento: equipamientos) {
                boolean tieneEquipamiento = false;
                for (Equipamiento e: espacio.getEquipamiento()) {
                    if (e.getTipo() == equipamiento.getTipo() && e.getCantidad() >= equipamiento.getCantidad()) {
                        tieneEquipamiento = true;
                        break;
                    }
                }
                if (!tieneEquipamiento) {
                    correcto = false;
                    break;
                }
            }
            if (correcto) {
                espaciosFiltrados.add(espacio);
            }
        }
        return espaciosFiltrados;
    }

}
