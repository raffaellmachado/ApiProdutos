package br.com.bling.ApiFormaPagamento.controllers;


import br.com.bling.ApiFormaPagamento.exceptions.ApiFormaPagamentoException;
import br.com.bling.ApiFormaPagamento.service.FormaPagamentoService;
import br.com.bling.ApiFormaPagamento.controllers.request.JsonRequest;
import br.com.bling.ApiFormaPagamento.controllers.response.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")          //Padrão para os métodos /api/...
@Api(value = "API REST FORMA PAGAMENTO")    //Swagger
@CrossOrigin(origins = "*")                 // Liberar os dominios da API
@Validated
public class FormaPagamentoController {

    @Autowired
    public FormaPagamentoService formaPagamentoService;


    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    @GetMapping("/formaspagamento")
    @ApiOperation(value = "Retorna uma lista de formas de pagamento")
    public ResponseEntity<JsonResponse> getAllFormaPagamento() {
        try {
            JsonResponse request = formaPagamentoService.getAllFormaPagamento();

            System.out.println("GET: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiFormaPagamentoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/formapagamento/{id}")
    @ApiOperation(value = "Retorna uma forma de pagamento pelo ID")
    public ResponseEntity<JsonResponse> getFormaPagamentoById(@PathVariable("id") String id) {
        try {
            JsonResponse request = formaPagamentoService.getFormaPagamentoById(id);

            System.out.println("GET ID: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiFormaPagamentoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/formapagamento/{id}")
    @ApiOperation(value = "Deleta uma forma de pagamento pelo ID")
    public ResponseEntity<String> deleteProductByCode(@PathVariable String id) {
        try {
            ResponseEntity<String> request = formaPagamentoService.deleteFormaPagemento(id);

            System.out.println("Codigo deletado = " + id);

            return request;

        } catch (Exception e) {
            throw new ApiFormaPagamentoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarformapagamento", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastra uma forma de pagamento")
    public ResponseEntity<JsonRequest> createFormaPagamento(@RequestBody @Valid String xmlFormaPagamento) {
        try {
            JsonRequest request = formaPagamentoService.createFormaPagamento(xmlFormaPagamento);

            System.out.println("POST: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiFormaPagamentoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".
     */
    @PutMapping(path = "/atualizarformapagamento/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um pedido")
    public ResponseEntity<JsonRequest> updateFormaPagamento(@RequestBody String xmlFormaPagamento, @PathVariable("id") String id) {
        try {
            JsonRequest request = formaPagamentoService.updateFormaPagamento(xmlFormaPagamento, id);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiFormaPagamentoException("Houve algum erro sistemico, tente novamente", e);
        }
    }
}