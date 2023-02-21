package br.com.bling.ApiProdutos.exceptions;

public class ApiProdutoException extends RuntimeException {
    public ApiProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
}

