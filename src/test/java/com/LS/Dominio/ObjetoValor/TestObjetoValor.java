package com.LS.Dominio.ObjetoValor;

import Enum.TipoEquipamiento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestObjetoValor {

    @Test
    public void TestEquipamiento() {

        final TipoEquipamiento tipoEquipamiento = TipoEquipamiento.PIZARRA;
        final int cantidad = 1;
        final int maxCantidad = 2;

        Equipamiento equipamiento = new Equipamiento();

        assertNull(equipamiento.getTipo());
        assertEquals(0, equipamiento.getCantidad());
        assertEquals(0, equipamiento.getMaxCantidad());

        equipamiento = new Equipamiento(tipoEquipamiento, cantidad, maxCantidad);

        assertEquals(tipoEquipamiento, equipamiento.getTipo());
        assertEquals(cantidad, equipamiento.getCantidad());
        assertEquals(maxCantidad, equipamiento.getMaxCantidad());

        assertEquals("pizarra", equipamiento.returnTipoString());

        assertTrue(equipamiento.anadir(1));

        assertEquals(cantidad + 1, equipamiento.getCantidad());
        assertEquals(cantidad + 1, equipamiento.getMaxCantidad());
        assertFalse(equipamiento.anadir(1));

        assertTrue(equipamiento.quitar(2));
        assertEquals(cantidad - 1, equipamiento.getCantidad());
        assertFalse(equipamiento.quitar(1));

    }

    @Test
    public void TestUbicacion() {

        final String edificio = "Ada Byron";
        final int planta = 0;

        Ubicacion ubicacion = new Ubicacion();

        assertNull(ubicacion.getEdificio());
        assertEquals(0, ubicacion.getPlanta());

        ubicacion = new Ubicacion(edificio, planta);

        assertEquals(edificio, ubicacion.getEdificio());
        assertEquals(planta, ubicacion.getPlanta());

    }

    @Test
    public void TestUsuario() {

        final String nombre = "Adolfo";
        final String apellidos = "Rodriguez Gutierrez";
        final String email = "adolfo@rodriguezgutierrez.com";
        final int NIA = 123456;
        final int telefono = 123456789;

        Usuario usuario = new Usuario();

        assertNull(usuario.getNombre());
        assertNull(usuario.getApellidos());
        assertNull(usuario.getEmail());
        assertEquals(0, usuario.getNIA());
        assertEquals(0, usuario.getTelefono());

        usuario = new Usuario(nombre, apellidos, email, NIA, telefono);

        assertEquals(nombre, usuario.getNombre());
        assertEquals(apellidos, usuario.getApellidos());
        assertEquals(email, usuario.getEmail());
        assertEquals(NIA, usuario.getNIA());
        assertEquals(telefono, usuario.getTelefono());

        usuario.setNombre("Benedicto");
        usuario.setApellidos("Mateo Montoro");
        usuario.setEmail("benedicto@mateomontoro.com");
        usuario.setNIA(654321);
        usuario.setTelefono(987654321);

        assertEquals("Benedicto", usuario.getNombre());
        assertEquals("Mateo Montoro", usuario.getApellidos());
        assertEquals("benedicto@mateomontoro.com", usuario.getEmail());
        assertEquals(654321, usuario.getNIA());
        assertEquals(987654321, usuario.getTelefono());
    }

}