package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.controllers.request.RespostaRequest;
import br.com.bling.ApiDeposito.controllers.request.RetornoRequest;
import br.com.bling.ApiDeposito.controllers.response.RespostaResponse;
import br.com.bling.ApiDeposito.controllers.response.RetornoResponse;
import br.com.bling.ApiDeposito.exceptions.ApiDepositoException;
import br.com.bling.ApiDeposito.exceptions.DepositoCadastroException;
import br.com.bling.ApiDeposito.exceptions.DepositoIdDepositoNaoEncontradoException;
import br.com.bling.ApiDeposito.exceptions.DepositoListaNaoEncontradoException;
import br.com.bling.ApiDeposito.services.DepositoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public RespostaResponse getCategoria() {
        try {
            RespostaResponse request = depositoService.getAllDeposit();

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

            return request;
        } catch (Exception e) {
            throw new DepositoListaNaoEncontradoException();
        }
    }

    /**
     * GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    @GetMapping("/deposito/{idDeposito}")
    @ApiOperation(value = "Retorna um deposito pelo idDeposito")
    public RespostaResponse getDepositByIdDeposit(@PathVariable String idDeposito) {
        try {
            RespostaResponse request = depositoService.getDepositByIdDeposit(idDeposito);

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

            return request;
        } catch (Exception e) {
            throw new DepositoIdDepositoNaoEncontradoException(idDeposito);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo deposito")
    public RespostaRequest createDeposit(@RequestBody String xml) {
        try {
            RespostaRequest request = depositoService.createDeposit(xml);

            if (request.retorno.depositos == null || request.getRetorno() == null) {
                throw new ApiDepositoException("Não foi possível cadastrar o deposito");
            }

            for (ArrayList<RetornoRequest.Deposito> listaDepositos : request.getRetorno().getDepositos()) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Id Deposito: " + listaDepositos.get(0).deposito.id);
                System.out.println("Descrição: " + listaDepositos.get(0).deposito.descricao);
                System.out.println("Situação: " + listaDepositos.get(0).deposito.situacao);
                System.out.println("Deposito Padrão: " + listaDepositos.get(0).deposito.depositoPadrao);
                System.out.println("Desconsiderar Saldo: " + listaDepositos.get(0).deposito.desconsiderarSaldo);
                System.out.println("-------------------------------------------------------------------");
            }
            System.out.println(request);

            return request;
        } catch (Exception e) {
            throw new DepositoCadastroException();
        }
    }

    /**
     * PUT "ATUALIZAR UM DEPOSITO EXISTENTE UTILIZANDO XML".  -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizardeposito/{idDeposito}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public RespostaRequest updateCategory(@RequestBody String xml, @PathVariable String idDeposito) {
        try {
            RespostaRequest request = depositoService.updateDeposit(xml, idDeposito);

            if (request.retorno.depositos == null || request.getRetorno() == null) {
                throw new ApiDepositoException("Não foi possível atualizar o deposito");
            }
            System.out.println(request);

            return request;
        } catch (Exception e) {
            throw new DepositoCadastroException();
        }
    }
}