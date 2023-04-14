package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.controllers.request.JsonRequest;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import br.com.bling.ApiProdutos.controllers.response.ProdutoResponse;
import br.com.bling.ApiProdutos.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutos.exceptions.ApiProdutoException;
import br.com.bling.ApiProdutos.repositories.ProdutoRequestRepository;
import br.com.bling.ApiProdutos.repositories.ProdutoResponseRepository;
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
public class ProdutoServiceImpl implements ProdutoService {

    @Value("${external.api.url}")
    public String apiBaseUrl;

    @Value("${external.api.apikey}")
    public String apiKey;

    @Value("${external.api.apikeyparam}")
    private String apikeyparam;

    @Value("${external.api.xmlparam}")
    public String apiXmlParam;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private ProdutoResponseRepository produtoResponseRepository;

    @Autowired
    private ProdutoRequestRepository produtoRequestRepository;


    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     */
    @Override
    public JsonResponse getAllProducts() throws ApiProdutoException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/produtos/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            List<ProdutoResponse> produtos = new ArrayList<>();
            for (RetornoResponse.Produtos produto : jsonResponse.getRetorno().getProdutos()) {
                produtos.add(produto.getProduto());
            }

            ArrayList<RetornoResponse.Produtos> produtosResponse = new ArrayList<>();
            for (ProdutoResponse produto : produtos) {
                Optional<ProdutoResponse> produtoExistente = produtoResponseRepository.findById(produto.getId());
                if (produtoExistente.isPresent()) {
                    ProdutoResponse produtoAtualizado = produtoExistente.get();
                    produtoAtualizado.setId(produto.getId());
                    produtoResponseRepository.save(produtoAtualizado);
                } else {
                    produtoResponseRepository.save(produto);
                }
                RetornoResponse.Produtos produtoResponse = new RetornoResponse.Produtos();
                produtoResponse.setProduto(produto);
                produtosResponse.add(produtoResponse);
            }

            RetornoResponse retornoResponse = new RetornoResponse();
            retornoResponse.setProdutos(produtosResponse);

            JsonResponse jsonRetornoResponse = new JsonResponse();
            jsonRetornoResponse.setRetorno(retornoResponse);

            return jsonRetornoResponse;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            List<ProdutoResponse> produtos = produtoResponseRepository.findAll();
            if (produtos.isEmpty()) {
                throw new ApiProdutoException("Erro ao chamar API: ", e);
            } else {
                RetornoResponse retornoResponse = new RetornoResponse();
                ArrayList<RetornoResponse.Produtos> produtosResponse = new ArrayList<>();
                for (ProdutoResponse produto : produtos) {
                    RetornoResponse.Produtos produtoResponse = new RetornoResponse.Produtos();
                    produtoResponse.setProduto(produto);
                    produtosResponse.add(produtoResponse);
                }
                retornoResponse.setProdutos(produtosResponse);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(retornoResponse);
                return jsonResponse;
            }
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU)".
     */
    @Override
    public JsonResponse getProductByCode(String codigo) throws ApiProdutoException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/produto/" + codigo + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            Optional<ProdutoResponse> produtoExistente = produtoResponseRepository.findById(Long.valueOf(codigo));
            if (produtoExistente.isPresent()) {
                RetornoResponse.Produtos produto = new RetornoResponse.Produtos();
                produto.setProduto(produtoExistente.get());
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(new RetornoResponse());
                jsonResponse.getRetorno().setProdutos(new ArrayList<>());
                jsonResponse.getRetorno().getProdutos().add(produto);
                return jsonResponse;
            } else {
                throw new ApiProdutoException("A API está indisponível e a categoria não foi encontrada no banco de dados.", e);
            }
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU) E IDFORNECEDOR".
     */
    @Override
    public JsonResponse getProductByCodeSupplier(String codigoFabricante, String idFabricante) throws ApiProdutoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/produto/" + codigoFabricante + "/" + idFabricante + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * DELETE "APAGA UM PRODUTO PELO CÓDIGO (SKU)".
     */
    @Override
    public void deleteProductByCode(String codigo) throws ApiProdutoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/produto/" + codigo + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRA UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public JsonRequest createProduct(String xmlProdutos) throws ApiProdutoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlProdutos);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            String url = apiBaseUrl + "/produto/json/";
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "ATUALIZA UM PRODUTO EXISTENTE PELO CODIGO" UTILIZANDO XML.
     */
    @Override
    public JsonRequest updateProduct(String xmlProdutos, String codigo) throws ApiProdutoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlProdutos);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/produto/" + codigo + "/json/";
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * ---------------------------------------------------- VERSÃO 1 - SEM CONEXÃO AO BANCO DE DADOS. ----------------------------------------------------------
     */

    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     */
//    @Override
//    public JsonResponse getAllProducts() throws ApiProdutoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/produtos/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return produtoRepository.save(result);
//
//        } catch (JsonProcessingException e) {
//            throw new ApiProdutoException("Erro ao processar JSON", e);
//        } catch (RestClientException e) {
//            throw new ApiProdutoException("Erro ao chamar API", e);
//        }
//    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU) E IDFORNECEDOR".
     */
//    @Override
//    public JsonResponse getProductByCodeSupplier(String codigoFabricante, String idFabricante) throws ApiProdutoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/produto/" + codigoFabricante + "/" + idFabricante + "/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiProdutoException("Erro ao processar JSON", e);
//        } catch (RestClientException e) {
//            throw new ApiProdutoException("Erro ao chamar API", e);
//        }
//    }
}