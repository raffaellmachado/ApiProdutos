package br.com.bling.ApiContatos.exceptions;

public class ContatoCadastroException extends RuntimeException {
    public ContatoCadastroException(String message) {
        super("Cadastro não efetuado, revise os campos e tente novamente!");
    }
}
