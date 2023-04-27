package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoRequest {

    @JsonProperty("idPedido")
    public String idPedido;

    @JsonProperty("idContato")
    public String idContato;

    @JsonProperty("data")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public String data;

    @JsonProperty("data_saida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public String data_saida;

    @JsonProperty("data_prevista")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public String data_prevista;

    @JsonProperty("numero")
    public String numero;

    @JsonProperty("numero_loja")
    public String numero_loja;

    @JsonProperty("loja")
    public Integer loja;

    @JsonProperty("nat_operacao")
    public String nat_operacao;

    @JsonProperty("vendedor")
    public String vendedor;

    @JsonProperty("cliente")
    public ClienteRequest cliente;

    @JsonProperty("transporte")
    public TransporteRequest transporte;

    @JsonProperty("itens")
    public ItensRequest itens;

    @JsonProperty("idFormaPagamento")
    public Integer idFormaPagamento;

    @JsonProperty("parcelas")
    public ParcelasRequest parcelas;

    @JsonProperty("vlr_frete")
    public BigDecimal vlr_frete;

    @JsonProperty("vlr_desconto")
    public String vlr_desconto;

    @JsonProperty("obs")
    public String obs;

    @JsonProperty("obs_internas")
    public String obs_internas;

    @JsonProperty("numeroOrdemCompra")
    public String numeroOrdemCompra;

    @JsonProperty("outrasDespesas")
    public BigDecimal outrasDespesas;

    @JsonProperty("intermediador")
    public IntermediadorRequest intermediador;
}
