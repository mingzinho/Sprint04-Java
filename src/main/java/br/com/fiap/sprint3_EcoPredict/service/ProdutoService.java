package br.com.fiap.sprint3_EcoPredict.service;

import br.com.fiap.sprint3_EcoPredict.model.Produto;
import br.com.fiap.sprint3_EcoPredict.request.ProdutoRequest;
import br.com.fiap.sprint3_EcoPredict.response.ProdutoResponse;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public Produto requestToProduto(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setNomeProd(produtoRequest.getNomeProd());
        produto.setDescricao(produtoRequest.getDescricao());
        produto.setPreco(produtoRequest.getPreco());
        produto.setCategoria(produtoRequest.getCategoria());
        return produto;
    }

    public ProdutoResponse produtoToResponse(Produto produto) {
        ProdutoResponse produtoResponse = new ProdutoResponse();
        produtoResponse.setId(produto.getId());
        produtoResponse.setNomeProd(produto.getNomeProd());
        produtoResponse.setDescricao(produto.getDescricao());
        produtoResponse.setPreco(produto.getPreco());
        produtoResponse.setCategoria(produto.getCategoria().getDescricao());
        return produtoResponse;
    }
}
