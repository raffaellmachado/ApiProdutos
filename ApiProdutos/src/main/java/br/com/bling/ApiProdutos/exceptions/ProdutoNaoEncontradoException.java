package br.com.bling.ApiProdutos.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }

}
