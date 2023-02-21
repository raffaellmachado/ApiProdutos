package br.com.bling.ApiProdutos.exceptions;

public class ProdutoNaoEncontradoParaExclusaoException extends RuntimeException {
    public ProdutoNaoEncontradoParaExclusaoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado para exclusão");
    }
}
