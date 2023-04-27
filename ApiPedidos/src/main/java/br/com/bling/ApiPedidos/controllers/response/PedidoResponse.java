package br.com.bling.ApiPedidos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
//@Entity
//@Table(name = "TB_CATEGORIA_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoResponse {

    @JsonProperty("desconto")
    public String desconto;

    @JsonProperty("observacoes")
    public String observacoes;

    @JsonProperty("observacaointerna")
    public String observacaointerna;

    @JsonProperty("data")
    public String data;

    @JsonProperty("numero")
    public String numero;

    @JsonProperty("numeroOrdemCompra")
    public String numeroOrdemCompra;

    @JsonProperty("vendedor")
    public String vendedor;

    @JsonProperty("valorfrete")
    public String valorfrete;

    @JsonProperty("outrasdespesas")
    public String outrasdespesas;

    @JsonProperty("totalprodutos")
    public String totalprodutos;

    @JsonProperty("totalvenda")
    public String totalvenda;

    @JsonProperty("situacao")
    public String situacao;

    @JsonProperty("dataSaida")
    public String dataSaida;

    @JsonProperty("loja")
    public String loja;

    @JsonProperty("dataPrevista")
    public String dataPrevista;

    @JsonProperty("cliente")
    public ClienteResponse cliente;

    @JsonProperty("pagamento")
    public PagamentoResponse pagamento;

    @JsonProperty("transporte")
    public TransporteResponse transporte;

    @JsonProperty("itens")
    public ArrayList<ItensResponse> itens;

    @JsonProperty("parcelas")
    public ArrayList<ParcelasResponse> parcelas;
}
