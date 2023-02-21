package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.exceptions.DepositoIdDepositoNaoEncontradoException;
import br.com.bling.ApiDeposito.exceptions.DepositoListaNaoEncontradoException;
import br.com.bling.ApiDeposito.models.Resposta;
import br.com.bling.ApiDeposito.models.Retorno;
import br.com.bling.ApiDeposito.service.DepositoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST DEPOSITOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class DepositoController {

    @Autowired
    public DepositoService depositoService;
    private String idDeposito;

    /**
     * GET "BUSCA LISTA DE DEPOSITOS".
     */
    @GetMapping("/depositos")
    @ApiOperation(value = "Retorna uma lista de depositos")
    public Resposta getCategoria() {
        Resposta request = depositoService.getAllDeposit();
        for (Retorno.Deposito listaDepositos : request.getRetorno().getDepositos()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Id Deposito: " + listaDepositos.deposito.id);
            System.out.println("Descrição: " + listaDepositos.deposito.descricao);
            System.out.println("Situação: " + listaDepositos.deposito.situacao);
            System.out.println("Deposito Padrão: " + listaDepositos.deposito.depositoPadrao);
            System.out.println("Desconsiderar Saldo: " + listaDepositos.deposito.desconsiderarSaldo);
            System.out.println("-------------------------------------------------------------------");
        }

        if (request.retorno.depositos == null || request.getRetorno() == null) {
            throw new DepositoListaNaoEncontradoException();
        }

        System.out.println(request);

        return request;
    }

    /**
     * GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    @GetMapping("/deposito/{idDeposito}")
    @ApiOperation(value = "Retorna um deposito pelo idDeposito")
    public Resposta getDepositByIdDeposit(@PathVariable String idDeposito) {
        Resposta request = depositoService.getDepositByIdDeposit(idDeposito);
        for (Retorno.Deposito listaDepositos : request.getRetorno().getDepositos()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Id Deposito: " + listaDepositos.deposito.id);
            System.out.println("Descrição: " + listaDepositos.deposito.descricao);
            System.out.println("Situação: " + listaDepositos.deposito.situacao);
            System.out.println("Deposito Padrão: " + listaDepositos.deposito.depositoPadrao);
            System.out.println("Desconsiderar Saldo: " + listaDepositos.deposito.desconsiderarSaldo);
            System.out.println("-------------------------------------------------------------------");
        }

        if (request.retorno.depositos == null || request.getRetorno() == null) {
            throw new DepositoIdDepositoNaoEncontradoException(idDeposito);
        }

        System.out.println(request);

        return request;
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo deposito")
    public String createDeposit(@RequestBody String xml) {
        String request = depositoService.createDeposit(xml);

        System.out.println(request);

        return request;
    }

//    /**
//     * POST "CADASTRA UM NOVO DEPOSITO UTILIZANDO XML".
//     */
//    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Cadastrar um deposito")
//    public void registerProduct(@RequestBody String xml) {
//        RestTemplate restTemplate = new RestTemplate();
//        depositoService.postDepositoXml(xml);
//    }

}
