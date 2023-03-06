package br.com.bling.ApiDeposito.services;


import br.com.bling.ApiDeposito.controllers.request.JsonRequest;
import br.com.bling.ApiDeposito.controllers.response.JsonResponse;
import br.com.bling.ApiDeposito.exceptions.ApiDepositoException;
import br.com.bling.ApiDeposito.exceptions.RespostaApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositoServiceImpl implements DepositoService {

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
    public JsonResponse getAllDeposit() throws ApiDepositoException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/depositos/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse response =  objectMapper.readValue(request, JsonResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            throw new ApiDepositoException("Erro ao chamar API: " + e);
        }
    }

    /**
     * GET "BUSCAR UM DEPOSITO PELO CÒDIGO IDDEPOSITO".
     */
    @Override
    public JsonResponse getDepositByIdDeposit(String idDeposito) throws ApiDepositoException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse response = objectMapper.readValue(request, JsonResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            throw new ApiDepositoException("Erro ao chamar API: " + e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO DEPOSITO" UTILIZANDO XML.
     */
    @Override
    public Object createDeposit(String xmlDeposito) throws ApiDepositoException  {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xmlDeposito, headers);
            String url = apiBaseUrl + "/deposito/json/" + apiKey + apiXmlParam + xmlDeposito;
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
            throw new ApiDepositoException("Erro ao processar JSON");
        } catch (RestClientException e) {
            return ("Erro ao chamar API: " + e);
        }
    }
//    @Override
//    public RespostaRequest createDeposit(String xml) throws ApiDepositoException  {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_XML);
//
//            HttpEntity<String> request = new HttpEntity<>(xml, headers);
//            String url = apiBaseUrl + "/deposito/json/" + apiKey + apiXmlParam + xml;
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            RespostaRequest resposta = objectMapper.readValue(response.getBody(), RespostaRequest.class);
//
//            return resposta;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiDepositoException("Erro ao processar JSON");
//        } catch (RestClientException e) {
//            throw new ApiDepositoException("Erro ao chamar API");
//        }
//    }

    /**
     * PUT "ATUALIZAR UMA NOVA CATEGORIA UTILIZANDO XML". -----> CORRIGIR e INSERIR EXCEPTION
     */
    @Override
    public Object updateDeposit(String xmlDeposito, String idDeposito) throws ApiDepositoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xmlDeposito, headers);
            String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apiKey + apiXmlParam + xmlDeposito;
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
            throw new ApiDepositoException("Erro ao processar JSON: " + e);
        } catch (RestClientException e) {
            return ("Erro ao chamar API: " + e);
        }
    }
//    @Override
//    public RespostaRequest updateDeposit(String xml, String idDeposito) throws ApiDepositoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_XML);
//
//            HttpEntity<String> request = new HttpEntity<>(xml, headers);
//            String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apiKey + apiXmlParam + xml;
//            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            RespostaRequest response = objectMapper.readValue(responseEntity.getBody(), RespostaRequest.class);
//
//            return response;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiDepositoException("Erro ao processar JSON");
//        } catch (RestClientException e) {
//            throw new ApiDepositoException("Erro ao chamar API");
//        }
//    }
}