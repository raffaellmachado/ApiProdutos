package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.RespostaRequest;
import br.com.bling.ApiContatos.controllers.response.RespostaResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContatoService {

    public RespostaResponse getAllContacts();

    public RespostaResponse getContactsById(@PathVariable String cpf_cnpj);

    public RespostaRequest createContact(@RequestBody String xml);

    public String updateContact(@RequestBody String xml, @PathVariable String id);
}
