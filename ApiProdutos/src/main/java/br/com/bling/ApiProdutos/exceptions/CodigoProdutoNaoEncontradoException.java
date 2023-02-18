package br.com.bling.ApiProdutos.exceptions;

import org.springframework.http.HttpStatus;

public class CodigoProdutoNaoEncontradoException extends RuntimeException {
    public CodigoProdutoNaoEncontradoException(String codigo) {
        super(String.format("Produto com código " + codigo + " não encontrado.", codigo));
    }

}
