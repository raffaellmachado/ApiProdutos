package br.com.bling.ApiContatos.exceptions;

public class ApiContatoException extends RuntimeException {
    public ApiContatoException(String message, Throwable cause) {
        super(message, cause);
    }
}

