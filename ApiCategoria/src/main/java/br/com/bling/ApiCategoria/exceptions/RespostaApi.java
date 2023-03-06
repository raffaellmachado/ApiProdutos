package br.com.bling.ApiCategoria.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class RespostaApi {
    private RetornoRequest retorno;

    public class RetornoRequest {
        private String id;
        private String data;

        @JsonProperty("erros")
        private Map<String, String> erros;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Map<String, String> getErros() {
            return erros;
        }

        public void setErros(Map<String, String> erros) {
            this.erros = erros;
        }

    }

}
