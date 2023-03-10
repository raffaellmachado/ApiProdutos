package br.com.bling.ApiDepositos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositoResponse {

    @JsonProperty("id")
    public String id;
    @JsonProperty("descricao")
    public String descricao;
    @JsonProperty("situacao")
    public String situacao;
    @JsonProperty("depositoPadrao")
    public boolean depositoPadrao = false;
    @JsonProperty("desconsiderarSaldo")
    public boolean desconsiderarSaldo = false;

}
