package controllers;

import exceptions.ApiCategoriaException;
import exceptions.CategoriaCadastroException;
import exceptions.CategoriaIdCategoriaNaoEncontradoException;
import exceptions.CategoriaListaNaoEncontradoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import models.Resposta;
import models.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.CategoriaService;

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
        Resposta response = categoriaService.getCategory();
        for (Retorno.Categoria listaCategoria : response.getRetorno().getCategorias()) {
            System.out.println(listaCategoria.categoria.getId());
            System.out.println(listaCategoria.categoria.getDescricao());
        }

        if (response.retorno.getCategorias() == null || response.getRetorno() == null) {
            throw new CategoriaListaNaoEncontradoException();
        }
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

        if (response.retorno.getCategorias()  == null || response.getRetorno() == null) {
            throw new CategoriaIdCategoriaNaoEncontradoException(idCategoria);
        }
        System.out.println(response);

        return response;
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public Resposta createCategory(@RequestBody String xml) {
      try{
        RestTemplate restTemplate = new RestTemplate();
        Resposta response = categoriaService.createCategory(xml);

          if (response.retorno.getCategorias()  == null) {
              throw new ApiCategoriaException("Não foi possível criar a categoria", null);
          }
          System.out.println("Categoria cadastrado com sucesso!");

          return response;
      } catch (Exception e) {
          throw new CategoriaCadastroException("Erro ao cadastrar categoria: " + e.getMessage());
      }
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


