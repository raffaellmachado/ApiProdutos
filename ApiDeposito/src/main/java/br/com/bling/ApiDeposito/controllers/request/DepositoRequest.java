package br.com.bling.ApiDeposito.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositoRequest {

    @JsonProperty("id")
    public String id;
    @NotEmpty(message = "Campo Obrigatorio")
    @Max(value = 120, message = "Descrição do depósito")
    @JsonProperty("descricao")
    public String descricao;
    @Pattern(regexp = "[AI]", message = "Situação do depósito (A ou I)")
    @JsonProperty("situacao")
    public String situacao = "A";
    @NotNull(message = "Define se o depósito vai ser o padrão")
    @JsonProperty("depositoPadrao")
    public boolean depositoPadrao = false;
    @NotNull(message = "Desconsidera saldo deste depósito")
    @JsonProperty("desconsiderarSaldo")
    public boolean desconsiderarSaldo = false;
}


