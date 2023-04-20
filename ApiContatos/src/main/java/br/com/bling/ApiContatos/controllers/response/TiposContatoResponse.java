package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_TIPOS_CONTATO_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TiposContatoResponse {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idbd;

    @JsonProperty("id")
    public Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contato_id")
    public ContatoResponse contato;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipo_contato_id")
    @JsonProperty("tipoContato")
    public TipoContatoResponse tipoContato;
}





