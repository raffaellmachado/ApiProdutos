package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.JsonRequest;
import br.com.bling.ApiContatos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ContatoService {

    public JsonResponse getAllContacts();

    public JsonResponse getContactsById(@PathVariable("cpf_cnpj") String cpf_cnpj);

    public JsonRequest createContact(@RequestBody String xmlContato);

    public JsonRequest updateContact(@RequestBody @Valid String xmlContato, @PathVariable("cpf_cnpj") String cpf_cnpj);
}
