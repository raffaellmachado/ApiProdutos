package br.com.bling.ApiProdutos.service;


import br.com.bling.ApiProdutos.exceptions.ApiProdutoException;
import br.com.bling.ApiProdutos.exceptions.ProdutoException;
import br.com.bling.ApiProdutos.exceptions.ProdutoNaoEncontradoException;
import br.com.bling.ApiProdutos.models.Produto;
import br.com.bling.ApiProdutos.models.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.xmlparam}")
    private String apiXmlParam;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     */
    @Override
    public Resposta getAllProducts() {
        try {
            Resposta result = restTemplate.getForObject(apiBaseUrl + "/produtos/json/" + apiKey, Resposta.class);
            if (result == null || result.getRetorno() == null) {
                throw new ProdutoException("Não foi possível obter a lista de produtos", null);
            }
            return result;
        } catch (RestClientException e) {
            throw new ProdutoException("Erro ao obter a lista de produtos", e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @Override
    public Resposta getProductByCode(String codigo) {
        try {
            Resposta result = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey, Resposta.class);
            if (result == null || result.getRetorno() == null) { //Retornando FALSE quando força um codigo inexistente (CORRIGIR)
                throw new ProdutoNaoEncontradoException(codigo);
            }
            return result;
        } catch (HttpClientErrorException ex) {
            throw new ProdutoNaoEncontradoException(codigo);
        }
    }


    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU) E NOME DO FORNECEDOR".
     */
    @Override
    public Resposta getProductByCodeSupplier(String codigo, String nomeFornecedor) {
        try {
            Resposta result = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/" + nomeFornecedor + "/json/" + apiKey, Resposta.class);
            return result;
        } catch (RestClientException ex) {
            throw new ApiProdutoException(ex);
        }
    }


    /**
     * DELETE "APAGAR UM PRODUTO PELO CÓDIGO (SKU)".
     */
    @Override
    public void deleteProductByCode(String codigo) {
        restTemplate.delete(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey);
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public String createProduct(String xml) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/produto/json/" + apiKey + apiXmlParam + xml;
        return restTemplate.postForObject(url, request, String.class);
    }



/* - ESSE É O OK DO VIDEO.
    @Override
    public ProdutoRequest createProduct(ProdutoRequest produtoRequest) {

        ProdutoRequest request = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<ProdutoRequest> httpEntity = new HttpEntity<>(produtoRequest, headers);
        ResponseEntity<ProdutoRequest> newPostEntity = restTemplate.postForEntity(apiBaseUrl + "/produto/json/" + APIKEY + XML_PARAM, httpEntity, ProdutoRequest.class);
        if(newPostEntity.getStatusCode() == HttpStatus.CREATED) {
            request = newPostEntity.getBody();
        }
        return request;
    }
*/

//    /**
//     * POST DE UM NOVO PRODUTO UTILIZANDO XML
//     */
//    public String postProductXml(String xml) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
//        HttpEntity<String> request = new HttpEntity<>(xml, headers);
//        String url = BLING_API_URL_POST + xml;
//        return restTemplate.postForObject(url, request, String.class);
//    }
//
//    /**
//     * POST ATUALIZA UM PRODUTO A PARTIR DO SEU CODIGO UTILIZANDO XML
//     */
//    public String postProductXmlByCode(String xml, String codigo) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
//        HttpEntity<String> request = new HttpEntity<>(xml, headers);
//        String url = BLING_API_URL_POST + xml + codigo;
//        return restTemplate.postForObject(url, request, String.class);
//    }
}