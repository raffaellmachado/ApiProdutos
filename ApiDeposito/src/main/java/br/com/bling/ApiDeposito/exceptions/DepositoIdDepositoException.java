package br.com.bling.ApiDeposito.exceptions;

public class DepositoIdDepositoException extends RuntimeException {
    public DepositoIdDepositoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }
}
