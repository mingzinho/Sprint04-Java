package br.com.fiap.sprint3_EcoPredict.controller.mvc;

import br.com.fiap.sprint3_EcoPredict.model.Arquivo;
import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import br.com.fiap.sprint3_EcoPredict.repository.ArquivoRepository;
import br.com.fiap.sprint3_EcoPredict.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/arquivo")
public class ArquivoViewController {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;  // Repositório para buscar usuários

    // Redireciona para a lista de arquivos
    @GetMapping
    public String redirectToLista() {
        return "redirect:/arquivo/lista";
    }

    // Exibe a lista de arquivos
    @GetMapping("/lista")
    public ModelAndView listaArquivos() {
        List<Arquivo> listaArquivos = arquivoRepository.findAll();
        ModelAndView mv = new ModelAndView("arquivo/lista");
        mv.addObject("listaArquivos", listaArquivos);
        return mv;
    }

    // Exibe o formulário para criação de um novo arquivo
    @GetMapping("/novo")
    public ModelAndView novoArquivo() {
        ModelAndView mv = new ModelAndView("arquivo/form");
        mv.addObject("arquivo", new Arquivo());
        mv.addObject("usuarios", usuarioRepository.findAll());  // Passa os usuários para o formulário
        return mv;
    }

    // Exibe o formulário para editar um arquivo existente
    @GetMapping("/editar/{id}")
    public ModelAndView editarArquivo(@PathVariable Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            ModelAndView mv = new ModelAndView("arquivo/form");
            mv.addObject("arquivo", arquivo.get());
            mv.addObject("usuarios", usuarioRepository.findAll());  // Passa os usuários para o formulário
            return mv;
        }
        return new ModelAndView("redirect:/arquivo/lista");
    }

    // Exibe os detalhes de um arquivo
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhesArquivo(@PathVariable Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            ModelAndView mv = new ModelAndView("arquivo/detalhes");
            mv.addObject("arquivo", arquivo.get());
            return mv;
        }
        return new ModelAndView("redirect:/arquivo/lista");
    }

    // Salva um novo arquivo
    @PostMapping("/salvar")
    public String salvarArquivo(@Valid Arquivo arquivo, BindingResult result) {
        if (result.hasErrors()) {
            return "arquivo/form";
        }
        arquivo.setDataUpload(LocalDateTime.now());  // Define a data de upload
        arquivoRepository.save(arquivo);
        return "redirect:/arquivo/lista";
    }

    // Atualiza um arquivo existente
    @PostMapping("/atualizar/{id}")
    public String atualizarArquivo(@PathVariable Long id, @Valid Arquivo arquivo, BindingResult result) {
        if (result.hasErrors()) {
            return "arquivo/form";
        }
        arquivo.setId(id);
        arquivo.setDataUpload(LocalDateTime.now());  // Atualiza a data de upload
        arquivoRepository.save(arquivo);
        return "redirect:/arquivo/lista";
    }

    // Deleta um arquivo
    @GetMapping("/deletar/{id}")
    public String deletarArquivo(@PathVariable Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            arquivoRepository.delete(arquivo.get());
        }
        return "redirect:/arquivo/lista";
    }
}
