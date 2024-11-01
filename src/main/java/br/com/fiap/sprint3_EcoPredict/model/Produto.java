package br.com.fiap.sprint3_EcoPredict.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_prod")
    @NotBlank(message = "o nome é obrigatório")
    private String nomeProd;

    @Column(name = "descricao")
    @Size(min = 11, message = "a descricao deve ter no mínimo 10 caracteres")
    private String descricao;

    @Column(name = "preco")
    @DecimalMin(value = "0.99", message = "o valor mínimo deve ser 0,99")
    private Double preco;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "a categoria do produto é obrigatória")
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
