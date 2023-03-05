package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.controllers.request.*;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import br.com.bling.ApiProdutos.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutos.exceptions.*;
import br.com.bling.ApiProdutos.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS")    //Swagger
@CrossOrigin(origins = "*", maxAge = 3600)        // Liberar os dominios da API
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
    public ResponseEntity<JsonResponse> getAllProducts() {
        try {
            JsonResponse request = produtoService.getAllProducts();

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

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoListaException();
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public ResponseEntity<JsonResponse> getProductByCode(@PathVariable String codigo) {
        try {
            JsonResponse request = produtoService.getProductByCode(codigo);

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

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoCodigoException(codigo);
        }
    }

    /**
     * GET "PRODUTO UTILIZANDO O CÓDIGO E NOME DO FORNECEDOR.
     */
    @GetMapping("/produto/{codigo}/{codigoFabricante}")
    @ApiOperation(value = "Retorna um produto pelo código e nome do fornecedor")
    public ResponseEntity<JsonResponse> getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String codigoFabricante) {
        JsonResponse request = produtoService.getProductByCodeSupplier(codigo, codigoFabricante);
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

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoCodigoFornecedorException(codigo, codigoFabricante);
        }
    }

    /**
     * DELETE PROUTO PELO CÓDIGO (SKU).
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deletar um produto pelo código")
    public ResponseEntity<Object> deleteProductByCode(@PathVariable String codigo) {
        try {
            JsonResponse request = produtoService.getProductByCode(codigo);

            if (request.retorno.produtos == null || request.getRetorno() == null) {
                throw new ApiProdutoException("Produto com código " + codigo + " não encontrado para exclusão");
            }
            produtoService.deleteProductByCode(codigo);
            System.out.println("Codigo deletado = " + codigo);

            return ResponseEntity.status(HttpStatus.OK).body("Produto com o código " + codigo + " foi deletado com sucesso!");
        } catch (Exception e) {
            throw new ProdutoExclusaoException(codigo);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto")
    public ResponseEntity<JsonRequest> createProduct(@RequestBody @Valid String xml) {
        try {
            JsonRequest request = produtoService.createProduct(xml);

            if (request.retorno.produtos == null) {
                throw new ApiProdutoException("Não foi possível criar o produto");
            }

            for (RetornoRequest.Produtos listaContatos : request.getRetorno().getProdutos()) {
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Id: " + listaContatos.produto.id);
                System.out.println("codigo: " + listaContatos.produto.codigo);
                System.out.println("codigoItem: " + listaContatos.produto.codigoItem);
                System.out.println("descricao: " + listaContatos.produto.descricao);
                System.out.println("tipo: " + listaContatos.produto.tipo);
                System.out.println("situacao: " + listaContatos.produto.situacao);
                System.out.println("descricaoCurta: " + listaContatos.produto.descricaoCurta);
                System.out.println("descricaoComplementar: " + listaContatos.produto.descricaoComplementar);
                System.out.println("un: " + listaContatos.produto.un);
                System.out.println("vlr_unit: " + listaContatos.produto.vlr_unit);
                System.out.println("preco_custo: " + listaContatos.produto.preco_custo);
                System.out.println("peso_bruto: " + listaContatos.produto.peso_bruto);
                System.out.println("peso_liq: " + listaContatos.produto.peso_liq);
                System.out.println("class_fiscal: " + listaContatos.produto.class_fiscal);
                System.out.println("marca: " + listaContatos.produto.marca);
                System.out.println("cest: " + listaContatos.produto.cest);
                System.out.println("origem: " + listaContatos.produto.origem);
                System.out.println("idGrupoProduto: " + listaContatos.produto.idGrupoProduto);
                System.out.println("condicao: " + listaContatos.produto.condicao);
                System.out.println("freteGratis: " + listaContatos.produto.freteGratis);
                System.out.println("linkExterno: " + listaContatos.produto.linkExterno);
                System.out.println("observacoes: " + listaContatos.produto.observacoes);
                System.out.println("unidadeMedida: " + listaContatos.produto.unidadeMedida);
                System.out.println("dataValidade: " + listaContatos.produto.dataValidade);
                System.out.println("descricaoFornecedor: " + listaContatos.produto.descricaoFornecedor);
                System.out.println("idFabricante: " + listaContatos.produto.idFabricante);
                System.out.println("codigoFabricante: " + listaContatos.produto.codigoFabricante);
                System.out.println("gtin: " + listaContatos.produto.gtin);
                System.out.println("gtinEmbalagem: " + listaContatos.produto.gtinEmbalagem);
                System.out.println("largura: " + listaContatos.produto.largura);
                System.out.println("altura: " + listaContatos.produto.altura);
                System.out.println("profundidade: " + listaContatos.produto.profundidade);
                System.out.println("estoqueMinimo: " + listaContatos.produto.estoqueMinimo);
                System.out.println("estoqueMaximo: " + listaContatos.produto.estoqueMaximo);
                System.out.println("estoque: " + listaContatos.produto.estoque);
                System.out.println("itensPorCaixa: " + listaContatos.produto.itensPorCaixa);
                System.out.println("volumes: " + listaContatos.produto.volumes);
                System.out.println("urlVideo: " + listaContatos.produto.urlVideo);
                System.out.println("localizacao: " + listaContatos.produto.localizacao);
                System.out.println("crossdocking: " + listaContatos.produto.crossdocking);
                System.out.println("garantia: " + listaContatos.produto.garantia);
                System.out.println("spedTipoItem: " + listaContatos.produto.spedTipoItem);
                System.out.println("idCategoria: " + listaContatos.produto.idCategoria);
                if (listaContatos.produto.variacoes != null) {
                    for (VariacaoRequest variacao : listaContatos.produto.variacoes.variacao) {
                        System.out.println("--------------------Variacoes---------------------");
                        System.out.println("Variação Nome: " + variacao.nome);
                        System.out.println("Variação codigo: " + variacao.codigo);
                        System.out.println("Variação vlr_unit: " + variacao.vlr_unit);
                        System.out.println("Variação clonarDadosPau: " + variacao.clonarDadosPai);
                        System.out.println("Variação estoque: " + variacao.estoque);
                        System.out.println("Variação un: " + variacao.un);
                        System.out.println("--------------------------------------------------");
                    }
                }
                if (listaContatos.produto.imagens != null) {
                    for (ImagemRequest imagem : listaContatos.produto.imagens) {
                        System.out.println("--------------------Imagens---------------------");
                        System.out.println("Imagem url: " + imagem.url);
                        System.out.println("------------------------------------------------");
                    }
                }
                if (listaContatos.produto.deposito != null) {
                    System.out.println("--------------------Imagens---------------------");
                    System.out.println("Deposito Id: " + listaContatos.produto.deposito.id);
                    System.out.println("Deposito Estoque: " + listaContatos.produto.deposito.estoque);

                    System.out.println("------------------------------------------------");
                }
                if (listaContatos.produto.camposCustomizados != null) {
                    System.out.println("--------------Campos Customizados---------------");
                    System.out.println("Campo Customizado Alias: " + listaContatos.produto.camposCustomizados.alias);
                    System.out.println("------------------------------------------------");
                }
                if (listaContatos.produto.estrutura != null) {
                    System.out.println("--------------------Estrutura-------------------");
                    System.out.println("Estrutura tipoEstoque: " + listaContatos.produto.estrutura.tipoEstoque);
                    System.out.println("Estrutura lancarEstoque: " + listaContatos.produto.estrutura.lancarEstoque);
                    System.out.println("------------------------------------------------");
                }
                if (listaContatos.produto.estrutura != null) {
                    for (ComponenteRequest estrutura : listaContatos.produto.estrutura.componente) {
                        System.out.println("--------------------Componentes-----------------");
                        System.out.println("Compoenente nome: " + estrutura.nome);
                        System.out.println("Compoenente codigo: " + estrutura.codigo);
                        System.out.println("Compoenente quantidade: " + estrutura.quantidade);
                    }
                }

                System.out.println("-----------------------------------------------------------------------------------");
            }
            System.out.println("Produto cadastrado com sucesso!");

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoCadastroException(xml);
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @PostMapping(path = "/atualizarproduto/{codigo}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um produto existente")
    public ResponseEntity<JsonRequest> updateProduct(@RequestBody @Valid String xml, @PathVariable String codigo) {
        try {
            JsonRequest request = produtoService.updateProduct(xml, codigo);

            if (request.retorno.produtos == null) {
                throw new ProdutoAtualizarException(codigo);
            }
            System.out.println("Produto cadastrado com sucesso!");

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoAtualizarException(codigo);
        }
    }
}