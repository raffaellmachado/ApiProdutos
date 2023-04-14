package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.JsonRequest;
import br.com.bling.ApiContatos.controllers.response.ContatoResponse;
import br.com.bling.ApiContatos.controllers.response.JsonResponse;
import br.com.bling.ApiContatos.controllers.response.RetornoResponse;
import br.com.bling.ApiContatos.exceptions.ApiContatoException;
import br.com.bling.ApiContatos.repositories.ContatoRequestRepository;
import br.com.bling.ApiContatos.repositories.ContatoResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Value("${external.api.url}")
    public String apiBaseUrl;

    @Value("${external.api.apikey}")
    public String apiKey;

    @Value("${external.api.apikeyparam}")
    public String apikeyparam;

    @Value("${external.api.xmlparam}")
    public String apiXmlParam;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public ContatoResponseRepository contatoResponseRepository;

    @Autowired
    public ContatoRequestRepository contatoRequestRepository;

    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     */
    @Override
    public JsonResponse getAllContacts() throws ApiContatoException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/contatos/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            List<ContatoResponse> contatos = new ArrayList<>();
            for (RetornoResponse.Contatos contato : jsonResponse.getRetorno().getContatos()) {
                contatos.add(contato.getContato());
            }

            ArrayList<RetornoResponse.Contatos> contatosResponse = new ArrayList<>();
            for (ContatoResponse contato : contatos) {
                Optional<ContatoResponse> categoriaExistente = contatoResponseRepository.findById(contato.getId());
                if (categoriaExistente.isPresent()) {
                    ContatoResponse categoriaAtualizada = categoriaExistente.get();
                    categoriaAtualizada.setId(contato.getId());
                    contatoResponseRepository.save(categoriaAtualizada);
                } else {
                    contatoResponseRepository.save(contato);
                }
                RetornoResponse.Contatos contatoResponse = new RetornoResponse.Contatos();
                contatoResponse.setContato(contato);
                contatosResponse.add(contatoResponse);
            }

            RetornoResponse retornoResponse = new RetornoResponse();
            retornoResponse.setContatos(contatosResponse);

            JsonResponse jsonRetornoResponse = new JsonResponse();
            jsonRetornoResponse.setRetorno(retornoResponse);

            return jsonRetornoResponse;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            List<ContatoResponse> contatos = contatoResponseRepository.findAll();
            if (contatos.isEmpty()) {
                throw new ApiContatoException("Erro ao chamar API: ", e);
            } else {
                RetornoResponse retornoResponse = new RetornoResponse();
                ArrayList<RetornoResponse.Contatos> contatosResponse = new ArrayList<>();
                for (ContatoResponse contato : contatos) {
                    RetornoResponse.Contatos contatoResponse = new RetornoResponse.Contatos();
                    contatoResponse.setContato(contato);
                    contatosResponse.add(contatoResponse);
                }
                retornoResponse.setContatos(contatosResponse);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(retornoResponse);
                return jsonResponse;
            }
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (ID)".
     */
    @Override
    public JsonResponse getContactsById(String id) throws ApiContatoException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(id, headers);

            String url = apiBaseUrl + "/contato/" + id + "/json/" +apikeyparam + apiKey + "&identificador=2";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            Optional<ContatoResponse> contatoExistente = contatoResponseRepository.findById(Long.valueOf(id));
            if (contatoExistente.isPresent()) {
                RetornoResponse.Contatos contato = new RetornoResponse.Contatos();
                contato.setContato(contatoExistente.get());
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(new RetornoResponse());
                jsonResponse.getRetorno().setContatos(new ArrayList<>());
                jsonResponse.getRetorno().getContatos().add(contato);

                return jsonResponse;

            } else {
                throw new ApiContatoException("A API está indisponível e o contato não foi encontrado no banco de dados.", e);
            }
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public JsonRequest createContact(String xmlContato) throws ApiContatoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlContato);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/contato/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            throw new RuntimeException("Erro ao chamar API: ", e);
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO PELO ID UTILIZANDO XML.
     */
    @Override
    public JsonRequest updateContact(@RequestBody @Valid String xmlContato, @PathVariable("id") String id) throws ApiContatoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlContato);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/contato/" + id + "/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

    /**
     * ---------------------------------------------------- VERSÃO 1 - SEM CONEXÃO AO BANCO DE DADOS. ----------------------------------------------------------
     */

//    @Override
//    public JsonResponse getAllContacts() throws ApiContatoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/contatos/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiContatoException("Erro ao processar JSON", e);
//        } catch (RestClientException e) {
//            throw new ApiContatoException("Erro ao chamar API", e);
//        }
//    }
}