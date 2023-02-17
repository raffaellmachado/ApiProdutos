package br.com.bling.ApiProdutos.exceptions;

public class CodigoProdutoNaoEncontradoException extends RuntimeException {
    public CodigoProdutoNaoEncontradoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }

}
