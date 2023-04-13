package br.com.bling.ApiCategorias.service;

import br.com.bling.ApiCategorias.controllers.request.CategoriaRequest;
import br.com.bling.ApiCategorias.controllers.request.JsonRequest;
import br.com.bling.ApiCategorias.controllers.request.RetornoRequest;
import br.com.bling.ApiCategorias.controllers.response.CategoriaResponse;
import br.com.bling.ApiCategorias.controllers.response.JsonResponse;
import br.com.bling.ApiCategorias.controllers.response.RetornoResponse;
import br.com.bling.ApiCategorias.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategorias.repository.CategoriaRequestRepository;
import br.com.bling.ApiCategorias.repository.CategoriaResponseRepository;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoriaServiceImpl implements CategoriaService {

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
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaResponseRepository categoriaResponseRepository;

    @Autowired
    private CategoriaRequestRepository categoriaRequestRepository;

    /**
     * GET "BUSCAR A LISTA DE CATEGORIA CADASTRADOS NO BLING".
     */
//    @Override
//    public JsonResponse getAllCategory() throws ApiCategoriaException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/categorias/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new ApiCategoriaException("Erro ao chamar API: ", e);
//        }
//    }

    @Override
    public List<CategoriaResponse> getAllCategory() throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/categorias/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            List<CategoriaResponse> categorias = new ArrayList<>();
            for (RetornoResponse.Categorias categoria : jsonResponse.getRetorno().getCategorias()) {
                categorias.add(categoria.getCategoria());
            }

            for (CategoriaResponse categoria : categorias) {
                Optional<CategoriaResponse> categoriaExistente = categoriaResponseRepository.findById(categoria.getId());
                if (categoriaExistente.isPresent()) {
                    CategoriaResponse categoriaAtualizada = categoriaExistente.get();
                    categoriaAtualizada.setId(categoria.getId());
                    categoriaResponseRepository.save(categoriaAtualizada);
                } else {
                    categoriaResponseRepository.save(categoria);
                }
            }

            return categorias;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            List<CategoriaResponse> categorias = categoriaResponseRepository.findAll();
            if (categorias.isEmpty()) {
                throw new ApiCategoriaException("Erro ao chamar API: ", e);
            } else {
                return categorias;
            }
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
//    @Override
//    public JsonResponse getCategoryByIdCategoria(String idCategoria) throws ApiCategoriaException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(idCategoria, headers);
//
//            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new ApiCategoriaException("Erro ao chamar API: ", e);
//        }
//    }

    @Override
    public CategoriaResponse getCategoryByIdCategoria(String idCategoria) throws ApiCategoriaException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/"  + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            RetornoResponse.Categorias categoria = jsonResponse.getRetorno().getCategorias().get(0);
            CategoriaResponse categoriaResponse = categoria.getCategoria();

            categoriaResponseRepository.save(categoriaResponse);

            return categoriaResponse;

        } catch (RestClientException e) {
            Optional<CategoriaResponse> categoriaExistente = categoriaResponseRepository.findById(idCategoria);
            if (categoriaExistente.isPresent()) {
                return categoriaExistente.get();
            } else {
                throw new ApiCategoriaException("A API está indisponível e a categoria não foi encontrada no banco de dados.", e);
            }
        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
//    @Override
//    public JsonRequest createCategory(String xmlCategoria) throws ApiCategoriaException {
//        try {
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
//            map.add("xml", xmlCategoria);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//            String url = apiBaseUrl + "/categoria/json/";
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new ApiCategoriaException("Erro ao chamar API", e);
//        }
//    }
    @Transactional
    @Override
    public CategoriaRequest createCategory(String xmlCategoria) throws ApiCategoriaException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("xml", xmlCategoria);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url =  apiBaseUrl + "/categoria/json/";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest jsonRequest = objectMapper.readValue(response.getBody(), JsonRequest.class);

            ArrayList<ArrayList<RetornoRequest.Categorias>> categorias = jsonRequest.getRetorno().getCategorias();
            CategoriaRequest categoriaRequest = categorias.get(0).get(0).getCategoria();

            categoriaRequestRepository.save(categoriaRequest);

            // Salva a categoria na tabela tb_categoria_response
            CategoriaResponse categoriaResponse = new CategoriaResponse();
            categoriaResponse.setId(categoriaRequest.getId());
            categoriaResponse.setDescricao(categoriaRequest.getDescricao());
            categoriaResponse.setIdCategoriaPai(String.valueOf(categoriaRequest.getIdCategoriaPai()));
            categoriaResponseRepository.save(categoriaResponse);

            return categoriaRequest;

        } catch (RestClientException e) {
            // Em caso de erro ao chamar a API, salva os dados no banco de dados
            CategoriaRequest categoriaRequest = new CategoriaRequest();
            // Preencha os dados de categoriaRequest aqui com os valores passados em xmlCategoria
            categoriaRequestRepository.save(categoriaRequest);

            // Salva a categoria na tabela tb_categoria_response
            CategoriaResponse categoriaResponse = new CategoriaResponse();
            categoriaResponse.setId(categoriaRequest.getId());
            categoriaResponse.setDescricao(categoriaRequest.getDescricao());
            categoriaResponse.setIdCategoriaPai(String.valueOf(categoriaRequest.getIdCategoriaPai()));
            categoriaResponseRepository.save(categoriaResponse);

            throw new ApiCategoriaException("Erro ao chamar API", e);
        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
        }
    }


    /**
     * PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Override
    public JsonRequest updateCategory(String xmlCategoria, String idCategoria) throws ApiCategoriaException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlCategoria);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiCategoriaException("Erro ao chamar API", e);
        }
    }
}