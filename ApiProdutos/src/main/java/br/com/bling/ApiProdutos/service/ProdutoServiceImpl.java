package br.com.bling.ApiProdutos.service;


import br.com.bling.ApiProdutos.controllers.request.JsonRequest;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import br.com.bling.ApiProdutos.exceptions.ApiProdutoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;


@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Value("${external.api.url}")
    public String apiBaseUrl;

    @Value("${external.api.apikey}")
    public String apiKey;

    @Value("${external.api.xmlparam}")
    public String apiXmlParam;

    @Autowired
    public RestTemplate restTemplate;

    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     */
    @Override
    public JsonResponse getAllProducts() throws ApiProdutoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/produtos/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse request =  objectMapper.readValue(json, JsonResponse.class);

            return request;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API");
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU)".
     */
    @Override
    public JsonResponse getProductByCode(String codigo) throws ApiProdutoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse request =  objectMapper.readValue(json, JsonResponse.class);

            return request;
        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API");
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU) E NOME DO FORNECEDOR".
     */
    @Override
    public JsonResponse getProductByCodeSupplier(String codigo, String codigoFabricante) throws ApiProdutoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/" + codigoFabricante + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse request =  objectMapper.readValue(json, JsonResponse.class);

            return request;
        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoException("Não foi possível recuperar o produto do fornecedor. Código: " + codigo + ", Nome do Fornecedor: " + codigoFabricante);
        }
    }

    /**
     * DELETE "APAGAR UM PRODUTO PELO CÓDIGO (SKU)".
     */
    @Override
    public void deleteProductByCode(String codigo) throws ApiProdutoException {
        try {
            ResponseEntity<String> response = restTemplate.exchange(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey, HttpMethod.DELETE, null, String.class);
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse resposta = objectMapper.readValue(json, JsonResponse.class);

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API");
        }
    }
//    @Override
//    public void deleteProductByCode(String codigo) throws ApiProdutoException {
//        try {
//            restTemplate.delete(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey);
//
//        } catch (RestClientException e) {
//            throw new ApiProdutoException("Erro ao chamar API");
//        }
//    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public JsonRequest createProduct(String xml) throws ApiProdutoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produto/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(json, JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API");
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @Override
    public JsonRequest updateProduct(String xml, String codigo) throws ApiProdutoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(json, JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API");
        }
    }
}