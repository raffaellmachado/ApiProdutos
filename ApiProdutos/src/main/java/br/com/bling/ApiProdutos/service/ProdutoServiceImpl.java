package br.com.bling.ApiProdutos.service;


import br.com.bling.ApiProdutos.exceptions.ApiProdutoException;
import br.com.bling.ApiProdutos.exceptions.CodigoProdutoNaoEncontradoException;
import br.com.bling.ApiProdutos.models.Resposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProdutoServiceImpl implements ProdutoService {

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
    public Resposta getAllProducts()  {

        String json = restTemplate.getForObject(apiBaseUrl + "/produtos/json/" + apiKey, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Resposta result =  objectMapper.readValue(json, Resposta.class);
            System.out.println(result);
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @Override
    public Resposta getProductByCode(String codigo) {
        try {
            Resposta result = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey, Resposta.class);

            if (result == null || result.getRetorno() == null || result.getRetorno().getProdutos() == null || result.getRetorno().getProdutos().isEmpty()) {
                throw new CodigoProdutoNaoEncontradoException(codigo);
            }
            return result;
        } catch (RestClientException ex) {
            throw new ApiProdutoException(ex);
        }
    }


    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU) E NOME DO FORNECEDOR".
     */
    @Override
    public Resposta getProductByCodeSupplier(String codigo, String nomeFornecedor) {
        try {
            Resposta result = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/" + nomeFornecedor + "/json/" + apiKey, Resposta.class);

            if (result == null || result.getRetorno() == null || result.getRetorno().getProdutos() == null || result.getRetorno().getProdutos().isEmpty()) {
                throw new CodigoProdutoNaoEncontradoException(codigo);
            }
            return result;
        } catch (RestClientException ex) {
            throw new ApiProdutoException(ex);
        }
    }


    /**
     * DELETE "APAGAR UM PRODUTO PELO CÓDIGO (SKU)".
     *
     * @return
     */
    @Override
    public ResponseEntity<Void> deleteProductByCode(String codigo) {
        try {
        restTemplate.delete(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey);
        } catch (RestClientException ex) {
            throw new ApiProdutoException(ex);
        }
        return null;
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public Resposta createProduct(String xml) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/produto/json/" + apiKey + apiXmlParam + xml;
        String json = restTemplate.postForObject(url, request, String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Resposta r =  objectMapper.readValue(json, Resposta.class);
            System.out.println(r);
            return r;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
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