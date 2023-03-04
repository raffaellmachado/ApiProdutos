package br.com.bling.ApiProdutos.exceptions;

public class ProdutoCodigoException extends RuntimeException {
    public ProdutoCodigoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }
}
