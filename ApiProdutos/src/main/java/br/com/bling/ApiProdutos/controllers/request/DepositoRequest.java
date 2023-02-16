package br.com.bling.ApiProdutos.controllers.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class DepositoRequest {

    @NotEmpty
    @Max(value = 120, message = "Descrição do depósito")
    public String descricao;
    @NotEmpty
    @Max(value= 1, message = "Situação do depósito (A ou I)")
    public String situacao = "A";
    @Size(message = "Define se o depósito vai ser o padrão")
    public boolean depositoPadrao = false;
    @Size(message = "Desconsidera saldo deste depósito")
    public boolean desconsiderarSaldo = false;

}


