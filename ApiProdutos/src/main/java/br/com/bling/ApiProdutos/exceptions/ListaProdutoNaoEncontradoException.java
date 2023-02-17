package br.com.bling.ApiProdutos.exceptions;

public class ListaProdutoNaoEncontradoException extends RuntimeException {
    public ListaProdutoNaoEncontradoException() {
        super("Nenhum produto foi encontrado.");
    }
}
