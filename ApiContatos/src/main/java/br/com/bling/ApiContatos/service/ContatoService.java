package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.JsonRequest;
import br.com.bling.ApiContatos.controllers.response.ContatoResponse;
import br.com.bling.ApiContatos.controllers.response.JsonResponse;
import br.com.bling.ApiContatos.controllers.response.RetornoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ContatoService {

//    public JsonResponse getAllContacts();

    public JsonResponse getAllContacts();

    public JsonResponse getContactsById(@PathVariable("id") String id);

    public JsonRequest createContact(@RequestBody String xmlContato);

    public JsonRequest updateContact(@RequestBody @Valid String xmlContato, @PathVariable("id") String id);
}
