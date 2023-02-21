package br.com.bling.ApiDeposito.exceptions;

public class DepositoListaNaoEncontradoException extends RuntimeException {
    public DepositoListaNaoEncontradoException() {
        super("Nenhum produto foi encontrado.");
    }
}
