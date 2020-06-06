package com.LS.Dominio.Entidad;

import Enum.EstadoReserva;
import Enum.TipoEquipamiento;
import com.LS.Dominio.ObjetoValor.Equipamiento;
import com.LS.Dominio.ObjetoValor.Ubicacion;
import com.LS.Dominio.ObjetoValor.Usuario;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntidad {

    @Test
    public void TestEspacio() {

        String id = "id";
        String nombre = "Nombre";
        String tipo = "Tipo";
        int capacidad = 60;
        List<Equipamiento> equipamiento = new ArrayList<>();
        Ubicacion ubicacion = new Ubicacion("Edificio", 1);
        String notas = "";
        Boolean reservable = true;

        Espacio espacio = new Espacio(id, nombre, tipo, capacidad, equipamiento, ubicacion, notas, reservable);

        assertEquals(id, espacio.getId());
        assertEquals(nombre, espacio.getNombre());
        assertEquals(tipo, espacio.getTipo());
        assertEquals(capacidad, espacio.getCapacidad());
        assertEquals(equipamiento, espacio.getEquipamiento());
        assertEquals(ubicacion, espacio.getUbicacion());
        assertEquals(notas, espacio.getNotas());
        assertEquals(reservable, espacio.getReservable());

        Equipamiento equipamiento1 = new Equipamiento(TipoEquipamiento.PIZARRA, 1, 1);
        equipamiento.add(equipamiento1);
        reservable = !reservable;
        notas = "Nota solitaria";
        espacio.modificar(equipamiento, capacidad + 10, reservable, notas);

        assertEquals(capacidad + 10, espacio.getCapacidad());
        assertEquals(equipamiento, espacio.getEquipamiento());
        assertEquals(ubicacion, espacio.getUbicacion());
        assertEquals(notas, espacio.getNotas());
        assertEquals(reservable, espacio.getReservable());
    }

    @Test
    public void TestGerente() {

        String nombre = "nomUsuario";
        String password = "123456789";

        Gerente gerente = new Gerente();

        assertNull(gerente.getNombre());
        assertNull(gerente.getContrasena());

        gerente = new Gerente(nombre, password);

        assertEquals(nombre, gerente.getNombre());
        assertEquals(password, gerente.getContrasena());
    }

    @Test
    public void TestReserva() {

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

        assertNotNull(reserva.getId());
        assertEquals(horaInicio, reserva.getHoraInicio());
        assertEquals(horaFin, reserva.getHoraFin());
        assertEquals(fechaInicio, reserva.getFechaInicio());
        assertEquals(fechaFin, reserva.getFechaFin());
        assertEquals(dias.stream()
                .map(Reserva::getDiaGlobal)
                .collect(Collectors.toList()), reserva.getDias());
        assertEquals(estado.getEstado(), reserva.getEstado().getEstado());
        assertEquals(usuario, reserva.getUsuario());
        assertEquals(idEspacio, reserva.getIdEspacio());

        reserva.setEstado(EstadoReserva.ACEPTADA);
        assertEquals(EstadoReserva.ACEPTADA.getEstado(), reserva.getEstado().getEstado());
    }

}