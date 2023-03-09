package br.com.bling.ApiContatos.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class RespostaApi {

    private RetornoRequest retorno;
@Data
    public class RetornoRequest {
        @JsonProperty("id")
        public String id;

        @JsonProperty("data")
        public String data;
        @JsonProperty("erros")
        private Map<String, String> erros;
    }
}
