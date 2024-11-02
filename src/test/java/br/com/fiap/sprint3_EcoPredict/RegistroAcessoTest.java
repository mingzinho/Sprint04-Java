package br.com.fiap.sprint3_EcoPredict;

import br.com.fiap.sprint3_EcoPredict.model.RegistroAcesso;
import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegistroAcessoTest {

    private RegistroAcesso registro;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNomeEmpresa("Empresa Teste");

        registro = new RegistroAcesso();
        registro.setUsuario(usuario);
        registro.setDataHoraAcesso(LocalDateTime.now());
    }

    @Test
    void testGetUsuario() {
        assertEquals(usuario, registro.getUsuario());
    }

    @Test
    void testGetDataHoraAcesso() {
        assertNotNull(registro.getDataHoraAcesso());
    }
}
