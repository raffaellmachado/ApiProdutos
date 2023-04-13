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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_response_id", referencedColumnName = "id")
    public ContatoResponse contatoResponse;

    @JsonProperty("tipoContato")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_contato_response_id", referencedColumnName = "id")
    public TipoContatoResponse tipoContato;

}





