package br.com.bling.ApiProdutosFornecedores.exceptions;

public class IdProdutoFornecedorNaoEncontradoException extends RuntimeException {
    public IdProdutoFornecedorNaoEncontradoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }
}
