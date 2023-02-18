package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.models.Produto;
import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST CATEGORIA")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    private String idCategoria;

    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    @GetMapping("/categorias")
    @ApiOperation(value = "Retorna uma lista de categorias")
    public Resposta getCategory(Produto produto) {
        Resposta response = categoriaService.getCategory();

        System.out.println(response);

        return response;
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public Resposta getCategoryByIdCategory(@PathVariable String idCategoria) {
        Resposta response = categoriaService.getCategoryByIdCategoria(idCategoria);

        System.out.println(response);

        return response;
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public String createCategory(@RequestBody String xml) {
        RestTemplate restTemplate = new RestTemplate();
        String request = categoriaService.createCategory(xml);

        System.out.println(request);

        return request;
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

