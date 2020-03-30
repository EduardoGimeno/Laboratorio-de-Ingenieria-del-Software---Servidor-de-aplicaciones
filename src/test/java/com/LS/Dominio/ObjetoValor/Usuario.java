/*
 * Usuario.java 1.0 30/03/2020
 */

/**
 * Esta clase reprensenta el objeto valor Usuario
 *
 * @author Gonzalo Berné
 * @version 1.0, 30/03/2020
 */

package com.LS.Dominio.ObjetoValor;

public class Usuario {

    private String nombre;

    private String apellidos;

    private String email;

    private int NIA;

    private int telefono;

    public Usuario(String nombre, String apellidos, String email, int NIA, int telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.NIA = NIA;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public int getNIA() {
        return NIA;
    }

    public int getTelefono() {
        return telefono;
    }
}
