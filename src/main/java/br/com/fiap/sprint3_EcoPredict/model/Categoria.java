package br.com.fiap.sprint3_EcoPredict.model;

public enum Categoria {

    ALIMENTOS_ORGANICOS("Alimentos Orgânicos"),
    MODA_SUSTENTAVEL("Moda Sustentável"),
    BELEZA_E_CUIDADOS_PESSOAIS("Beleza e cuidados pessoais"),
    TECNOLOGIA_VERDE("Tecnologia Verde");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
