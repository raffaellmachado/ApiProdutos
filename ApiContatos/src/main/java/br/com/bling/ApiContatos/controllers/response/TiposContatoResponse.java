package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
//@Entity
//@Table(name = "TB_TIPOS_CONTATO_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiposContatoResponse implements Serializable {

//    @Id
    @JsonIgnore
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contato_response_id", referencedColumnName = "id")
//    @JsonBackReference // Anotação para indicar que esta é a ponta "de volta" da relação
    public ContatoResponse contatoResponse;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tipo_contato_id")
    public TipoContatoResponse tipoContato;
}





