package br.com.bling.ApiDepositos.services;

import br.com.bling.ApiDepositos.controllers.request.JsonRequest;
import br.com.bling.ApiDepositos.controllers.response.DepositoResponse;
import br.com.bling.ApiDepositos.controllers.response.JsonResponse;
import br.com.bling.ApiDepositos.controllers.response.RetornoResponse;
import br.com.bling.ApiDepositos.exceptions.ApiDepositoException;
import br.com.bling.ApiDepositos.repositories.DepositoRequestRepository;
import br.com.bling.ApiDepositos.repositories.DepositoResponseRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositoServiceImpl implements DepositoService {

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
    @Autowired
    public DepositoResponseRepository depositoResponseRepository;

    @Autowired
    public DepositoRequestRepository depositoRequestRepository;


    /**
     * GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
    @Override
    public JsonResponse getAllDeposit() throws ApiDepositoException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/depositos/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            List<DepositoResponse> depositos = new ArrayList<>();
            for (RetornoResponse.Depositos deposito : jsonResponse.getRetorno().getDepositos()) {
                depositos.add(deposito.getDeposito());
            }

            ArrayList<RetornoResponse.Depositos> depositosResponse = new ArrayList<>();
            for (DepositoResponse deposito : depositos) {
                Optional<DepositoResponse> depositoExistente = depositoResponseRepository.findById(deposito.getId());
                if (depositoExistente.isPresent()) {
                    DepositoResponse depositoAtualizado = depositoExistente.get();
                    depositoAtualizado.setId(deposito.getId());
                    depositoResponseRepository.save(depositoAtualizado);
                } else {
                    depositoResponseRepository.save(deposito);
                }
                RetornoResponse.Depositos depositoResponse = new RetornoResponse.Depositos();
                depositoResponse.setDeposito(deposito);
                depositosResponse.add(depositoResponse);
            }

            RetornoResponse retornoResponse = new RetornoResponse();
            retornoResponse.setDepositos(depositosResponse);

            JsonResponse jsonRetornoResponse = new JsonResponse();
            jsonRetornoResponse.setRetorno(retornoResponse);

            return jsonRetornoResponse;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            List<DepositoResponse> depositos = depositoResponseRepository.findAll();
            if (depositos.isEmpty()) {
                throw new ApiDepositoException("Erro ao chamar API: ", e);
            } else {
                RetornoResponse retornoResponse = new RetornoResponse();
                ArrayList<RetornoResponse.Depositos> depositosResponse = new ArrayList<>();
                for (DepositoResponse deposito : depositos) {
                    RetornoResponse.Depositos depositoResponse = new RetornoResponse.Depositos();
                    depositoResponse.setDeposito(deposito);
                    depositosResponse.add(depositoResponse);
                }
                retornoResponse.setDepositos(depositosResponse);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(retornoResponse);
                return jsonResponse;
            }
        }
    }

    /**
     * GET "BUSCAR UM DEPOSITO PELO CÒDIGO IDDEPOSITO".
     */
    @Override
    public JsonResponse getDepositById(String idDeposito) throws ApiDepositoException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(idDeposito, headers);

            String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            Optional<DepositoResponse> depositoExistente = depositoResponseRepository.findById(Long.valueOf(idDeposito));
            if (depositoExistente.isPresent()) {
                RetornoResponse.Depositos deposito = new RetornoResponse.Depositos();
                deposito.setDeposito(depositoExistente.get());

                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(new RetornoResponse());
                jsonResponse.getRetorno().setDepositos(new ArrayList<>());
                jsonResponse.getRetorno().getDepositos().add(deposito);

                return jsonResponse;

            } else {
                throw new ApiDepositoException("A API está indisponível e o contato não foi encontrado no banco de dados.", e);
            }
        }
    }

    /**
     * POST "CADASTRAR UM NOVO DEPOSITO UTILIZANDO XML".
     */
    @Override
    public JsonRequest createDeposit(String xmlDeposito) throws ApiDepositoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlDeposito);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/deposito/json/";
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiDepositoException("Erro ao chamar API", e);
        }
    }

    /**
     * PUT "ATUALIZAR UM DEPOSITO UTILIZANDO XML".
     */
    @Override
    public JsonRequest updateDeposit(String xmlDeposito, String idDeposito) throws ApiDepositoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlDeposito);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiDepositoException("Erro ao chamar API", e);
        }
    }

    /**
     * ---------------------------------------------------- VERSÃO 1 - SEM CONEXÃO AO BANCO DE DADOS. ----------------------------------------------------------
     */

    /**
     * GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
//    @Override
//    public JsonResponse getAllDeposit() throws ApiDepositoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/depositos/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiDepositoException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new ApiDepositoException("Erro ao chamar API: ", e);
//        }
//    }

    /**
     * GET "BUSCAR UM DEPOSITO PELO CÒDIGO IDDEPOSITO".
     */
//    @Override
//    public JsonResponse getDepositById(String idDeposito) throws ApiDepositoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(idDeposito, headers);
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiDepositoException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new ApiDepositoException("Erro ao chamar API: ", e);
//        }
//    }
}