package br.com.bling.ApiProdutosFornecedores.exceptions;

public class ProdutoFornecedorIdException extends RuntimeException {
    public ProdutoFornecedorIdException(String idProdutoFornecedor) {
        super("Produto com código " + idProdutoFornecedor + " não localizado.");
    }
}
