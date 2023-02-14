package br.com.bling.ApiProdutos.service;


import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bling.ApiProdutos.models.Resposta;

@Service
public class ProdutoService {

	//Chamada da lista de produtos;
    private static final String APIKEY = "f335209056ebf0f8542e371edbcffbc6c95fa2925903c63aa0175825c2ac2110ab01fd23";
    private static final String XML_PARAM = "&xml=";
    private static final String APIKEY_PARAM = "?apikey=";

    private static final String BLING_API_URL;
    //Chamada apenas de um  produto;
    private static final String BLING_API_URL_CODIGO;
    //Chamada passando o codigo do produto e nome do fornecedor
    private static final String BLING_API_URL_ID_FORNECEDOR;
    //Chamada informando o XML para adicionar um novo produto.
    private static final String BLING_API_URL_POST;

    static {


        BLING_API_URL = "https://bling.com.br/Api/v2/produtos/json/" + APIKEY_PARAM + APIKEY;
        BLING_API_URL_CODIGO = "https://bling.com.br/Api/v2/produto/{codigo}/json/" + APIKEY_PARAM + APIKEY;
        BLING_API_URL_ID_FORNECEDOR = "https://bling.com.br/Api/v2/produto/{codigo}/{id_fornecedor}/json/" + APIKEY_PARAM + APIKEY;
        BLING_API_URL_POST = "https://bling.com.br/Api/v2/produto/json/" + APIKEY_PARAM + XML_PARAM;
    }

    @Autowired
    private RestTemplate restTemplate;
    private String codigo;
    private String nomeFornecedor;
    private String xml;

    /**
     * BUSCA A LISTA DE PRODUTOS CADASTRADO NO BLING.
     */
    public Resposta getProduct() {
        return restTemplate.getForObject(BLING_API_URL, Resposta.class);
    }

    /**
     * BUSCA PRODUTO PELO CÓDIGO
     */
    public Resposta getProductByCodigo() {
        return getProductByCodigo(null);
    }

    public Resposta getProductByCodigo(String codigo) {
        this.codigo = codigo;
        return restTemplate.getForObject(BLING_API_URL_CODIGO, Resposta.class, codigo);
    }

    /**
     * BUSCA PRODUTO PELO CÓDIGO E NOME DO FORNECEDOR.
     */
    public Resposta getProductByCodigoFornecedor() {
        return getProductByCodigoFornecedor(null, null);
    }

    public Resposta getProductByCodigoFornecedor(String codigo, String nomeFornecedor) {
        this.codigo = codigo;
        this.nomeFornecedor = nomeFornecedor;
        return restTemplate.getForObject(BLING_API_URL_ID_FORNECEDOR, Resposta.class, codigo, nomeFornecedor);
    }

    /**
     * DELETE PRODUTO PELO CÓDIGO
     */
    public void deleteProductByCodigo() {
        deleteProductByCodigo(null);
    }

    public void deleteProductByCodigo(String codigo) {
        this.codigo = codigo;
        String url = BLING_API_URL_CODIGO.replace("{codigo}", String.valueOf(codigo));
        restTemplate.delete(url);
    }

    /**
     * POST DE UM NOVO PRODUTO UTILIZANDO XML
     */
    public String postProductXml(String xml) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        return restTemplate.postForObject(BLING_API_URL_POST + xml, request , String.class);
    }




}