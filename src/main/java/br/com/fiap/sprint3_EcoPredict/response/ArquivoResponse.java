package br.com.fiap.sprint3_EcoPredict.response;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import lombok.Builder;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;

public class ArquivoResponse {

    private Long id;
    private String nomeArquivo;
    private String tipo;
    private LocalDateTime dataUpload;
    private Usuario usuario;
    private Link link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
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
