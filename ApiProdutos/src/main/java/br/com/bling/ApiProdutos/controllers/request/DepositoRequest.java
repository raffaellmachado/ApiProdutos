package br.com.bling.ApiProdutos.controllers.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DepositoRequest {

    public String descricao;
    public String situacao;
    public boolean depositoPadrao;
    public boolean desconsiderarSaldo;
}
