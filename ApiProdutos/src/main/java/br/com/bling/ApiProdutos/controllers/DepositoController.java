package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.service.DepositoService;
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
        Resposta response = depositoService.getAllDeposit();

        System.out.println(response);

        return response;
    }

    /**
     * GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    @GetMapping("/deposito/{idDeposito}")
    @ApiOperation(value = "Retorna um deposito pelo idDeposito")
    public Resposta getDepositByIdDeposit(@PathVariable String idDeposito) {
        Resposta response = depositoService.getDepositByIdDeposit(idDeposito);

        System.out.println(response);

        return response;
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
