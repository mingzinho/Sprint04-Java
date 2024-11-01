package br.com.fiap.sprint3_EcoPredict.request;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ArquivoRequest {

    @NotBlank(message = "o nome é obrigatório")
    private String nomeArquivo;
    @NotBlank(message = "o tipo é obrigatório")
    private String tipo;
    private Usuario usuario;

    public @NotBlank(message = "o nome é obrigatório") String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(@NotBlank(message = "o nome é obrigatório") String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public @NotBlank(message = "o tipo é obrigatório") String getTipo() {
        return tipo;
    }

    public void setTipo(@NotBlank(message = "o tipo é obrigatório") String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
