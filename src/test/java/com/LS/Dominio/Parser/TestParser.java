package com.LS.Dominio.Parser;

import DTO.EquipamientoDTO;
import DTO.ReservaDTO;
import Enum.*;
import DTO.EspacioDTO;
import com.LS.Dominio.Entidad.Espacio;
import com.LS.Dominio.Entidad.Reserva;
import com.LS.Dominio.ObjetoValor.Equipamiento;
import com.LS.Dominio.ObjetoValor.Ubicacion;
import com.LS.Dominio.ObjetoValor.Usuario;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestParser {

    ObjetosValorParser objetosValorParser = new ObjetosValorParser();
    EspacioParser espacioParser = new EspacioParser(objetosValorParser);
    ReservaParser reservaParser = new ReservaParser(objetosValorParser);

    @Test
    public void TestObjetosValorParser() {

        final TipoEquipamiento tipoEquipamiento = TipoEquipamiento.PIZARRA;
        final int cantidad = 1;
        final int maxCantidad = 2;

        Equipamiento equipamiento = new Equipamiento(tipoEquipamiento, cantidad, maxCantidad);

        EquipamientoDTO equipamientoDTO = objetosValorParser.equipamientoOVADTO(equipamiento);

        assertEquals(new JSONObject(equipamiento).toString(), new JSONObject(equipamientoDTO).toString());

        Equipamiento equipamiento1 = objetosValorParser.equipamientoDTOAOV(equipamientoDTO);

        assertEquals(new JSONObject(equipamientoDTO).toString(), new JSONObject(equipamiento1).toString());
    }

    @Test
    public void TestEspacioParser() {

        String id = "id";
        String nombre = "Nombre";
        String tipo = "Tipo";
        int capacidad = 60;
        List<Equipamiento> equipamiento = new ArrayList<>();
        Ubicacion ubicacion = new Ubicacion("Edificio", 1);
        String notas = "";
        Boolean reservable = true;

        Espacio espacio = new Espacio(id, nombre, tipo, capacidad, equipamiento, ubicacion, notas, reservable);

        EspacioDTO dto = new EspacioDTO();
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setTipo(tipo);
        dto.setCapacidad(capacidad);
        dto.setUbicacion(objetosValorParser.ubicacionOVADTO(ubicacion));
        dto.setEquipamiento(equipamiento.stream()
                .map(objetosValorParser::equipamientoOVADTO)
                .collect(Collectors.toList()));
        dto.setNotas(notas);
        dto.setReservable(reservable);
        dto.setGeom(espacio.getGeom());

        assertEquals(new JSONObject(dto).toString(), new JSONObject(espacioParser.entidadADTO(espacio)).toString());
    }

    @Test
    public void TestReservaParser() {

        int horaInicio = 10;
        int horaFin = 10;
        Timestamp fechaInicio = Timestamp.valueOf("2007-09-23 0:0:0.0");
        Timestamp fechaFin = Timestamp.valueOf("2007-09-23 0:0:0.0");
        List<Integer> dias = new ArrayList<Integer>();
        dias.add(1);
        dias.add(2);
        dias.add(3);
        dias.add(4);
        dias.add(5);
        dias.add(6);
        dias.add(7);
        EstadoReserva estado = EstadoReserva.PENDIENTE;
        Usuario usuario = new Usuario("Nombre", "Apellidos", "nombre@apellidos.com", 123456, 123456789);
        String idEspacio = "idEspacio";

        Reserva reserva = new Reserva(horaInicio, horaFin, fechaInicio, fechaFin, dias, estado, usuario, idEspacio);

        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setIdEspacio(idEspacio);
        dto.setUsuario(objetosValorParser.usuarioOVADTO(usuario));
        dto.setDias(dias.stream()
                .map(reservaParser::calendarDaytoDia)
                .collect(Collectors.toList()));
        dto.setEstado(estado);
        dto.setFechaInicio(fechaInicio);
        dto.setFechaFin(fechaFin);
        dto.setHoraInicio(horaInicio);
        dto.setHoraFin(horaFin);

        assertEquals(new JSONObject(dto).toString(), new JSONObject(reservaParser.entidadADTO(reserva)).toString());

        List<Dia> dias1 = new ArrayList<Dia>();
        dto.setDias(dias1);

        Reserva reserva1 = reservaParser.DTOAEntidad(dto);
        assertEquals(reserva1.getHoraInicio(), reserva.getHoraInicio());
        assertEquals(reserva1.getHoraFin(), reserva.getHoraFin());
        assertEquals(reserva1.getFechaInicio(), reserva.getFechaInicio());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reserva.getFechaFin());
        calendar.add(Calendar.DATE, 1);
        assertEquals(reserva1.getFechaFin(), new Timestamp(calendar.getTimeInMillis()));
        assertEquals(reserva1.getDias(), dias1);
        assertEquals(reserva1.getEstado().getEstado(), reserva.getEstado().getEstado());
        assertEquals(new JSONObject(reserva1.getUsuario()).toString(), new JSONObject(reserva.getUsuario()).toString());
        assertEquals(reserva1.getIdEspacio(), reserva.getIdEspacio());
    }

}
