package br.com.bling.ApiProdutosFornecedores.exceptions;

public class IdProdutoFornecedorNaoEncontradoException extends RuntimeException {
    public IdProdutoFornecedorNaoEncontradoException(String id) {
        super("Produto com código " + id + " não encontrado.");
    }
}
