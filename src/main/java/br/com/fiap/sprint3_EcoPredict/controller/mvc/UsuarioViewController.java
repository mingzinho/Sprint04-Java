package br.com.fiap.sprint3_EcoPredict.controller.mvc;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import br.com.fiap.sprint3_EcoPredict.repository.UsuarioRepository;
import br.com.fiap.sprint3_EcoPredict.response.UsuarioResponse;
import br.com.fiap.sprint3_EcoPredict.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioViewController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Lista de usuários
    @GetMapping("/lista")
    public ModelAndView listaUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosResponse.add(usuarioService.usuarioToResponse(usuario));
        }
        ModelAndView mv = new ModelAndView("usuario/listaUsuarios");
        mv.addObject("listaUsuarios", listaUsuariosResponse);
        return mv;
    }

    // Formulário para novo usuário (Registro)
    @GetMapping("/register")
    public ModelAndView novoUsuario() {
        ModelAndView mv = new ModelAndView("usuario/register"); // Template: usuario/register.html
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    // Processar registro de novo usuário
    @PostMapping("/register")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "usuario/register";
        }

        // Verificar se o email já está em uso
        Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            result.rejectValue("email", null, "Este email já está em uso");
            return "usuario/register";
        }

        // Criptografar a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    // Formulário para editar usuário existente
    @GetMapping("/editar/{id}")
    public ModelAndView editarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            ModelAndView mv = new ModelAndView("usuario/formUsuario");
            mv.addObject("usuario", usuario.get());
            return mv;
        }
        return new ModelAndView("redirect:/usuario/lista");
    }

    // Atualizar usuário existente
    @PostMapping("/atualizar/{id}")
    public String atualizarUsuario(@PathVariable Long id, @Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "usuario/formUsuario";
        }

        // Criptografar a senha antes de atualizar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuario.setId(id);
        usuarioRepository.save(usuario);
        return "redirect:/usuario/lista";
    }

    // Detalhes de um usuário
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhesUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            ModelAndView mv = new ModelAndView("usuario/detalhesUsuario");
            mv.addObject("usuario", usuario.get());
            return mv;
        }
        return new ModelAndView("redirect:/usuario/lista");
    }

    // Deletar usuário
    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        }
        return "redirect:/usuario/lista";
    }
}
