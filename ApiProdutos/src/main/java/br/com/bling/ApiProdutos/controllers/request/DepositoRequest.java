package br.com.bling.ApiProdutos.controllers.request;

import lombok.*;

import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Data
public class DepositoRequest {

    @Size(max = 120)
    public String descricao;
    @Size(max= 1, message = "A ou I")
    public String situacao = "A";
    public boolean depositoPadrao = false;
    public boolean desconsiderarSaldo = false;

}


