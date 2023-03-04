package br.com.bling.ApiProdutos.exceptions;

public class ProdutoAtualizarException  extends RuntimeException {
    public ProdutoAtualizarException(String codigo) {
        super("Código do produto " + codigo + " solicitado não existe na base para atualizar.");
    }
}
