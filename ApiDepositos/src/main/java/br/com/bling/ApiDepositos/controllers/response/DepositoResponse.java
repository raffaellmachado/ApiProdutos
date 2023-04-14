package br.com.bling.ApiDepositos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_DEPOSITO_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositoResponse {

    @Id
    @JsonProperty("id")
    public Long id;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("situacao")
    public String situacao;

    @JsonProperty("depositoPadrao")
    public boolean depositoPadrao = false;

    @JsonProperty("desconsiderarSaldo")
    public boolean desconsiderarSaldo = false;

}
