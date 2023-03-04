package br.com.bling.ApiProdutos.exceptions;

public class ProdutoCadastroException extends RuntimeException {
    public ProdutoCadastroException(String message) {
        super("Erro ao cadastrar produto: ");
    }
}
