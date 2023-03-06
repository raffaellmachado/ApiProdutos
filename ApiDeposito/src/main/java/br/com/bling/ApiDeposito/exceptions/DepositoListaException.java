package br.com.bling.ApiDeposito.exceptions;

public class DepositoListaException extends RuntimeException {
    public DepositoListaException() {
        super("Nenhum produto foi encontrado.");
    }
}
