package br.com.bling.ApiContatos.controllers.request;

import br.com.bling.ApiContatos.controllers.response.ContatoResponse;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_TIPOS_CONTATO_REQUEST")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiposContatoRequest  implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_request_id", referencedColumnName = "id")
    @JsonBackReference // Anotação para indicar que esta é a ponta "de volta" da relação
    public ContatoRequest contatoRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_contato_id")
    public TipoContatoRequest tipoContato;
}
