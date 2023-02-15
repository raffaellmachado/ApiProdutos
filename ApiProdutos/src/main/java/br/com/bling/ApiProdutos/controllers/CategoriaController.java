package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.service.CategoriaService;
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
public class CategoriaController {

    private final CategoriaService categoriaService;
    private String idCategoria;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    @GetMapping("/categoria")
    @ApiOperation(value = "Retorna uma lista de categorias")
    public Resposta getCategoria() {
        return categoriaService.getCategoria();
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public Resposta getProductById(@PathVariable String idCategoria) {
        this.idCategoria = idCategoria;
        return categoriaService.getCategoriaIdCategoria(idCategoria);
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public void registerProduct(@RequestBody String xml) {
        RestTemplate restTemplate = new RestTemplate();
        categoriaService.postCategoriaXml(xml);
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".
     */
//    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Cadastrar um produto")
//    public void registerProduct(@RequestBody String xml) {
//        RestTemplate restTemplate = new RestTemplate();
//        categoriaService.postCategoriaXml(xml);
//    }
}

