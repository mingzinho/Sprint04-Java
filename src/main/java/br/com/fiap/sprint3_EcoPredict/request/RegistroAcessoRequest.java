package br.com.fiap.sprint3_EcoPredict.request;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RegistroAcessoRequest {

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
