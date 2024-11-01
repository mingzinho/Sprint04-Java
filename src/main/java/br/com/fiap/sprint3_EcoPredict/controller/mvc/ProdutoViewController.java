package br.com.fiap.sprint3_EcoPredict.controller.mvc;

import br.com.fiap.sprint3_EcoPredict.model.Categoria;
import br.com.fiap.sprint3_EcoPredict.model.Produto;
import br.com.fiap.sprint3_EcoPredict.repository.ProdutoRepository;
import br.com.fiap.sprint3_EcoPredict.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produto")
public class ProdutoViewController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    // Lista de produtos
    @GetMapping("/lista")
    public ModelAndView listaProdutos() {
        List<Produto> listaProdutos = produtoRepository.findAll();
        ModelAndView mv = new ModelAndView("produto/listaProdutos");
        mv.addObject("listaProdutos", listaProdutos);
        return mv;
    }

    // Exibir formulário para criar novo produto
    @GetMapping("/novo")
    public ModelAndView novoProduto() {
        ModelAndView mv = new ModelAndView("produto/formProduto");
        mv.addObject("produto", new Produto());
        mv.addObject("categorias", Arrays.asList(Categoria.values()));
        return mv;
    }

    // Exibir formulário para editar um produto existente
    @GetMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            ModelAndView mv = new ModelAndView("produto/formProduto");
            mv.addObject("produto", produto.get());
            mv.addObject("categorias", Arrays.asList(Categoria.values()));
            return mv;
        }
        return new ModelAndView("redirect:/produto/lista");
    }

    // Exibir detalhes de um produto
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhesProduto(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            ModelAndView mv = new ModelAndView("produto/detalhesProduto");
            mv.addObject("produto", produto.get());
            return mv;
        }
        return new ModelAndView("redirect:/produto/lista");
    }

    // Salvar novo produto
    @PostMapping("/salvar")
    public ModelAndView salvarProduto(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("produto/formProduto");
            mv.addObject("categorias", Arrays.asList(Categoria.values()));
            return mv;
        }
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produto/lista");
    }

    // Atualizar produto existente
    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizarProduto(@PathVariable Long id, @Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("produto/formProduto");
            mv.addObject("categorias", Arrays.asList(Categoria.values()));
            return mv;
        }
        produto.setId(id);
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produto/lista");
    }

    // Deletar produto
    @GetMapping("/deletar/{id}")
    public ModelAndView deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return new ModelAndView("redirect:/produto/lista");
    }
}
