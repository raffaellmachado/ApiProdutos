package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.models.Resposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoriaServiceImpl implements CategoriaService{

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
    public Resposta getAllCategory() throws ApiCategoriaException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/categorias/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Resposta request = objectMapper.readValue(json, Resposta.class);

            System.out.println(request);

            return request;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API", e);
        }

    }
    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Override
    public Resposta getCategoryByIdCategoria(String idCategoria) throws ApiCategoriaException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Resposta request = objectMapper.readValue(json, Resposta.class);

            return request;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".  -----> CRIAR EXCEPTION
     */
    @Override
    public String createCategory(String xml) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url = apiBaseUrl + "/categoria/json/" + apiKey + apiXmlParam + xml;
            String result =  restTemplate.postForObject(url, request, String.class);

            return result;

        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API", e);
        }
    }

    /**
     * PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML". -----> CORRIGIR e INSERIR EXCEPTION
     */
    @Override
    public String updateCategory(String xml, String idCategoria) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey + apiXmlParam + xml;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            return responseEntity.getBody();

        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API: " + e, e);
        }
    }

}