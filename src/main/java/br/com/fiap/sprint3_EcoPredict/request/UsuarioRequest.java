package br.com.fiap.sprint3_EcoPredict.request;

import br.com.fiap.sprint3_EcoPredict.model.Arquivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRequest {

    @NotBlank(message = "o nome é obrigatório")
    private String nomeEmpresa;
    @NotBlank(message = "o nome é obrigatório")
    private String email;
    @NotBlank(message = "a senha é obrigatória")
    private String senha;
    @Size(min = 11, max = 11)
    private String cnpj;

    List<Arquivo> arquivos = new ArrayList<>();

    public @NotBlank(message = "o nome é obrigatório") String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(@NotBlank(message = "o nome é obrigatório") String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public @NotBlank(message = "o nome é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "o nome é obrigatório") String email) {
        this.email = email;
    }

    public @NotBlank(message = "a senha é obrigatória") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "a senha é obrigatória") String senha) {
        this.senha = senha;
    }

    public @Size(min = 11, max = 11) String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@Size(min = 11, max = 11) String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }
}
