package com.LS.Dominio.Servicio;

import DTO.BusquedaDTO;
import DTO.EquipamientoDTO;
import DTO.HorarioDTO;
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
    private ObjetosValorParser objetosValorParser;

    @Autowired
    private ObtenerHorarios obtenerHorarios;

    public Collection<Espacio> filtrar (BusquedaDTO busquedaDTO) {
        busquedaDTO = limpiarDatosIncorrectos(busquedaDTO);
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

        espacios = filtrarPorHorario(espacios, busquedaDTO);

        return espacios;
    }

    private List<Espacio> filtrarPorEquipamiento(List<Espacio> espacios, List<Equipamiento> equipamientos) {
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

    private List<Espacio> filtrarPorHorario(List<Espacio> espacios, BusquedaDTO busqueda) {
        List<Espacio> espaciosFiltrados = new ArrayList<>();
        if (!busqueda.isPeriodo()) {
            for (Espacio espacio: espacios) {
                HorarioDTO horario = obtenerHorarios.obtenerPorEspacioYDia(espacio.getId(),
                        busqueda.getFechaInicio());
                boolean ocupado = false;
                for (int i = busqueda.getHoraInicio(); i < busqueda.getHoraFin(); i++) {
                    if (horario.getHorasOcupadas().contains(i)) {
                        ocupado = true;
                    }
                }
                if (!ocupado) {
                    espaciosFiltrados.add(espacio);
                }
            }
        } else {
            for (Espacio espacio: espacios) {
                List<HorarioDTO> horarios = obtenerHorarios.obtenerPorEspaciosEntreFechasYDiasConcretos(espacio.getId(),
                        busqueda.getFechaInicio(), busqueda.getFechaFin(), busqueda.getDias());
                boolean ocupado = false;
                for (HorarioDTO horario: horarios) {
                    for (int i = busqueda.getHoraInicio(); i < busqueda.getHoraFin(); i++) {
                        if (horario.getHorasOcupadas().contains(i)) {
                            ocupado = true;
                        }
                    }
                }
                if (!ocupado) {
                    espaciosFiltrados.add(espacio);
                }
            }
        }
        return espaciosFiltrados;
    }

    private BusquedaDTO limpiarDatosIncorrectos(BusquedaDTO busqueda) {
        if (!busqueda.isPeriodo()) {
            busqueda.setDias(new ArrayList<>());
        }
        List<EquipamientoDTO> equipamientos = new ArrayList<>();
        for (EquipamientoDTO equipamiento :busqueda.getEquipamiento()) {
            if (equipamiento.getCantidad() > 0) {
                equipamientos.add(equipamiento);
            }
        }
        busqueda.setEquipamiento(equipamientos);
        return busqueda;
    }

}
