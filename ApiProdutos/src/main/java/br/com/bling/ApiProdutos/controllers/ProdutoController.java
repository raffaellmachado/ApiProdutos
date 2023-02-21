package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.exceptions.*;
import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.models.Retorno;
import br.com.bling.ApiProdutos.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
        for (Retorno.Produto listaProdutos : response.getRetorno().getProdutos()) {
            System.out.println(listaProdutos.produto.codigo);
            System.out.println(listaProdutos.produto.descricao);
        }

        if (response.retorno.produtos == null || response.getRetorno() == null) {
            throw new ListaProdutoNaoEncontradoException();
        }
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

        if (response.retorno.produtos == null || response.getRetorno() == null) {
            throw new CodigoProdutoNaoEncontradoException(codigo);
        }

        System.out.println(response);

        return response;
    }

    /**
     * GET "PRODUTO UTILIZANDO O CÓDIGO E NOME DO FORNECEDOR.
     */
    @GetMapping("/produto/{codigo}/{id_fornecedor}")
    @ApiOperation(value = "Retorna um produto pelo código e nome do fornecedor")
    public Resposta getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String id_fornecedor) {
        Resposta response = produtoService.getProductByCodeSupplier(codigo, id_fornecedor);

        if (response.retorno.produtos == null || response.getRetorno() == null) {
            throw new CodigoProdutoNaoEncontradoException(codigo);
        }

        System.out.println(response);
        return response;
    }

    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deletar um produto pelo código")
    public String deleteProductByCode(@PathVariable String codigo) {
        Resposta response = produtoService.getProductByCode(codigo);

        if (response.retorno.produtos == null || response.getRetorno() == null) {
            throw new ProdutoNaoEncontradoParaExclusaoException(codigo);
        }
        produtoService.deleteProductByCode(codigo);

        System.out.println("Codigo deletado = " + codigo);
        return "Produto com o código " + codigo + " foi deletado com sucesso!";
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto")
    public Resposta createProduct(@RequestBody String xml) {
        try {
            Resposta result = produtoService.createProduct(xml);

            if (result.retorno.produtos == null) {
                throw new ApiProdutoException("Não foi possível criar o produto", null);
            }
            System.out.println("Produto cadastrado com sucesso!");

            return result;
        } catch (Exception e) {
            throw new ProdutoCadastroException("Erro ao cadastrar produto: " + e.getMessage());
        }
    }
}