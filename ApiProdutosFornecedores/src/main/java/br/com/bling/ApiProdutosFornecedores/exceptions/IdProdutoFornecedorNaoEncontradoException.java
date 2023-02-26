package br.com.bling.ApiProdutosFornecedores.exceptions;

public class IdProdutoFornecedorNaoEncontradoException extends RuntimeException {
    public IdProdutoFornecedorNaoEncontradoException(String idProdutoFornecedor) {
        super("Produto com código " + idProdutoFornecedor + " não encontrado.");
    }
}
