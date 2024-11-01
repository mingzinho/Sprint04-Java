package br.com.fiap.sprint3_EcoPredict.response;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import lombok.Builder;
import org.springframework.hateoas.Link;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RegistroAcessoResponse {

    private Long id;
    private LocalDateTime dataHoraAcesso;
    private Usuario usuario;
    private Link link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public void setDataHoraAcesso(LocalDateTime dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
