package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.controllers.request.JsonRequest;
import br.com.bling.ApiDeposito.controllers.response.JsonResponse;
import br.com.bling.ApiDeposito.controllers.response.RetornoResponse;
import br.com.bling.ApiDeposito.exceptions.*;
import br.com.bling.ApiDeposito.services.DepositoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST DEPOSITOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class DepositoController {

    @Autowired
    public DepositoService depositoService;


    /**
     * GET "BUSCA LISTA DE DEPOSITOS".
     */
    @GetMapping("/depositos")
    @ApiOperation(value = "Retorna uma lista de depositos")
    public ResponseEntity<JsonResponse> getAllDeposit() {
        try {
            JsonResponse request = depositoService.getAllDeposit();

            if (request.retorno.depositos == null && request.retorno.erros == null) {
                throw new DepositoListaException("Não foi possível localizar a lista de depositos");
            }

            if (request.retorno.depositos != null) {
                for (RetornoResponse.Depositos listaDepositos : request.getRetorno().getDepositos()) {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Id Deposito: " + listaDepositos.deposito.id);
                    System.out.println("Descrição: " + listaDepositos.deposito.descricao);
                    System.out.println("Situação: " + listaDepositos.deposito.situacao);
                    System.out.println("Deposito Padrão: " + listaDepositos.deposito.depositoPadrao);
                    System.out.println("Desconsiderar Saldo: " + listaDepositos.deposito.desconsiderarSaldo);
                    System.out.println("-------------------------------------------------------------------");
                }
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ApiDepositoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    @GetMapping("/deposito/{idDeposito}")
    @ApiOperation(value = "Retorna um deposito pelo idDeposito")
    public ResponseEntity<JsonResponse> getDepositById(@PathVariable String idDeposito) {
        try {
            JsonResponse request = depositoService.getDepositById(idDeposito);

            if (request.retorno.depositos == null && request.retorno.erros == null) {
                throw new DepositoIdDepositoException("Contato com o número de CPF/CNPJ: " + idDeposito + " não encontrado.");
            }

            if (request.retorno.depositos != null) {
                for (RetornoResponse.Depositos listaDepositos : request.getRetorno().getDepositos()) {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Id Deposito: " + listaDepositos.deposito.id);
                    System.out.println("Descrição: " + listaDepositos.deposito.descricao);
                    System.out.println("Situação: " + listaDepositos.deposito.situacao);
                    System.out.println("Deposito Padrão: " + listaDepositos.deposito.depositoPadrao);
                    System.out.println("Desconsiderar Saldo: " + listaDepositos.deposito.desconsiderarSaldo);
                    System.out.println("-------------------------------------------------------------------");
                }
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ApiDepositoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo deposito")
    public ResponseEntity<JsonRequest> createDeposit(@RequestBody @Valid String xmlDeposito) {
        try {
            JsonRequest request = depositoService.createDeposit(xmlDeposito);

            if (request.retorno.depositos == null && request.retorno.erros == null) {
                throw new DepositoCadastroException("Cadastro não efetuado, revise os campos e tente novamente!");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (Exception e) {
            throw new ApiDepositoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * PUT "ATUALIZAR UM DEPOSITO EXISTENTE UTILIZANDO XML".
     */
    @PutMapping(path = "/atualizardeposito/{idDeposito}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<JsonRequest> updateDeposit(@RequestBody @Valid String xmlDeposito, @PathVariable String idDeposito) {
        try {
            JsonRequest request = depositoService.updateDeposit(xmlDeposito, idDeposito);

            if (request.retorno.depositos == null && request.retorno.erros == null) {
                throw new DepositoAtualizarException("Cadastro não efetuado, revise os campos e tente novamente!");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (Exception e) {
            throw new ApiDepositoException("Houve algum erro sistemico, tente novamente", e);
        }
    }
}