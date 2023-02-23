package br.com.bling.ApiDeposito.controllers.response;

import lombok.Data;

@Data
public class Deposito {


    public String id;
    public String descricao;
    public String situacao = "A";
    public boolean depositoPadrao = false;
    public boolean desconsiderarSaldo = false;

}