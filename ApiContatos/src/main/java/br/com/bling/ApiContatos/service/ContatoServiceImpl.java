package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.RespostaRequest;
import br.com.bling.ApiContatos.controllers.response.RespostaResponse;
import br.com.bling.ApiContatos.exceptions.ApiContatoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ContatoServiceImpl implements ContatoService {

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
    public RespostaResponse getAllContacts() throws ApiContatoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/contatos/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            RespostaResponse response = objectMapper.readValue(json, RespostaResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @Override
    public RespostaResponse getContactsById(String cpf_cnpj) throws ApiContatoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/contato/" + cpf_cnpj + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            RespostaResponse response = objectMapper.readValue(json, RespostaResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public RespostaRequest createContact(String xml) throws ApiContatoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/contato/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest response = objectMapper.readValue(json, RespostaRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML. -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @Override
    public RespostaRequest updateContact(String xml, String id) throws ApiContatoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url = apiBaseUrl + "/contato/" + id + "/json/" + apiKey + apiXmlParam + xml;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest response = objectMapper.readValue(responseEntity.getBody(), RespostaRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", null);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", null);
        }
    }
}