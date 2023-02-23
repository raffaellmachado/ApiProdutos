package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.response.Resposta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContatoService {

    public Resposta getAllContacts();

    public Resposta getContactsById(@PathVariable String cpf_cnpj);

    public String createContact(@RequestBody String xml);

    public String updateContact(@RequestBody String xml, @PathVariable String cpf_cnpj);
}
