package br.com.bling.ApiProdutos.exceptions;

public class ProdutoCodigoNaoEncontradoException extends RuntimeException {
    public ProdutoCodigoNaoEncontradoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }
}
