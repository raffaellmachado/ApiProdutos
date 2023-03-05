package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.JsonRequest;
import br.com.bling.ApiContatos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContatoService {

    public JsonResponse getAllContacts();

    public JsonResponse getContactsById(@PathVariable String cpf_cnpj);

    public JsonRequest createContact(@RequestBody String xml);

    public JsonRequest updateContact(@RequestBody String xml, @PathVariable String id);
}
