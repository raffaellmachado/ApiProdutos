package br.com.bling.ApiDeposito.exceptions;

public class DepositoIdDepositoNaoEncontradoException extends RuntimeException {
    public DepositoIdDepositoNaoEncontradoException(String codigo) {
        super("Produto com código " + codigo + " não encontrado.");
    }
}
