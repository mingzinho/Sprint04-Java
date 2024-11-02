package br.com.fiap.sprint3_EcoPredict;


import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNomeEmpresa("Empresa Teste");
        usuario.setEmail("teste@empresa.com");
        usuario.setSenha("senhaSegura");
        usuario.setCnpj("12345678901234");
    }

    @Test
    void testGetUsername() {
        assertEquals("teste@empresa.com", usuario.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("senhaSegura", usuario.getPassword());
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(usuario.isAccountNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(usuario.isEnabled());
    }
}
