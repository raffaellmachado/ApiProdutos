package br.com.bling.ApiCategoria.exceptions;

public class CategoriaIdCategoriaException extends RuntimeException {
    public CategoriaIdCategoriaException(String idCategoria) {
        super("Produto com código " + idCategoria + " não encontrado.");
    }
}
