package br.com.fiap.sprint3_EcoPredict;

import br.com.fiap.sprint3_EcoPredict.model.Arquivo;
import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ArquivoTest {

    private Arquivo arquivo;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNomeEmpresa("Empresa Teste");

        arquivo = new Arquivo();
        arquivo.setNomeArquivo("arquivo_teste.pdf");
        arquivo.setTipo("pdf");
        arquivo.setDataUpload(LocalDateTime.now());
        arquivo.setUsuario(usuario);
    }

    @Test
    void testNomeArquivoNaoNulo() {
        assertNotNull(arquivo.getNomeArquivo());
    }

    @Test
    void testTipoArquivoNaoNulo() {
        assertNotNull(arquivo.getTipo());
    }

    @Test
    void testDataUploadNaoNula() {
        assertNotNull(arquivo.getDataUpload());
    }

    @Test
    void testAssociacaoUsuario() {
        assertEquals(usuario, arquivo.getUsuario());
    }
}
