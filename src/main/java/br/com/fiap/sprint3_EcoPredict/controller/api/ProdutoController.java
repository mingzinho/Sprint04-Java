package br.com.fiap.sprint3_EcoPredict.controller.api;

import br.com.fiap.sprint3_EcoPredict.model.Produto;
import br.com.fiap.sprint3_EcoPredict.repository.ProdutoRepository;
import br.com.fiap.sprint3_EcoPredict.request.ProdutoRequest;
import br.com.fiap.sprint3_EcoPredict.response.ProdutoResponse;
import br.com.fiap.sprint3_EcoPredict.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> crate(@Valid @RequestBody ProdutoRequest produtoRequest) {
        Produto produtoConvertido = produtoService.requestToProduto(produtoRequest);
        Produto produtoPersistido = produtoRepository.save(produtoConvertido);
        ProdutoResponse produtoResponse = produtoService.produtoToResponse(produtoPersistido);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> read() {
        List<Produto> listaProdutos = produtoRepository.findAll();
        if (listaProdutos.isEmpty()) {
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ProdutoResponse> listaProdutoResponse = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            ProdutoResponse produtoResponse = produtoService.produtoToResponse(produto);
            produtoResponse.setLink(
                    linkTo(
                            methodOn(ProdutoController.class).read(produto.getId())).withSelfRel());
            listaProdutoResponse.add(produtoResponse);
        }
        return new ResponseEntity<>(listaProdutoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> read(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            ProdutoResponse produtoResponse = produtoService.produtoToResponse(produto.get());
            produtoResponse.setLink(
                    linkTo(
                            methodOn(ProdutoController.class)
                                    .read()).withRel("Lista de produtos"));
            return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, @Valid @RequestBody ProdutoRequest produtoRequest) {
        Optional<Produto> produtoPesistido = produtoRepository.findById(id);
        ProdutoResponse produtoResponse = null;
        if (produtoPesistido.isPresent()) {
            Produto produto = produtoService.requestToProduto(produtoRequest);
            produto.setId(id);
            Produto produtoAtualizado = produtoRepository.save(produto);
            produtoResponse = produtoService.produtoToResponse(produtoAtualizado);
            return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(produtoResponse, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
