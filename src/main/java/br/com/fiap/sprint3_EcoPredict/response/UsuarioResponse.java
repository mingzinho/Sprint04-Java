package br.com.fiap.sprint3_EcoPredict.response;

import br.com.fiap.sprint3_EcoPredict.model.Arquivo;
import lombok.Builder;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

public class UsuarioResponse {

    private Long id;
    private String nomeEmpresa;
    private String email;
    private String cnpj;
    private List<Arquivo> arquivos = new ArrayList<>();
    private Link link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
