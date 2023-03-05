package br.com.bling.ApiContatos.exceptions;

public class ContatoIdException extends RuntimeException {
    public ContatoIdException(String cpf_cnpj) {
        super("Contato com o número de CPF/CNPJ: " + cpf_cnpj + " não encontrado.");
    }
}
