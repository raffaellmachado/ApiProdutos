package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaNaoEncontradoException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaNaoEncontradoException;
import br.com.bling.ApiCategoria.models.Resposta;
import br.com.bling.ApiCategoria.models.Retorno;
import br.com.bling.ApiCategoria.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public Resposta getCategory() {
        Resposta request = categoriaService.getCategory();
        for (Retorno.Categoria listaCategoria : request.getRetorno().getCategorias()) {
            System.out.println(listaCategoria.categoria.getId());
            System.out.println(listaCategoria.categoria.getDescricao());
        }

        if (request.retorno.getCategorias() == null || request.getRetorno() == null) {
            throw new CategoriaListaNaoEncontradoException();
        }
        System.out.println(request);

        return request;
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public Resposta getCategoryByIdCategory(@PathVariable String idCategoria) {
        Resposta request = categoriaService.getCategoryByIdCategoria(idCategoria);

        if (request.retorno.categorias == null || request.getRetorno() == null) {
            throw new CategoriaIdCategoriaNaoEncontradoException(idCategoria);
        }
        System.out.println(request);

        return request;
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public String createCategory(@RequestBody String xml) {

        String request = categoriaService.createCategory(xml);

        System.out.println(request);

        return request;
    }
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


