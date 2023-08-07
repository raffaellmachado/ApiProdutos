package br.com.bling.ApiContatos.controllers;

import br.com.bling.ApiContatos.controllers.request.JsonRequest;
import br.com.bling.ApiContatos.controllers.response.JsonResponse;
import br.com.bling.ApiContatos.controllers.response.RetornoResponse;
import br.com.bling.ApiContatos.exceptions.*;
import br.com.bling.ApiContatos.service.ContatoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")   //Padrão para os métodos /api/...
@Api(value = "API REST CONTATOS")    //Swagger
@CrossOrigin(origins = "*")          // Liberar os dominios da API
public class ContatoController {

    @Autowired
    public ContatoService contatosService;

    /**
     * GET "BUSCAR LISTA DE CONTATOS".
     */
    @GetMapping("/contatos")
    @ApiOperation(value = "Retorna uma lista de contatos")
    public ResponseEntity<JsonResponse> getAllContacts() {
        try {
            JsonResponse request = contatosService.getAllContacts();

            if (request.retorno.contatos == null && request.retorno.erros == null) {
                throw new ContatoListaException("Nenhum contato foi localizado.", null);
            }

            System.out.println("Retorno GET: " + request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (Exception e) {
            throw new ApiContatoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * GET "BUSCAR UM CONTATO PELO CPF ou CNPJ".
     */
    @GetMapping("/contato/{id}")
    @ApiOperation(value = "Retorna um contato pelo CPF ou CNPJ")
    public ResponseEntity<JsonResponse> getContactsById(@PathVariable String id) {
        try {
            JsonResponse request = contatosService.getContactsById(id);

            if (request.retorno.contatos == null && request.retorno.erros == null) {
                throw new ContatoIdException("Contato com o número de CPF/CNPJ: " + id + " não localizado.", null);
            }

            System.out.println("Retorno GET CPF/CNPJ:" + request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ApiContatoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO CONTATO UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcontato", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo contato")
    public ResponseEntity<JsonRequest> createContact(@RequestBody String xml) {
        try {
            JsonRequest request = contatosService.createContact(xml);

            if (request.retorno.contatos == null && request.retorno.erros == null) {
                throw new ContatoCadastroException("Cadastro não efetuado, revise os campos e tente novamente!", null);
            }

            System.out.println("Retorno POST: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiContatoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * PUT "ATUALIZAR CONTATO PELO CPF e CNPJ UTILIZANDO XML".
     */
    @PutMapping(path = "/atualizarcontato/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um contato existente")
    public ResponseEntity<JsonRequest> updateContact(@RequestBody @Valid String xmlContato, @PathVariable("id") String id) {
        try {
            JsonRequest request = contatosService.updateContact(xmlContato, id);

            if (request.retorno.contatos == null && request.retorno.erros == null) {
                throw new ContatoAtualizarException("Não foi possivel atualizar o contato.", null);
            }

            System.out.println("Retorno UPDATE: \n " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiContatoException("Houve algum erro sistemico, tente novamente", e);
        }
    }
}