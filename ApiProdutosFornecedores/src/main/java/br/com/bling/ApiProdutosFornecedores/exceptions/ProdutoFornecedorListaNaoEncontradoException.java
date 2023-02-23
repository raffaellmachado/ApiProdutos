package br.com.bling.ApiProdutosFornecedores.exceptions;

public class ProdutoFornecedorListaNaoEncontradoException extends RuntimeException {
    public ProdutoFornecedorListaNaoEncontradoException() {
        super("Nenhum produto foi encontrado.");
    }
}
