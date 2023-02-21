package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.exceptions.*;
import br.com.bling.ApiProdutos.models.Categoria2;
import br.com.bling.ApiProdutos.models.Produto2;
import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.models.Retorno;
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
    public Resposta getCategory() {
        Resposta response = categoriaService.getCategory();
        for (Retorno.Categoria categorias : response.getRetorno().getProdutos()) {
            System.out.println(listaCategoria.categorias.codigo);
            System.out.println(listaCategoria.categorias.descricao);
        }

        if (response.retorno. == null || response.getRetorno() == null) {
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

        if (response.retorno.produtos == null || response.getRetorno() == null) {
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
        Resposta result = categoriaService.createCategory(xml);

          if (result.retorno.produtos == null) {
              throw new ApiCategoriaException("Não foi possível criar a categoria", null);
          }
          System.out.println("Categoria cadastrado com sucesso!");

          return result;
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


