package br.com.fiap.sprint3_EcoPredict.service;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import br.com.fiap.sprint3_EcoPredict.request.UsuarioRequest;
import br.com.fiap.sprint3_EcoPredict.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario requestToUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNomeEmpresa(usuarioRequest.getNomeEmpresa());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha())); // Criptografa a senha
        usuario.setCnpj(usuarioRequest.getCnpj());
        usuario.setArquivos(usuarioRequest.getArquivos());
        return usuario;
    }

    public UsuarioResponse usuarioToResponse(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNomeEmpresa(usuario.getNomeEmpresa());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setCnpj(usuario.getCnpj());
        usuarioResponse.setArquivos(usuario.getArquivos());
        return usuarioResponse;
    }
}
