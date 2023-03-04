package br.com.bling.ApiProdutos.exceptions;

public class ProdutoCodigoFornecedorException extends RuntimeException {
    public ProdutoCodigoFornecedorException(String codigo, String codigoFabricante) {
        super("Produto com código " + codigo + " e " + codigoFabricante+ " não encontrado.");
    }
}
