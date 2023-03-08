package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import br.com.bling.ApiProdutosFornecedores.exceptions.ApiProdutoFornecedorException;
import br.com.bling.ApiProdutosFornecedores.exceptions.RespostaApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProdutoFornecedorServiceImpl implements ProdutoFornecedorService {

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.apikeyparam}")
    private String apikeyparam;

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
            String request = restTemplate.getForObject(apiBaseUrl + "/produtosfornecedores/json/" + apikeyparam + apiKey, String.class);
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
            String request = restTemplate.getForObject(apiBaseUrl + "/produtofornecedor/" + idProdutoFornecedor + "/json/" + apikeyparam +  apiKey, String.class);
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
    public Object createProduct(String xmlProdutoFornecedor) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<String> request = new HttpEntity<>(xmlProdutoFornecedor, headers);

            String url  = apiBaseUrl + "/produtofornecedor/json/" + apikeyparam + apiKey + apiXmlParam + xmlProdutoFornecedor;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            // verifica se a resposta contém algum erro
            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody().contains("\"erros\":")) {
                ObjectMapper objectMapper = new ObjectMapper();
                RespostaApi respostaApi = objectMapper.readValue(responseEntity.getBody(), RespostaApi.class);
                List<RespostaApi.Erro> erros = respostaApi.getRetornoRequest().getErros();
                if (!erros.isEmpty()) {
                    return erros.get(0).getMsg();
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest response = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            return ("Erro ao chamar API: " + e);
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO FORNECEDOR PELO ID" UTILIZANDO XML. -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @Override
    public Object updateProduct(String xmlProdutoFornecedor, String idProdutoFornecedor) throws ApiProdutoFornecedorException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlProdutoFornecedor);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/produtofornecedor/" + idProdutoFornecedor + "/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            // verifica se a resposta contém algum erro
            if (response.getStatusCode() == HttpStatus.OK && response.getBody().contains("\"erros\":")) {
                ObjectMapper objectMapper = new ObjectMapper();
                RespostaApi respostaApi = objectMapper.readValue(response.getBody(), RespostaApi.class);
                List<RespostaApi.Erro> erros = respostaApi.getRetornoRequest().getErros();
                if (!erros.isEmpty()) {
                    return erros.get(0).getMsg();
                }
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

                return result;

            } else {
                throw new ApiProdutoFornecedorException("Erro ao chamar API");
            }
        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }
}
