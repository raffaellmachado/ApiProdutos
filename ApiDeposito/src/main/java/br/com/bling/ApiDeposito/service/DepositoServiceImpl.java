package br.com.bling.ApiDeposito.service;


import br.com.bling.ApiDeposito.exceptions.ApiDepositoException;
import br.com.bling.ApiDeposito.models.Resposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositoServiceImpl implements DepositoService{

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.xmlparam}")
    private String apiXmlParam;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
    @Override
    public Resposta getAllDeposit() throws ApiDepositoException {
        try {
        String json = restTemplate.getForObject(apiBaseUrl + "/depositos/json/" + apiKey, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Resposta request =  objectMapper.readValue(json, Resposta.class);

        return request;

        } catch (
        JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiDepositoException("Erro ao chamar API", e);
        }
    }

    /**
     * GET "BUSCAR UM DEPOSITO PELO CÒDIGO IDDEPOSITO".
     */
    @Override
    public Resposta getDepositByIdDeposit(String idDeposito) throws ApiDepositoException {
        try {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apiKey, Resposta.class);

        return result;

        } catch (RestClientException e) {
            throw new ApiDepositoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO DEPOSITO" UTILIZANDO XML.
     */
    @Override
    public String createDeposit(String xml) throws ApiDepositoException  {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/deposito/json/" + apiKey + apiXmlParam + xml;
        return restTemplate.postForObject(url, request, String.class);
    }

    /**
     * PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML". -----> CORRIGIR e INSERIR EXCEPTION
     */
    @Override
    public String updateDeposit(String xml, String idDeposito) throws ApiDepositoException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apiKey + apiXmlParam + xml;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

        return response.getBody();
    }
}