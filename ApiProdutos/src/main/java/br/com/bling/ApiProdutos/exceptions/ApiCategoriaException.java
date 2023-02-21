package br.com.bling.ApiProdutos.exceptions;

public class ApiCategoriaException extends RuntimeException {
    public ApiCategoriaException(String message, Throwable cause) {
        super(message, cause);
    }
}
