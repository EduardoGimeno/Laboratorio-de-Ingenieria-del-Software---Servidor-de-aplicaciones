/*
 * Gerente.java 1.0 01/04/2020
 */

/**
 * Esta clase reprensenta la entidad Gerente
 *
 * @author Eduardo Gimeno
 * @version 1.0, 01/04/2020
 */

package com.LS.Dominio.Entidad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Gerente {

    @Id
    private String nombre;

    @NotNull
    @Size(min = 8, max = 100, message="La contraseña tiene que tener entre 8 y 100 caracteres.")
    @Pattern(regexp = "[A-z,0-9,_,-]+", 
             message="La contraseña solo puede tener letras mayusculas o minusculas sin acentuar,"
             + "numeros, y los caracteres _ y -.")
    private String contrasena;

    public Gerente(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getContrasena() {
        return this.contrasena;
    }
}