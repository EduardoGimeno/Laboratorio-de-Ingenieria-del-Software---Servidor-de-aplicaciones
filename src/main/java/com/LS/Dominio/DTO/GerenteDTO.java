package com.LS.Dominio.DTO;

import java.io.Serializable;

public class GerenteDTO implements Serializable {

    String nomUsuario;
    String password;

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
