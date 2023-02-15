package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.service.DepositoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api")        //Padrão para os métodos /api/...
@Api(value = "API REST CATEGORIA")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class DepositoController {

    private final DepositoService depositoService;
    private String idDeposito;

    @Autowired
    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    /**
     * GET "BUSCA LISTA DE DEPOSITOS".
     */
    @GetMapping("/deposito")
    @ApiOperation(value = "Retorna uma lista de depositos")
    public Resposta getCategoria() {
        return depositoService.getDeposito();
    }

    /**
     * GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    @GetMapping("/deposito/{idDeposito}")
    @ApiOperation(value = "Retorna um deposito pelo idDeposito")
    public Resposta getProductById(@PathVariable String idDeposito) {
        this.idDeposito = idDeposito;
        return depositoService.getDepositoIdDeposito(idDeposito);
    }

    /**
     * POST "CADASTRA UM NOVO DEPOSITO UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrardeposito", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um deposito")
    public void registerProduct(@RequestBody String xml) {
        RestTemplate restTemplate = new RestTemplate();
        depositoService.postDepositoXml(xml);
    }

}
