package br.com.bling.ApiCategoria.exceptions;

public class CategoriaListaException extends RuntimeException {
    public CategoriaListaException() {
        super("Nenhum produto foi encontrado.");
    }
}
