package br.com.bling.ApiProdutosFornecedores.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RespostaApi {
    @JsonProperty("retorno")
    private RetornoRequest retornoRequest;

    @Data
    public static class RetornoRequest {
        private List<Erro> erros;
    }

    @Data
    public static class Erro {
        private int cod;
        private String msg;

        @JsonProperty("erro")
        private void unpackErro(Map<String, Object> erroMap) {
            if (erroMap.containsKey("cod")) {
                this.cod = (Integer) erroMap.get("cod");
            }
            if (erroMap.containsKey("msg")) {
                this.msg = (String) erroMap.get("msg");
            }
        }
    }

}
