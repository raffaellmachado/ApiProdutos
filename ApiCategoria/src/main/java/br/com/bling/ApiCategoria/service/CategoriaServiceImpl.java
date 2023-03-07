package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.controllers.request.JsonRequest;
import br.com.bling.ApiCategoria.controllers.response.JsonResponse;
import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.exceptions.RespostaApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.xmlparam}")
    private String apiXmlParam;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CategoriaService categoriaService;

    /**
     * GET "BUSCAR A LISTA DE CATEGORIA CADASTRADOS NO BLING".
     */
    @Override
    public JsonResponse getAllCategory() throws ApiCategoriaException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/categorias/json/" + apiKey, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse response = objectMapper.readValue(request, JsonResponse.class);

            System.out.println(response);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API: " + e);
        }

    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Override
    public JsonResponse getCategoryByIdCategoria(String idCategoria) throws ApiCategoriaException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse response = objectMapper.readValue(request, JsonResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API: " + e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Override
    public Object createCategory(String xmlCategoria) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<String> request = new HttpEntity<>(xmlCategoria, headers);

            String url = apiBaseUrl + "/categoria/json/" + apiKey + apiXmlParam + xmlCategoria;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            // verifica se a resposta contém algum erro
            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody().contains("\"erros\":")) {
                ObjectMapper objectMapper = new ObjectMapper();
                RespostaApi respostaApi = objectMapper.readValue(responseEntity.getBody(), RespostaApi.class);
                return respostaApi.getRetorno().getErros().values().stream().findFirst().get();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest response = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            return ("Erro ao chamar API: " + e);
        }
    }

    /**
     * PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML". -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @Override
    public Object updateCategory(String xmlCategoria, String idCategoria) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<String> request = new HttpEntity<>(xmlCategoria, headers);

            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey + apiXmlParam + xmlCategoria;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            // verifica se a resposta contém algum erro
            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody().contains("\"erros\":")) {
                ObjectMapper objectMapper = new ObjectMapper();
                RespostaApi respostaApi = objectMapper.readValue(responseEntity.getBody(), RespostaApi.class);
                return respostaApi.getRetorno().getErros().values().stream().findFirst().get();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest response = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            return ("Erro ao chamar API: " + e);
        }
    }
}