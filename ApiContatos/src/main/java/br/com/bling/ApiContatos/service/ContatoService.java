package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.JsonRequest;
import br.com.bling.ApiContatos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ContatoService {

    JsonResponse getAllContacts();

    JsonResponse getContactsById(@PathVariable("cnpj") String cnpj);

    JsonRequest createContact(@RequestBody String xmlContato);

    JsonRequest updateContact(@RequestBody @Valid String xmlContato, @PathVariable("id") String id);
}
