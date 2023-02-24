package br.com.bling.ApiContatos.exceptions;

public class ContatoCadastroException extends RuntimeException {
    public ContatoCadastroException() {

        super("Cadastro não efetuado, revise os campos e tente novamente.\n" +
                "\n" +
                "Os campos obrigatorios para realizar o cadastro são:\n" +
                "Nome: Nome do cliente ou fornecedor.\n" +
                "Tipo pessoa: F - Fisica, J - Juridica ou E - Estrangeira \n" +
                "contribuinte: 1 - Contribuinte do ICMS, 2 - Contribuinte isento do ICMS ou 9 - Não contribuinte \n" +
                "CPF ou CNPJ: Necessario que seja um CPF ou um CNPJ válido");
    }
}
