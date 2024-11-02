package br.com.fiap.sprint3_EcoPredict;

import br.com.fiap.sprint3_EcoPredict.model.Produto;
import br.com.fiap.sprint3_EcoPredict.model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setNomeProd("Produto Teste");
        produto.setDescricao("Descrição do produto teste");
        produto.setPreco(10.0);
        produto.setCategoria(Categoria.ALIMENTOS_ORGANICOS);
    }

    @Test
    void testNomeProdutoNaoNulo() {
        assertNotNull(produto.getNomeProd());
    }

    @Test
    void testDescricaoMinLength() {
        assertTrue(produto.getDescricao().length() >= 10);
    }

    @Test
    void testPrecoMinValue() {
        assertTrue(produto.getPreco() >= 0.99);
    }

    @Test
    void testCategoriaNaoNula() {
        assertNotNull(produto.getCategoria());
    }
}
