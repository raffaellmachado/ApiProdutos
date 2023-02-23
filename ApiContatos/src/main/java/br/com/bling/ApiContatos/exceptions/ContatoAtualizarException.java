package br.com.bling.ApiContatos.exceptions;

public class ContatoAtualizarException extends RuntimeException {
    public ContatoAtualizarException(String codigo) {
        super("Código do produto solicitado não existe na base para atualizar.");
    }
}
