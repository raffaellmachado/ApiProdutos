package br.com.bling.ApiContatos.controllers;

import br.com.bling.ApiContatos.exceptions.*;
import br.com.bling.ApiContatos.controllers.response.Resposta;
import br.com.bling.ApiContatos.controllers.response.Retorno;
import br.com.bling.ApiContatos.service.ContatoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST CONTATOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class ContatoController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ContatoService contatosService;

    /**
     * GET "BUSCAR LISTA DE CONTATOS".
     */
    @GetMapping("/contatos")
    @ApiOperation(value = "Retorna uma lista de contatos")
    public Resposta getAllContacts() {
        Resposta request = contatosService.getAllContacts();

        if (request.retorno.contatos == null || request.getRetorno() == null) {
            throw new ContatoListaNaoEncontradoException();
        }

        for (Retorno.Contato listaContatos : request.getRetorno().getContatos()) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Id: " + listaContatos.contato.id);
            System.out.println("codigo: " + listaContatos.contato.codigo);
            System.out.println("nome: " + listaContatos.contato.nome);
            System.out.println("fantasia: " + listaContatos.contato.fantasia);
            System.out.println("tipo: " + listaContatos.contato.tipo);
            System.out.println("cnpj: " + listaContatos.contato.cnpj);
            System.out.println("cpf_cnpj: " + listaContatos.contato.cpf_cnpj);
            System.out.println("ie_rg: " + listaContatos.contato.ie_rg);
            System.out.println("endereco: " + listaContatos.contato.endereco);
            System.out.println("numero: " + listaContatos.contato.numero);
            System.out.println("bairro: " + listaContatos.contato.bairro);
            System.out.println("cep: " + listaContatos.contato.cep);
            System.out.println("cidade: " + listaContatos.contato.cidade);
            System.out.println("complemento: " + listaContatos.contato.complemento);
            System.out.println("uf: " + listaContatos.contato.uf);
            System.out.println("fone: " + listaContatos.contato.fone);
            System.out.println("email: " + listaContatos.contato.email);
            System.out.println("situacao: " + listaContatos.contato.situacao);
            System.out.println("contribuinte: " + listaContatos.contato.contribuinte);
            System.out.println("site: " + listaContatos.contato.site);
            System.out.println("celular: " + listaContatos.contato.celular);
            System.out.println("dataAlteracao: " + listaContatos.contato.dataAlteracao);
            System.out.println("dataInclusao: " + listaContatos.contato.dataInclusao);
            System.out.println("sexo: " + listaContatos.contato.sexo);
            System.out.println("clienteDesde: " + listaContatos.contato.clienteDesde);
            System.out.println("limiteCredito: " + listaContatos.contato.limiteCredito);
            System.out.println("tiposContato: " + listaContatos.contato.tiposContato);
            System.out.println("dataNascimento: " + listaContatos.contato.dataNascimento);
        }

        System.out.println(request);

        return request;
    }

    /**
     * GET "BUSCAR UM CONTETO PELO CPF ou CNPJ".
     */
    @GetMapping("/contato/{cpf_cnpj}")
    @ApiOperation(value = "Retorna um contato pelo CPF ou CNPJ")
    public Resposta getContactsById(@PathVariable String cpf_cnpj) {
        Resposta request = contatosService.getContactsById(cpf_cnpj);

        if (request.retorno.contatos == null || request.getRetorno() == null) {
            throw new ContatoIdNaoEncontradoException(cpf_cnpj);
        }

        for (Retorno.Contato listaContatos : request.getRetorno().getContatos()) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Id: " + listaContatos.contato.id);
            System.out.println("codigo: " + listaContatos.contato.codigo);
            System.out.println("nome: " + listaContatos.contato.nome);
            System.out.println("fantasia: " + listaContatos.contato.fantasia);
            System.out.println("tipo: " + listaContatos.contato.tipo);
            System.out.println("cnpj: " + listaContatos.contato.cnpj);
            System.out.println("cpf_cnpj: " + listaContatos.contato.cpf_cnpj);
            System.out.println("ie_rg: " + listaContatos.contato.ie_rg);
            System.out.println("endereco: " + listaContatos.contato.endereco);
            System.out.println("numero: " + listaContatos.contato.numero);
            System.out.println("bairro: " + listaContatos.contato.bairro);
            System.out.println("cep: " + listaContatos.contato.cep);
            System.out.println("cidade: " + listaContatos.contato.cidade);
            System.out.println("complemento: " + listaContatos.contato.complemento);
            System.out.println("uf: " + listaContatos.contato.uf);
            System.out.println("fone: " + listaContatos.contato.fone);
            System.out.println("email: " + listaContatos.contato.email);
            System.out.println("situacao: " + listaContatos.contato.situacao);
            System.out.println("contribuinte: " + listaContatos.contato.contribuinte);
            System.out.println("site: " + listaContatos.contato.site);
            System.out.println("celular: " + listaContatos.contato.celular);
            System.out.println("dataAlteracao: " + listaContatos.contato.dataAlteracao);
            System.out.println("dataInclusao: " + listaContatos.contato.dataInclusao);
            System.out.println("sexo: " + listaContatos.contato.sexo);
            System.out.println("clienteDesde: " + listaContatos.contato.clienteDesde);
            System.out.println("limiteCredito: " + listaContatos.contato.limiteCredito);
            System.out.println("tiposContato: " + listaContatos.contato.tiposContato);
            System.out.println("dataNascimento: " + listaContatos.contato.dataNascimento);
        }

        System.out.println(request);

        return request;
    }

    /**
     * POST "CADASTRAR UM NOVO CONTATO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarcontato", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo contato")
    public String createContact(@RequestBody String xml) {

        String request = contatosService.createContact(xml);

        System.out.println(request);
        return request;
    }

    /**
     * POST "ATUALIZAR CONTATO PELO CPF e CNPJ" UTILIZANDO XML.
     */
    @PostMapping(path = "/atualizarcontato/{codigo}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um produto existente")
    public String updateContact(@RequestBody String xml, @PathVariable String cpf_cnpj) {

        String request = contatosService.updateContact(xml, cpf_cnpj);

        System.out.println("Produto atualizado com sucesso!");

            return request;

    }
}