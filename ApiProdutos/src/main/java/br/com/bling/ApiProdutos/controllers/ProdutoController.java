package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.bling.ApiProdutos.models.Resposta;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    private String codigo;

    /**
     * GET "BUSCAR LISTA DE PRODUTOS".
     */
    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public Resposta getAllProducts() {
        Resposta response = produtoService.getAllProducts();

        System.out.println(response);

        return response;
    }

    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public Resposta getProductByCode(@PathVariable String codigo) {
        Resposta response = produtoService.getProductByCode(codigo);

        System.out.println(response);

        return response;
    }

    /**
     * GET "PRODUTO UTILIZANDO O CÓDIGO E NOME DO FORNECEDOR.
     */
    @GetMapping("/produto/{codigo}/{id_fornecedor}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public Resposta getProductByCodeSupplier(@PathVariable String codigo, String nomeFornecedor) {
        Resposta response = produtoService.getProductByCodeSupplier(codigo, nomeFornecedor);

        System.out.println(response);

        return response;
    }


    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deletar um produto pelo código")
    public void deleteProductByCode(@PathVariable String codigo) {
        produtoService.deleteProductByCode(codigo);

        System.out.println(codigo);
    }

/* - EXEMPLO DO VIDEO COM O DE BAIXO

    @PostMapping(path = "/cadastrarproduto")
    @ApiOperation(value = "Cadastrar um produto")
    public ProdutoRequest createProduct(@RequestBody ProdutoRequest produtoRequest) {
        ProdutoRequest request = produtoService.createProduct(produtoRequest);
        return request;
    }
*/
    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto")
    public String createProduct(@RequestBody String xml) {
        String request = produtoService.createProduct(xml);

        System.out.println(request);

        return request;
    }

/*
    @PostMapping(path = "/cadastrarproduto/{codigo}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um produto pelo código")
    public void registerProductByCode(@RequestBody String xml, @PathVariable String codigo) {
        produtoServiceImp.postProductXmlByCode(xml, codigo);
    }
*/
}