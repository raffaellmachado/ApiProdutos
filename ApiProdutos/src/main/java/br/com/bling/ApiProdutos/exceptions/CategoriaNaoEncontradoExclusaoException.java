package br.com.bling.ApiProdutos.exceptions;

public class CategoriaNaoEncontradoExclusaoException extends RuntimeException {
    public CategoriaNaoEncontradoExclusaoException(String idCategoria) {
        super("Categoria com código " + idCategoria + " não encontrado para exclusão");
    }
}
