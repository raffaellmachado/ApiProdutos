package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.exceptions.*;
import br.com.bling.ApiProdutosFornecedores.models.Resposta;
import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
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
public class ProdutoFornecedorController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProdutoFornecedorService produtoFornecedorService;
    private String codigo;

    /**
     * GET "BUSCAR LISTA DE PRODUTOS".
     */
    @GetMapping("/produtosfornecedores")
    @ApiOperation(value = "Retorna uma lista de produtos fornecedors")
    public Resposta getAllProducts() {
        Resposta request = produtoFornecedorService.getAllProducts();

//        if (request.retorno.produtosfornecedores == null || request.getRetorno() == null) {
//            throw new ProdutoListaNaoEncontradoException();
//        }
//
//        for (Retorno.Produtosfornecedore listaProdutos : request.getRetorno().getProdutosfornecedores()) {
//            System.out.println("-----------------------------------------------------------------------------------");
//            System.out.println("Id Produto: " + listaProdutos.produtofornecedores.id);
//            System.out.println("codigo: " + listaProdutos.produto.codigo);
//            System.out.println("descricao: " + listaProdutos.produto.descricao);
//            System.out.println("tipo: " + listaProdutos.produto.tipo);
//            System.out.println("situacao: " + listaProdutos.produto.situacao);
//            System.out.println("unidade: " + listaProdutos.produto.unidade);
//            System.out.println("preco: " + listaProdutos.produto.preco);
//            System.out.println("precoCusto: " + listaProdutos.produto.precoCusto);
//            System.out.println("descricaoCurta: " + listaProdutos.produto.descricaoCurta);
//            System.out.println("descricaoComplementar: " + listaProdutos.produto.descricaoComplementar);
//            System.out.println("dataInclusao: " + listaProdutos.produto.dataInclusao);
//            System.out.println("dataAlteracao: " + listaProdutos.produto.dataAlteracao);
//            System.out.println("imageThumbnail: " + listaProdutos.produto.imageThumbnail);
//            System.out.println("urlVideo: " + listaProdutos.produto.urlVideo);
//            System.out.println("nomeFornecedor: " + listaProdutos.produto.nomeFornecedor);
//            System.out.println("codigoFabricante: " + listaProdutos.produto.codigoFabricante);
//            System.out.println("marca: " + listaProdutos.produto.marca);
//            System.out.println("class_fiscal: " + listaProdutos.produto.class_fiscal);
//            System.out.println("cest: " + listaProdutos.produto.cest);
//            System.out.println("origem: " + listaProdutos.produto.origem);
//            System.out.println("idGrupoProduto: " + listaProdutos.produto.idGrupoProduto);
//            System.out.println("linkExterno: " + listaProdutos.produto.linkExterno);
//            System.out.println("observacoes: " + listaProdutos.produto.observacoes);
//            System.out.println("grupoProduto: " + listaProdutos.produto.grupoProduto);
//            System.out.println("garantia: " + listaProdutos.produto.garantia);
//            System.out.println("descricaoFornecedor: " + listaProdutos.produto.descricaoFornecedor);
//            System.out.println("idFabricante: " + listaProdutos.produto.idFabricante);
//            System.out.println("categoria: " + listaProdutos.produto.categoria);
//            System.out.println("pesoLiq: " + listaProdutos.produto.pesoLiq);
//            System.out.println("pesoBruto: " + listaProdutos.produto.pesoBruto);
//            System.out.println("estoqueMinimo: " + listaProdutos.produto.estoqueMinimo);
//            System.out.println("estoqueMaximo: " + listaProdutos.produto.estoqueMaximo);
//            System.out.println("gtin: " + listaProdutos.produto.gtin);
//            System.out.println("gtinEmbalagem: " + listaProdutos.produto.gtinEmbalagem);
//            System.out.println("larguraProduto: " + listaProdutos.produto.larguraProduto);
//            System.out.println("alturaProduto: " + listaProdutos.produto.alturaProduto);
//            System.out.println("profundidadeProduto: " + listaProdutos.produto.profundidadeProduto);
//            System.out.println("unidadeMedida: " + listaProdutos.produto.unidadeMedida);
//            System.out.println("itensPorCaixa: " + listaProdutos.produto.itensPorCaixa);
//            System.out.println("volumes: " + listaProdutos.produto.volumes);
//            System.out.println("localizacao: " + listaProdutos.produto.localizacao);
//            System.out.println("crossdocking: " + listaProdutos.produto.crossdocking);
//            System.out.println("condicao: " + listaProdutos.produto.condicao);
//            System.out.println("freteGratis: " + listaProdutos.produto.freteGratis);
//            System.out.println("producao: " + listaProdutos.produto.producao);
//            System.out.println("dataValidade: " + listaProdutos.produto.dataValidade);
//            System.out.println("spedTipoItem: " + listaProdutos.produto.spedTipoItem);
//            System.out.println("clonarDadosPai: " + listaProdutos.produto.clonarDadosPai);
//            System.out.println("codigopai: " + listaProdutos.produto.codigopai);
//            System.out.println("variacoes: " + listaProdutos.produto.variacoes);
//            System.out.println("imagem: " + listaProdutos.produto.imagem);
//            System.out.println("depositos: " + listaProdutos.produto.depositos);
//            System.out.println("produtoLoja: " + listaProdutos.produto.produtoLoja);
//            System.out.println("estoqueAtual: " + listaProdutos.produto.estoqueAtual);
//            System.out.println("idCategoria: " + listaProdutos.produto.idCategoria);
//            System.out.println("-----------------------------------------------------------------------------------");
//        }

        System.out.println(request);

        return request;
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @GetMapping("/produtosfornecedores/{id}")
    @ApiOperation(value = "Retorna um produto fornecedor pelo ID")
    public Resposta getProductByCode(@PathVariable String id) {
        Resposta request = produtoFornecedorService.getProductByCode(id);

//        if (request.retorno.produtos == null || request.getRetorno() == null) {
//            throw new ProdutoCodigoNaoEncontradoException(codigo);
//        }
//
//        for (Retorno.Produtos listaProdutos : request.getRetorno().getProdutos()) {
//            System.out.println("-----------------------------------------------------------------------------------");
//            System.out.println("Id Produto: " + listaProdutos.produto.id);
//            System.out.println("codigo: " + listaProdutos.produto.codigo);
//            System.out.println("descricao: " + listaProdutos.produto.descricao);
//            System.out.println("tipo: " + listaProdutos.produto.tipo);
//            System.out.println("situacao: " + listaProdutos.produto.situacao);
//            System.out.println("unidade: " + listaProdutos.produto.unidade);
//            System.out.println("preco: " + listaProdutos.produto.preco);
//            System.out.println("precoCusto: " + listaProdutos.produto.precoCusto);
//            System.out.println("descricaoCurta: " + listaProdutos.produto.descricaoCurta);
//            System.out.println("descricaoComplementar: " + listaProdutos.produto.descricaoComplementar);
//            System.out.println("dataInclusao: " + listaProdutos.produto.dataInclusao);
//            System.out.println("dataAlteracao: " + listaProdutos.produto.dataAlteracao);
//            System.out.println("imageThumbnail: " + listaProdutos.produto.imageThumbnail);
//            System.out.println("urlVideo: " + listaProdutos.produto.urlVideo);
//            System.out.println("nomeFornecedor: " + listaProdutos.produto.nomeFornecedor);
//            System.out.println("codigoFabricante: " + listaProdutos.produto.codigoFabricante);
//            System.out.println("marca: " + listaProdutos.produto.marca);
//            System.out.println("class_fiscal: " + listaProdutos.produto.class_fiscal);
//            System.out.println("cest: " + listaProdutos.produto.cest);
//            System.out.println("origem: " + listaProdutos.produto.origem);
//            System.out.println("idGrupoProduto: " + listaProdutos.produto.idGrupoProduto);
//            System.out.println("linkExterno: " + listaProdutos.produto.linkExterno);
//            System.out.println("observacoes: " + listaProdutos.produto.observacoes);
//            System.out.println("grupoProduto: " + listaProdutos.produto.grupoProduto);
//            System.out.println("garantia: " + listaProdutos.produto.garantia);
//            System.out.println("descricaoFornecedor: " + listaProdutos.produto.descricaoFornecedor);
//            System.out.println("idFabricante: " + listaProdutos.produto.idFabricante);
//            System.out.println("categoria: " + listaProdutos.produto.categoria);
//            System.out.println("pesoLiq: " + listaProdutos.produto.pesoLiq);
//            System.out.println("pesoBruto: " + listaProdutos.produto.pesoBruto);
//            System.out.println("estoqueMinimo: " + listaProdutos.produto.estoqueMinimo);
//            System.out.println("estoqueMaximo: " + listaProdutos.produto.estoqueMaximo);
//            System.out.println("gtin: " + listaProdutos.produto.gtin);
//            System.out.println("gtinEmbalagem: " + listaProdutos.produto.gtinEmbalagem);
//            System.out.println("larguraProduto: " + listaProdutos.produto.larguraProduto);
//            System.out.println("alturaProduto: " + listaProdutos.produto.alturaProduto);
//            System.out.println("profundidadeProduto: " + listaProdutos.produto.profundidadeProduto);
//            System.out.println("unidadeMedida: " + listaProdutos.produto.unidadeMedida);
//            System.out.println("itensPorCaixa: " + listaProdutos.produto.itensPorCaixa);
//            System.out.println("volumes: " + listaProdutos.produto.volumes);
//            System.out.println("localizacao: " + listaProdutos.produto.localizacao);
//            System.out.println("crossdocking: " + listaProdutos.produto.crossdocking);
//            System.out.println("condicao: " + listaProdutos.produto.condicao);
//            System.out.println("freteGratis: " + listaProdutos.produto.freteGratis);
//            System.out.println("producao: " + listaProdutos.produto.producao);
//            System.out.println("dataValidade: " + listaProdutos.produto.dataValidade);
//            System.out.println("spedTipoItem: " + listaProdutos.produto.spedTipoItem);
//            System.out.println("clonarDadosPai: " + listaProdutos.produto.clonarDadosPai);
//            System.out.println("codigopai: " + listaProdutos.produto.codigopai);
//            System.out.println("variacoes: " + listaProdutos.produto.variacoes);
//            System.out.println("imagem: " + listaProdutos.produto.imagem);
//            System.out.println("depositos: " + listaProdutos.produto.depositos);
//            System.out.println("produtoLoja: " + listaProdutos.produto.produtoLoja);
//            System.out.println("estoqueAtual: " + listaProdutos.produto.estoqueAtual);
//            System.out.println("idCategoria: " + listaProdutos.produto.idCategoria);
//            System.out.println("-----------------------------------------------------------------------------------");
//        }

        System.out.println(request);

        return request;
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarprodutofornecedor", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto fornecedor")
    public Resposta createProduct(@RequestBody String xml) {
        try {
            Resposta request = produtoFornecedorService.createProduct(xml);

//            if (request.retorno.produtos == null) {
//                throw new ApiProdutoException("Não foi possível criar o produto", null);
//            }
//            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoFornecedorCadastroException("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @PostMapping(path = "/atualizarprodutofornecedor/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um produto fornecedor existente")
    public Resposta updateProduct(@RequestBody String xml, @PathVariable String id) {
        try {
            Resposta request = produtoFornecedorService.updateProduct(xml, id);

//            if (request.retorno.produtos == null) {
//                throw new ProdutoAtualizarException(codigo);
//            }
            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoFornecedorAtualizarException(id);
        }
    }
}