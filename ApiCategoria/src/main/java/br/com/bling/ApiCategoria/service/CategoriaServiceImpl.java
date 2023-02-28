package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.controllers.request.RespostaRequest;
import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.controllers.response.RespostaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
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
     * GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
    @Override
    public RespostaResponse getAllCategory() throws ApiCategoriaException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/categorias/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            RespostaResponse response = objectMapper.readValue(request, RespostaResponse.class);

            System.out.println(response);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API");
        }

    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Override
    public RespostaResponse getCategoryByIdCategoria(String idCategoria) throws ApiCategoriaException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            RespostaResponse response = objectMapper.readValue(request, RespostaResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API");
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".  -----> CRIAR EXCEPTION
     */
    @Override
    public RespostaRequest createCategory(String xml) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url = apiBaseUrl + "/categoria/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest response = objectMapper.readValue(json, RespostaRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API");
        }
    }

    /**
     * PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML". -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @Override
    public RespostaRequest updateCategory(String xml, String idCategoria) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey + apiXmlParam + xml;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest response = objectMapper.readValue(responseEntity.getBody(), RespostaRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API: ");
        }
    }
}