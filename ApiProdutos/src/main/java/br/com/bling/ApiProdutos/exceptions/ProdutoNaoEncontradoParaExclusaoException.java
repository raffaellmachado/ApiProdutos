package br.com.bling.ApiProdutos.exceptions;

public class ProdutoNaoEncontradoParaExclusaoException extends RuntimeException {
    public ProdutoNaoEncontradoParaExclusaoException(String codigo) {
        super(String.format("Produto com código '%s' não encontrado para exclusão", codigo));
    }
}
