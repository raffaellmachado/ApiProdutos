package br.com.bling.ApiProdutos.exceptions;

public class ProdutoException extends RuntimeException {
    public ProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
}
