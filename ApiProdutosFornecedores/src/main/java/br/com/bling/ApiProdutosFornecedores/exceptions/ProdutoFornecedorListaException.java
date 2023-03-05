package br.com.bling.ApiProdutosFornecedores.exceptions;

public class ProdutoFornecedorListaException extends RuntimeException {
    public ProdutoFornecedorListaException() {
        super("Nenhum produto foi encontrado.");
    }
}
