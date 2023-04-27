package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransporteRequest {

    @JsonProperty("transportadora")
    public String transportadora;

    @JsonProperty("tipo_frete")
    public String tipo_frete;

    @JsonProperty("servico_correios")
    public String servico_correios;

    @JsonProperty("codigo_cotacao")
    public String  codigo_cotacao;

    @JsonProperty("peso_bruto")
    public BigDecimal peso_bruto;

    @JsonProperty("qtde_volumes")
    public BigInteger qtde_volumes;

    @JsonProperty("dados_etiqueta")
    public DadosEtiquetaRequest dados_etiqueta;

    @JsonProperty("volumes")
    public VolumesRequest volumes;
}
