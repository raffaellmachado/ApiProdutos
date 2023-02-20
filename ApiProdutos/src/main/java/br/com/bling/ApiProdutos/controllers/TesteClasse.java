package br.com.bling.ApiProdutos.controllers;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class TesteClasse {
    @NonNull
    private String codigo;
    private String descricao;
    private String situacao;

}
