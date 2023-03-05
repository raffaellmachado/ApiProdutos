package br.com.bling.ApiContatos.exceptions;

public class ContatoListaException extends RuntimeException {
    public ContatoListaException() {
        super("Nenhum contato foi localizado.");
    }
}
