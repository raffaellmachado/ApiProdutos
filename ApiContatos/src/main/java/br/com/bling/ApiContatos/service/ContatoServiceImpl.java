package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.exceptions.ApiContatoException;
import br.com.bling.ApiContatos.controllers.response.Resposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public Resposta getAllContacts() throws ApiContatoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/contatos/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Resposta request = objectMapper.readValue(json, Resposta.class);

            return request;

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
    public Resposta getContactsById(String cpf_cnpj) throws ApiContatoException {
        try {
            Resposta request = restTemplate.getForObject(apiBaseUrl + "/contato/" + cpf_cnpj + "/json/" + apiKey, Resposta.class);

            return request;

        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public String createContact(String xml) throws ApiContatoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/contato/json/" + apiKey + apiXmlParam + xml;
            String result = restTemplate.postForObject(url, request, String.class);

            return result;

        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @Override
    public String updateContact(String xml, String cpf_cnpj) throws ApiContatoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/contato/" + cpf_cnpj + "/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.readValue(json, String.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }
}
