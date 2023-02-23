package br.com.bling.ApiContatos.exceptions;

public class ContatoListaNaoEncontradoException extends RuntimeException {
    public ContatoListaNaoEncontradoException() {
        super("Nenhum produto foi encontrado.");
    }
}
