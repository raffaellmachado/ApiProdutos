package br.com.bling.ApiDeposito.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositoResponse {

    public String id;
    public String descricao;
    public String situacao = "A";
    public boolean depositoPadrao = false;
    public boolean desconsiderarSaldo = false;

}
