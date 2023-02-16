package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.exceptions.ProdutoCadastroException;
import br.com.bling.ApiProdutos.exceptions.ProdutoNaoEncontradoException;
import br.com.bling.ApiProdutos.exceptions.ProdutoNaoEncontradoParaExclusaoException;
import br.com.bling.ApiProdutos.models.Produto;
import br.com.bling.ApiProdutos.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.bling.ApiProdutos.models.Resposta;
import io.swagger.annotations.Api;

import java.util.List;


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
    @GetMapping(value = "/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public Resposta getAllProducts() {
        Resposta response = produtoService.getAllProducts();

        System.out.println(response);

        return response;
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public Resposta getProductByCode(@PathVariable String codigo) {
        Resposta response = produtoService.getProductByCode(codigo);

        if (response == null || response.getRetorno() == null) {
            throw new ProdutoNaoEncontradoException(codigo);
        }

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

        if (response == null || response.getRetorno() == null) {
            throw new ProdutoNaoEncontradoException(codigo);
        }

        System.out.println(response);

        return response;
    }


    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deletar um produto pelo código")
    public void deleteProductByCode(@PathVariable String codigo) {
        Resposta response = produtoService.getProductByCode(codigo);

        if (response == null || response.getRetorno() == null) {
            throw new ProdutoNaoEncontradoParaExclusaoException(codigo);
        }

        produtoService.deleteProductByCode(codigo);

        System.out.println("Codigo deletado = " + codigo);
    }


    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody String xml) {
        try {
            String request = produtoService.createProduct(xml);
            System.out.println(request);
            return request;
        } catch (Exception e) {
            throw new ProdutoCadastroException("Erro ao cadastrar produto: " + e.getMessage());
        }
    }


    /* - EXEMPLO DO VIDEO COM O DE BAIXO

    @PostMapping(path = "/cadastrarproduto")
    @ApiOperation(value = "Cadastrar um produto")
    public ProdutoRequest createProduct(@RequestBody ProdutoRequest produtoRequest) {
        ProdutoRequest request = produtoService.createProduct(produtoRequest);
        return request;
    }
*/


/*
    @PostMapping(path = "/cadastrarproduto/{codigo}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um produto pelo código")
    public void registerProductByCode(@RequestBody String xml, @PathVariable String codigo) {
        produtoServiceImp.postProductXmlByCode(xml, codigo);
    }
*/
}