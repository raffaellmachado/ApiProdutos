package br.com.bling.ApiCategoria.exceptions;

public class CategoriaListaNaoEncontradoException  extends RuntimeException {
    public CategoriaListaNaoEncontradoException() {
        super("Nenhum produto foi encontrado.");
    }
}
