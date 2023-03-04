package br.com.bling.ApiProdutos.exceptions;

public class ProdutoExclusaoException extends RuntimeException {
    public ProdutoExclusaoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado para exclusão");
    }
}
