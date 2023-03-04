package br.com.bling.ApiProdutos.exceptions;

public class ProdutoListaException extends RuntimeException {
    public ProdutoListaException() {
        super("Nenhum produto foi encontrado.");
    }
}
