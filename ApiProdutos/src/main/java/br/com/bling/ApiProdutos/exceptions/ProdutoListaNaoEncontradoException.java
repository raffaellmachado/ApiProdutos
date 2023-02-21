package br.com.bling.ApiProdutos.exceptions;

public class ProdutoListaNaoEncontradoException extends RuntimeException {
    public ProdutoListaNaoEncontradoException() {
        super("Nenhum produto foi encontrado.");
    }
}
