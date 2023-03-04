package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutos.controllers.response.RespostaResponse;
import br.com.bling.ApiProdutos.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutos.exceptions.*;
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
    public RespostaResponse getAllProducts() {
        try {
            RespostaResponse request = produtoService.getAllProducts();

            if (request.retorno.produtos == null || request.getRetorno() == null) {
                throw new ApiProdutoException("Nenhum produto foi encontrado.");
            }

            for (RetornoResponse.Produtos listaProdutos : request.getRetorno().getProdutos()) {
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Id Produto: " + listaProdutos.produto.id);
                System.out.println("codigo: " + listaProdutos.produto.codigo);
                System.out.println("descricao: " + listaProdutos.produto.descricao);
                System.out.println("tipo: " + listaProdutos.produto.tipo);
                System.out.println("situacao: " + listaProdutos.produto.situacao);
                System.out.println("unidade: " + listaProdutos.produto.unidade);
                System.out.println("preco: " + listaProdutos.produto.preco);
                System.out.println("precoCusto: " + listaProdutos.produto.precoCusto);
                System.out.println("descricaoCurta: " + listaProdutos.produto.descricaoCurta);
                System.out.println("descricaoComplementar: " + listaProdutos.produto.descricaoComplementar);
                System.out.println("dataInclusao: " + listaProdutos.produto.dataInclusao);
                System.out.println("dataAlteracao: " + listaProdutos.produto.dataAlteracao);
                System.out.println("imageThumbnail: " + listaProdutos.produto.imageThumbnail);
                System.out.println("urlVideo: " + listaProdutos.produto.urlVideo);
                System.out.println("nomeFornecedor: " + listaProdutos.produto.nomeFornecedor);
                System.out.println("codigoFabricante: " + listaProdutos.produto.codigoFabricante);
                System.out.println("marca: " + listaProdutos.produto.marca);
                System.out.println("class_fiscal: " + listaProdutos.produto.class_fiscal);
                System.out.println("cest: " + listaProdutos.produto.cest);
                System.out.println("origem: " + listaProdutos.produto.origem);
                System.out.println("idGrupoProduto: " + listaProdutos.produto.idGrupoProduto);
                System.out.println("linkExterno: " + listaProdutos.produto.linkExterno);
                System.out.println("observacoes: " + listaProdutos.produto.observacoes);
                System.out.println("grupoProduto: " + listaProdutos.produto.grupoProduto);
                System.out.println("garantia: " + listaProdutos.produto.garantia);
                System.out.println("descricaoFornecedor: " + listaProdutos.produto.descricaoFornecedor);
                System.out.println("idFabricante: " + listaProdutos.produto.idFabricante);
                // Categoria
                System.out.println("categoriaId: " + listaProdutos.produto.categoria.id);
                System.out.println("categoriaDescricao: " + listaProdutos.produto.categoria.descricao);
                System.out.println("categoriaIDCategoria Pai: " + listaProdutos.produto.categoria.idCategoriaPai);
                // Categoria
                System.out.println("pesoLiq: " + listaProdutos.produto.pesoLiq);
                System.out.println("pesoBruto: " + listaProdutos.produto.pesoBruto);
                System.out.println("estoqueMinimo: " + listaProdutos.produto.estoqueMinimo);
                System.out.println("estoqueMaximo: " + listaProdutos.produto.estoqueMaximo);
                System.out.println("gtin: " + listaProdutos.produto.gtin);
                System.out.println("gtinEmbalagem: " + listaProdutos.produto.gtinEmbalagem);
                System.out.println("larguraProduto: " + listaProdutos.produto.larguraProduto);
                System.out.println("alturaProduto: " + listaProdutos.produto.alturaProduto);
                System.out.println("profundidadeProduto: " + listaProdutos.produto.profundidadeProduto);
                System.out.println("unidadeMedida: " + listaProdutos.produto.unidadeMedida);
                System.out.println("itensPorCaixa: " + listaProdutos.produto.itensPorCaixa);
                System.out.println("volumes: " + listaProdutos.produto.volumes);
                System.out.println("localizacao: " + listaProdutos.produto.localizacao);
                System.out.println("crossdocking: " + listaProdutos.produto.crossdocking);
                System.out.println("condicao: " + listaProdutos.produto.condicao);
                System.out.println("freteGratis: " + listaProdutos.produto.freteGratis);
                System.out.println("producao: " + listaProdutos.produto.producao);
                System.out.println("dataValidade: " + listaProdutos.produto.dataValidade);
                System.out.println("spedTipoItem: " + listaProdutos.produto.spedTipoItem);
                System.out.println("-----------------------------------------------------------------------------------");
            }

            System.out.println(request);

            return request;
        } catch (Exception e) {
            throw new ProdutoListaNaoEncontradoException();
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public RespostaResponse getProductByCode(@PathVariable String codigo) {
        try {
        RespostaResponse request = produtoService.getProductByCode(codigo);

        if (request.retorno.produtos == null || request.getRetorno() == null) {
            throw new ApiProdutoException("Contato com o número de CPF/CNPJ: " + codigo + " não encontrado.");
        }

        for (RetornoResponse.Produtos listaProdutos : request.getRetorno().getProdutos()) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Id Produto: " + listaProdutos.produto.id);
            System.out.println("codigo: " + listaProdutos.produto.codigo);
            System.out.println("descricao: " + listaProdutos.produto.descricao);
            System.out.println("tipo: " + listaProdutos.produto.tipo);
            System.out.println("situacao: " + listaProdutos.produto.situacao);
            System.out.println("unidade: " + listaProdutos.produto.unidade);
            System.out.println("preco: " + listaProdutos.produto.preco);
            System.out.println("precoCusto: " + listaProdutos.produto.precoCusto);
            System.out.println("descricaoCurta: " + listaProdutos.produto.descricaoCurta);
            System.out.println("descricaoComplementar: " + listaProdutos.produto.descricaoComplementar);
            System.out.println("dataInclusao: " + listaProdutos.produto.dataInclusao);
            System.out.println("dataAlteracao: " + listaProdutos.produto.dataAlteracao);
            System.out.println("imageThumbnail: " + listaProdutos.produto.imageThumbnail);
            System.out.println("urlVideo: " + listaProdutos.produto.urlVideo);
            System.out.println("nomeFornecedor: " + listaProdutos.produto.nomeFornecedor);
            System.out.println("codigoFabricante: " + listaProdutos.produto.codigoFabricante);
            System.out.println("marca: " + listaProdutos.produto.marca);
            System.out.println("class_fiscal: " + listaProdutos.produto.class_fiscal);
            System.out.println("cest: " + listaProdutos.produto.cest);
            System.out.println("origem: " + listaProdutos.produto.origem);
            System.out.println("idGrupoProduto: " + listaProdutos.produto.idGrupoProduto);
            System.out.println("linkExterno: " + listaProdutos.produto.linkExterno);
            System.out.println("observacoes: " + listaProdutos.produto.observacoes);
            System.out.println("grupoProduto: " + listaProdutos.produto.grupoProduto);
            System.out.println("garantia: " + listaProdutos.produto.garantia);
            System.out.println("descricaoFornecedor: " + listaProdutos.produto.descricaoFornecedor);
            System.out.println("idFabricante: " + listaProdutos.produto.idFabricante);
            // Categoria
            System.out.println("categoriaID: " + listaProdutos.produto.categoria.id);
            System.out.println("categoriaDescricao: " + listaProdutos.produto.categoria.descricao);
            System.out.println("categoriaIDCategoria Pai: " + listaProdutos.produto.categoria.idCategoriaPai);
            // Categoria
            System.out.println("pesoLiq: " + listaProdutos.produto.pesoLiq);
            System.out.println("pesoBruto: " + listaProdutos.produto.pesoBruto);
            System.out.println("estoqueMinimo: " + listaProdutos.produto.estoqueMinimo);
            System.out.println("estoqueMaximo: " + listaProdutos.produto.estoqueMaximo);
            System.out.println("gtin: " + listaProdutos.produto.gtin);
            System.out.println("gtinEmbalagem: " + listaProdutos.produto.gtinEmbalagem);
            System.out.println("larguraProduto: " + listaProdutos.produto.larguraProduto);
            System.out.println("alturaProduto: " + listaProdutos.produto.alturaProduto);
            System.out.println("profundidadeProduto: " + listaProdutos.produto.profundidadeProduto);
            System.out.println("unidadeMedida: " + listaProdutos.produto.unidadeMedida);
            System.out.println("itensPorCaixa: " + listaProdutos.produto.itensPorCaixa);
            System.out.println("volumes: " + listaProdutos.produto.volumes);
            System.out.println("localizacao: " + listaProdutos.produto.localizacao);
            System.out.println("crossdocking: " + listaProdutos.produto.crossdocking);
            System.out.println("condicao: " + listaProdutos.produto.condicao);
            System.out.println("freteGratis: " + listaProdutos.produto.freteGratis);
            System.out.println("producao: " + listaProdutos.produto.producao);
            System.out.println("dataValidade: " + listaProdutos.produto.dataValidade);
            System.out.println("spedTipoItem: " + listaProdutos.produto.spedTipoItem);
            System.out.println("-----------------------------------------------------------------------------------");
        }


        System.out.println(request);

        return request;
        } catch (Exception e) {
            throw new ProdutoCodigoNaoEncontradoException(codigo);
        }
    }

    /**
     * GET "PRODUTO UTILIZANDO O CÓDIGO E NOME DO FORNECEDOR.
     */
    @GetMapping("/produto/{codigo}/{id_fornecedor}")
    @ApiOperation(value = "Retorna um produto pelo código e nome do fornecedor")
    public RespostaResponse getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String idFornecedor) {
        RespostaResponse request = produtoService.getProductByCodeSupplier(codigo, idFornecedor);
        try {
            if (request.retorno.produtos == null || request.getRetorno() == null) {
                throw new ApiProdutoException("Produto com código " + codigo + " não encontrado.");
            }

            for (RetornoResponse.Produtos listaProdutos : request.getRetorno().getProdutos()) {
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Id Produto: " + listaProdutos.produto.id);
                System.out.println("codigo: " + listaProdutos.produto.codigo);
                System.out.println("descricao: " + listaProdutos.produto.descricao);
                System.out.println("tipo: " + listaProdutos.produto.tipo);
                System.out.println("situacao: " + listaProdutos.produto.situacao);
                System.out.println("unidade: " + listaProdutos.produto.unidade);
                System.out.println("preco: " + listaProdutos.produto.preco);
                System.out.println("precoCusto: " + listaProdutos.produto.precoCusto);
                System.out.println("descricaoCurta: " + listaProdutos.produto.descricaoCurta);
                System.out.println("descricaoComplementar: " + listaProdutos.produto.descricaoComplementar);
                System.out.println("dataInclusao: " + listaProdutos.produto.dataInclusao);
                System.out.println("dataAlteracao: " + listaProdutos.produto.dataAlteracao);
                System.out.println("imageThumbnail: " + listaProdutos.produto.imageThumbnail);
                System.out.println("urlVideo: " + listaProdutos.produto.urlVideo);
                System.out.println("nomeFornecedor: " + listaProdutos.produto.nomeFornecedor);
                System.out.println("codigoFabricante: " + listaProdutos.produto.codigoFabricante);
                System.out.println("marca: " + listaProdutos.produto.marca);
                System.out.println("class_fiscal: " + listaProdutos.produto.class_fiscal);
                System.out.println("cest: " + listaProdutos.produto.cest);
                System.out.println("origem: " + listaProdutos.produto.origem);
                System.out.println("idGrupoProduto: " + listaProdutos.produto.idGrupoProduto);
                System.out.println("linkExterno: " + listaProdutos.produto.linkExterno);
                System.out.println("observacoes: " + listaProdutos.produto.observacoes);
                System.out.println("grupoProduto: " + listaProdutos.produto.grupoProduto);
                System.out.println("garantia: " + listaProdutos.produto.garantia);
                System.out.println("descricaoFornecedor: " + listaProdutos.produto.descricaoFornecedor);
                System.out.println("idFabricante: " + listaProdutos.produto.idFabricante);
                // Categoria
                System.out.println("categoriaID: " + listaProdutos.produto.categoria.id);
                System.out.println("categoriaDescricao: " + listaProdutos.produto.categoria.descricao);
                System.out.println("categoriaIDCategoria Pai: " + listaProdutos.produto.categoria.idCategoriaPai);
                // Categoria
                System.out.println("pesoLiq: " + listaProdutos.produto.pesoLiq);
                System.out.println("pesoBruto: " + listaProdutos.produto.pesoBruto);
                System.out.println("estoqueMinimo: " + listaProdutos.produto.estoqueMinimo);
                System.out.println("estoqueMaximo: " + listaProdutos.produto.estoqueMaximo);
                System.out.println("gtin: " + listaProdutos.produto.gtin);
                System.out.println("gtinEmbalagem: " + listaProdutos.produto.gtinEmbalagem);
                System.out.println("larguraProduto: " + listaProdutos.produto.larguraProduto);
                System.out.println("alturaProduto: " + listaProdutos.produto.alturaProduto);
                System.out.println("profundidadeProduto: " + listaProdutos.produto.profundidadeProduto);
                System.out.println("unidadeMedida: " + listaProdutos.produto.unidadeMedida);
                System.out.println("itensPorCaixa: " + listaProdutos.produto.itensPorCaixa);
                System.out.println("volumes: " + listaProdutos.produto.volumes);
                System.out.println("localizacao: " + listaProdutos.produto.localizacao);
                System.out.println("crossdocking: " + listaProdutos.produto.crossdocking);
                System.out.println("condicao: " + listaProdutos.produto.condicao);
                System.out.println("freteGratis: " + listaProdutos.produto.freteGratis);
                System.out.println("producao: " + listaProdutos.produto.producao);
                System.out.println("dataValidade: " + listaProdutos.produto.dataValidade);
                System.out.println("spedTipoItem: " + listaProdutos.produto.spedTipoItem);
                System.out.println("-----------------------------------------------------------------------------------");
            }

            System.out.println(request);

            return request;
    } catch (Exception e) {
        throw new ProdutoCodigoNaoEncontradoException(codigo);
    }
    }

    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deletar um produto pelo código")
    public String deleteProductByCode(@PathVariable String codigo) {
        try {
            RespostaResponse request = produtoService.getProductByCode(codigo);

            if (request.retorno.produtos == null || request.getRetorno() == null) {
                throw new ApiProdutoException("Produto com código " + codigo + " não encontrado para exclusão");
            }
            produtoService.deleteProductByCode(codigo);
            System.out.println("Codigo deletado = " + codigo);

            return "Produto com o código " + codigo + " foi deletado com sucesso!";
        } catch (Exception e) {
            throw new ProdutoNaoEncontradoExclusaoException(codigo);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto")
    public RespostaRequest createProduct(@RequestBody String xml) {
        try {
            RespostaRequest request = produtoService.createProduct(xml);

            if (request.retorno.produtos == null) {
                throw new ApiProdutoException("Não foi possível criar o produto");
            }
            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoCadastroException(xml);
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @PostMapping(path = "/atualizarproduto/{codigo}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um produto existente")
    public RespostaRequest updateProduct(@RequestBody String xml, @PathVariable String codigo) {
        try {
            RespostaRequest request = produtoService.updateProduct(xml, codigo);

            if (request.retorno.produtos == null) {
                throw new ProdutoAtualizarException(codigo);
            }
            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoAtualizarException(codigo);
        }
    }
}