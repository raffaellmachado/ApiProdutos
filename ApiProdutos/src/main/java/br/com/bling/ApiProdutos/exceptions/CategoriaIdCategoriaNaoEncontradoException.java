package br.com.bling.ApiProdutos.exceptions;

public class CategoriaIdCategoriaNaoEncontradoException extends RuntimeException {
    public CategoriaIdCategoriaNaoEncontradoException(String idCategoria) {
        super("Produto com código " + idCategoria + " não encontrado.");
    }
}
