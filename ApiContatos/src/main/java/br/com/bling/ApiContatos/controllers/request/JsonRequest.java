package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRequest {

    @JsonProperty("retorno")
    public RetornoRequest retorno;

    @JsonProperty("success")
    private String success;

    @JsonProperty("msg")
    private String msg;

    public JsonRequest(String msg) {
        this.msg = msg;
    }
}
