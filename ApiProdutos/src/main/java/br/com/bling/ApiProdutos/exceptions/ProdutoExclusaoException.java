package br.com.bling.ApiProdutos.exceptions;

public class ProdutoExclusaoException extends RuntimeException {
    public ProdutoExclusaoException(String message) {
        super(message);
    }
}
