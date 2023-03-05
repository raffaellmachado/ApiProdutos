package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import br.com.bling.ApiProdutosFornecedores.exceptions.ApiProdutoFornecedorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProdutoFornecedorServiceImpl implements ProdutoFornecedorService {

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.xmlparam}")
    private String apiXmlParam;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET "BUSCAR LISTA DE PRODUTOS FORNECEDORES".
     */
    @Override
    public JsonResponse getAllProducts() throws ApiProdutoFornecedorException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/produtosfornecedores/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse response =  objectMapper.readValue(request, JsonResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO FORNECEDOR PELO ID".
     */
    @Override
    public JsonResponse getProducId(String idProdutoFornecedor) throws ApiProdutoFornecedorException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/produtofornecedor/" + idProdutoFornecedor + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse response =  objectMapper.readValue(request, JsonResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO FORNECEDOR" UTILIZANDO XML.
     */
    @Override
    public JsonRequest createProduct(String xml) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produtofornecedor/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest response = objectMapper.readValue(json, JsonRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO FORNECEDOR PELO ID" UTILIZANDO XML. -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @Override
    public JsonRequest updateProduct(String xml, String idProdutoFornecedor) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produtofornecedor/" + idProdutoFornecedor + "/json/" + apiKey + apiXmlParam + xml;
            ResponseEntity<String> json = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest response = objectMapper.readValue(json.getBody(), JsonRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }
}
