package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.controllers.request.JsonRequest;
import br.com.bling.ApiDeposito.controllers.response.JsonResponse;
import br.com.bling.ApiDeposito.controllers.response.RetornoResponse;
import br.com.bling.ApiDeposito.exceptions.ApiDepositoException;
import br.com.bling.ApiDeposito.exceptions.DepositoIdDepositoException;
import br.com.bling.ApiDeposito.exceptions.DepositoListaException;
import br.com.bling.ApiDeposito.services.DepositoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

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
    public ResponseEntity<JsonResponse> getCategoria() {
        try {
            JsonResponse request = depositoService.getAllDeposit();

            if (request.retorno.depositos == null || request.getRetorno() == null) {
                throw new ApiDepositoException("Não foi possível localizar a lista de depositos");
            }
            for (RetornoResponse.Depositos listaDepositos : request.getRetorno().getDepositos()) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Id Deposito: " + listaDepositos.deposito.id);
                System.out.println("Descrição: " + listaDepositos.deposito.descricao);
                System.out.println("Situação: " + listaDepositos.deposito.situacao);
                System.out.println("Deposito Padrão: " + listaDepositos.deposito.depositoPadrao);
                System.out.println("Desconsiderar Saldo: " + listaDepositos.deposito.desconsiderarSaldo);
                System.out.println("-------------------------------------------------------------------");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new DepositoListaException();
        }
    }

    /**
     * GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    @GetMapping("/deposito/{idDeposito}")
    @ApiOperation(value = "Retorna um deposito pelo idDeposito")
    public ResponseEntity<JsonResponse> getDepositByIdDeposit(@PathVariable String idDeposito) {
        try {
            JsonResponse request = depositoService.getDepositByIdDeposit(idDeposito);

            if (request.retorno.depositos == null || request.getRetorno() == null) {
                throw new ApiDepositoException("Não foi possível localizar produto fornecedor pelo IdDeosito");
            }

            for (RetornoResponse.Depositos listaDepositos : request.getRetorno().getDepositos()) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Id Deposito: " + listaDepositos.deposito.id);
                System.out.println("Descrição: " + listaDepositos.deposito.descricao);
                System.out.println("Situação: " + listaDepositos.deposito.situacao);
                System.out.println("Deposito Padrão: " + listaDepositos.deposito.depositoPadrao);
                System.out.println("Desconsiderar Saldo: " + listaDepositos.deposito.desconsiderarSaldo);
                System.out.println("-------------------------------------------------------------------");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new DepositoIdDepositoException(idDeposito);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo deposito")
    public ResponseEntity<?> createDeposit(@RequestBody @Valid String xml) {
        try {
            Object request = depositoService.createDeposit(xml);

            if (request == null) {
                throw new ApiDepositoException("Não foi possível cadastrar o deposito");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (ApiDepositoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new JsonRequest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonRequest());
        }
    }

    /**
     * PUT "ATUALIZAR UM DEPOSITO EXISTENTE UTILIZANDO XML".  -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizardeposito/{idDeposito}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<?> updateDeposit(@RequestBody @Valid String xml, @PathVariable String idDeposito) {
        try {
            Object request = depositoService.updateDeposit(xml, idDeposito);

            if (request == null) {
                throw new ApiDepositoException("Não foi possível cadastrar o deposito");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (ApiDepositoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new JsonRequest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonRequest());
        }
    }
//    @PutMapping(path = "/atualizardeposito/{idDeposito}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Cadastrar uma categoria")
//    public ResponseEntity<RespostaRequest> updateCategory(@RequestBody String xml, @PathVariable String idDeposito) {
//        try {
//            RespostaRequest request = depositoService.updateDeposit(xml, idDeposito);
//
//            if (request.retorno.depositos == null || request.getRetorno() == null) {
//                throw new ApiDepositoException("Não foi possível atualizar o deposito");
//            }
//            System.out.println(request);
//
//            return ResponseEntity.status(HttpStatus.OK).body(request);
//        } catch (Exception e) {
//            throw new DepositoCadastroException();
//        }
//    }
}