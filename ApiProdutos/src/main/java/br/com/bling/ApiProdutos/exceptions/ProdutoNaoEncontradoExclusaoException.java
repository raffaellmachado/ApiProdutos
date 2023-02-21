package br.com.bling.ApiProdutos.exceptions;

public class ProdutoNaoEncontradoExclusaoException extends RuntimeException {
    public ProdutoNaoEncontradoExclusaoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado para exclusão");
    }
}
