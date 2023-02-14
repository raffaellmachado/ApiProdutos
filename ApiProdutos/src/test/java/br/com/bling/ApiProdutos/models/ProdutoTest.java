package br.com.bling.ApiProdutos.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ProdutoTest {
    @Mock
    Object estoqueMaximo;
    @Mock
    Object imageThumbnail;
    @Mock
    List<Imagem> imagem;
    @Mock
    ProdutoLoja produtoLoja;
    @InjectMocks
    Produto produto;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetId() throws Exception {
        produto.setId("id");
    }

    @Test
    public void testSetCodigo() throws Exception {
        produto.setCodigo("codigo");
    }

    @Test
    public void testSetTipo() throws Exception {
        produto.setTipo("tipo");
    }

    @Test
    public void testSetDescricao() throws Exception {
        produto.setDescricao("descricao");
    }

    @Test
    public void testSetUnidade() throws Exception {
        produto.setUnidade("unidade");
    }

    @Test
    public void testSetPreco() throws Exception {
        produto.setPreco(0d);
    }

    @Test
    public void testSetPrecoCusto() throws Exception {
        produto.setPrecoCusto(0d);
    }

    @Test
    public void testSetPesoLiq() throws Exception {
        produto.setPesoLiq(0d);
    }

    @Test
    public void testSetPesoBruto() throws Exception {
        produto.setPesoBruto(0d);
    }

    @Test
    public void testSetEstoqueMinimo() throws Exception {
        produto.setEstoqueMinimo(0d);
    }

    @Test
    public void testSetEstoqueMaximo() throws Exception {
        produto.setEstoqueMaximo("estoqueMaximo");
    }

    @Test
    public void testSetGtin() throws Exception {
        produto.setGtin("gtin");
    }

    @Test
    public void testSetGtinEmbalagem() throws Exception {
        produto.setGtinEmbalagem("gtinEmbalagem");
    }

    @Test
    public void testSetDescricaoCurta() throws Exception {
        produto.setDescricaoCurta("descricaoCurta");
    }

    @Test
    public void testSetDescricaoComplementar() throws Exception {
        produto.setDescricaoComplementar("descricaoComplementar");
    }

    @Test
    public void testSetLarguraProduto() throws Exception {
        produto.setLarguraProduto(0);
    }

    @Test
    public void testSetAlturaProduto() throws Exception {
        produto.setAlturaProduto(0);
    }

    @Test
    public void testSetProfundidadeProduto() throws Exception {
        produto.setProfundidadeProduto(0);
    }

    @Test
    public void testSetUnidadeMedida() throws Exception {
        produto.setUnidadeMedida("unidadeMedida");
    }

    @Test
    public void testSetDataInclusao() throws Exception {
        produto.setDataInclusao("dataInclusao");
    }

    @Test
    public void testSetDataAlteracao() throws Exception {
        produto.setDataAlteracao("dataAlteracao");
    }

    @Test
    public void testSetImageThumbnail() throws Exception {
        produto.setImageThumbnail("imageThumbnail");
    }

    @Test
    public void testSetNomeFornecedor() throws Exception {
        produto.setNomeFornecedor("nomeFornecedor");
    }

    @Test
    public void testSetMarca() throws Exception {
        produto.setMarca("marca");
    }

    @Test
    public void testSetClassFiscal() throws Exception {
        produto.setClassFiscal("classFiscal");
    }

    @Test
    public void testSetCest() throws Exception {
        produto.setCest("cest");
    }

    @Test
    public void testSetOrigem() throws Exception {
        produto.setOrigem("origem");
    }

    @Test
    public void testSetIdGrupoProduto() throws Exception {
        produto.setIdGrupoProduto("idGrupoProduto");
    }

    @Test
    public void testSetLinkExterno() throws Exception {
        produto.setLinkExterno("linkExterno");
    }

    @Test
    public void testSetObservacoes() throws Exception {
        produto.setObservacoes("observacoes");
    }

    @Test
    public void testSetGrupoProduto() throws Exception {
        produto.setGrupoProduto("grupoProduto");
    }

    @Test
    public void testSetItensPorCaixa() throws Exception {
        produto.setItensPorCaixa(0);
    }

    @Test
    public void testSetVolumes() throws Exception {
        produto.setVolumes(0);
    }

    @Test
    public void testSetUrlVideo() throws Exception {
        produto.setUrlVideo("urlVideo");
    }

    @Test
    public void testSetLocalizacao() throws Exception {
        produto.setLocalizacao("localizacao");
    }

    @Test
    public void testSetCrossdocking() throws Exception {
        produto.setCrossdocking("crossdocking");
    }

    @Test
    public void testSetGarantia() throws Exception {
        produto.setGarantia(0);
    }

    @Test
    public void testSetCondicao() throws Exception {
        produto.setCondicao("condicao");
    }

    @Test
    public void testSetFreteGratis() throws Exception {
        produto.setFreteGratis("freteGratis");
    }

    @Test
    public void testSetProducao() throws Exception {
        produto.setProducao("producao");
    }

    @Test
    public void testSetDataValidade() throws Exception {
        produto.setDataValidade("dataValidade");
    }

    @Test
    public void testSetDescricaoFornecedor() throws Exception {
        produto.setDescricaoFornecedor("descricaoFornecedor");
    }

    @Test
    public void testSetImagem() throws Exception {
        produto.setImagem(List.of(new Imagem()));
    }

    @Test
    public void testSetProdutoLoja() throws Exception {
        produto.setProdutoLoja(new ProdutoLoja());
    }


}
