package br.com.fiap.sprint3_EcoPredict.request;

import br.com.fiap.sprint3_EcoPredict.model.Categoria;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequest {

    @NotBlank(message = "o nome é obrigatório")
    private String nomeProd;
    @Size(min = 11, message = "a descricao deve ter no mínimo 10 caracteres")
    private String descricao;
    @DecimalMin(value = "0.99", message = "o valor mínimo deve ser 0,99")
    private Double preco;
    @NotNull(message = "a categoria do produto é obrigatória")
    private Categoria categoria;

    public @NotBlank(message = "o nome é obrigatório") String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(@NotBlank(message = "o nome é obrigatório") String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public @Size(min = 11, message = "a descricao deve ter no mínimo 10 caracteres") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Size(min = 11, message = "a descricao deve ter no mínimo 10 caracteres") String descricao) {
        this.descricao = descricao;
    }

    public @DecimalMin(value = "0.99", message = "o valor mínimo deve ser 0,99") Double getPreco() {
        return preco;
    }

    public void setPreco(@DecimalMin(value = "0.99", message = "o valor mínimo deve ser 0,99") Double preco) {
        this.preco = preco;
    }

    public @NotNull(message = "a categoria do produto é obrigatória") Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotNull(message = "a categoria do produto é obrigatória") Categoria categoria) {
        this.categoria = categoria;
    }
}
