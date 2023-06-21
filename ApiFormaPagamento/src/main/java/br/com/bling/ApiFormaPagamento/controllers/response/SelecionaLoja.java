package br.com.bling.ApiFormaPagamento.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_SELECIONA_LOJA")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SelecionaLoja {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String idLoja;

    public String nomeLoja;

    public String unidadeLoja;

}
