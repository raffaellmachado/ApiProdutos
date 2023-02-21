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
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class ProdutoController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProdutoService produtoService;
    private String codigo;

    /**
     * GET "BUSCAR LISTA DE PRODUTOS".
     */
    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public Resposta getAllProducts() {
        Resposta request = produtoService.getAllProducts();

        if (request.retorno.produtos == null || request.getRetorno() == null) {
            throw new ProdutoListaNaoEncontradoException();
        }

        for (Retorno.Produto listaProdutos : request.getRetorno().getProdutos()) {
            System.out.println(listaProdutos.produto.codigo);
            System.out.println(listaProdutos.produto.descricao);
        }

        System.out.println(request);

        return request;
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public Resposta getProductByCode(@PathVariable String codigo) {
        Resposta request = produtoService.getProductByCode(codigo);

        if (request.retorno.produtos == null || request.getRetorno() == null) {
            throw new ProdutoCodigoNaoEncontradoException(codigo);
        }

        System.out.println(request);

        return request;
    }

    /**
     * GET "PRODUTO UTILIZANDO O CÓDIGO E NOME DO FORNECEDOR.
     */
    @GetMapping("/produto/{codigo}/{id_fornecedor}")
    @ApiOperation(value = "Retorna um produto pelo código e nome do fornecedor")
    public Resposta getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String id_fornecedor) {
        Resposta request = produtoService.getProductByCodeSupplier(codigo, id_fornecedor);

        if (request.retorno.produtos == null || request.getRetorno() == null) {
            throw new ProdutoCodigoNaoEncontradoException(codigo);
        }

        System.out.println(request);
        return request;
    }

    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deletar um produto pelo código")
    public String deleteProductByCode(@PathVariable String codigo) {
        Resposta request = produtoService.getProductByCode(codigo);

        if (request.retorno.produtos == null || request.getRetorno() == null) {
            throw new ProdutoNaoEncontradoExclusaoException(codigo);
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
            Resposta request = produtoService.createProduct(xml);

            if (request.retorno.produtos == null) {
                throw new ApiProdutoException("Não foi possível criar o produto", null);
            }
            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoCadastroException("Erro ao cadastrar produto: " + e.getMessage());
        }
    }
}