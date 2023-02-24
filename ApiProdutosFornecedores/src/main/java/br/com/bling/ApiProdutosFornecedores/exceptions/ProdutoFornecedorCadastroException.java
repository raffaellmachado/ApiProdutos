package br.com.bling.ApiProdutosFornecedores.exceptions;

public class ProdutoFornecedorCadastroException extends RuntimeException {
    public ProdutoFornecedorCadastroException() {

        super("Cadastro não efetuado, revise os campos e tente novamente.\n" +
                "\n" +
                "Verifique se o produto que deseja vincular ao fornecedor já não está vinculado! \n" +
                "\n" +
                "Os campos obrigatorios para realizar o cadastro são:\n" +
                "idProduto: Identificador único do produto (Necessário que exista uo produto cadastrado).\n" +
                "idFornecedor: Identificador único do contato fornecedor (Necessario que exista um fornecedor cadastrado).");
    }
}
