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

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Usuario {

    @Size(min = 2, max = 20, message="El nombre tiene que tener entre 2 y 20 caracteres.")
    private String nombre;

    @Size(min = 2, max = 30, message="Los apellidos tienen que tener entre 2 y 30 caracteres.")
    private String apellidos;

    @Pattern(regexp = "[^@]+@[A-z,0-9]+.[A-z]+", 
             message="El correo tiene que seguir el patron example@example.example.")
    @Size(min = 3, max = 100, message="El correo tiene que tener entre 3 y 100 caracteres.")
    private String email;

    @Min(100000)
    @Max(999999)
    private int NIA;

    @Min(100000000)
    @Max(999999999)
    private int telefono;

    public Usuario() {}

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNIA(int NIA) {
        this.NIA = NIA;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
