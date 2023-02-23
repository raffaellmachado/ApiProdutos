package br.com.bling.ApiContatos.exceptions;

public class ContatoIdNaoEncontradoException extends RuntimeException {
    public ContatoIdNaoEncontradoException(String cpf_cnpj) {
        super("Contato com o número de CPF/CNPJ: " + cpf_cnpj + " não encontrado.");
    }
}
