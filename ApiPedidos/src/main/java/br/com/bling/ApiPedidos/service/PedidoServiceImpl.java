package br.com.bling.ApiPedidos.service;

import br.com.bling.ApiPedidos.controllers.request.JsonRequest;
import br.com.bling.ApiPedidos.controllers.response.JsonResponse;
import br.com.bling.ApiPedidos.exceptions.ApiPedidoException;
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


@Service
public class PedidoServiceImpl implements PedidoService {

    @Value("${external.api.url}")
    public String apiBaseUrl;

    @Value("${external.api.apikey}")
    public String apiKey;

    @Value("${external.api.apikeyparam}")
    public String apikeyparam;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public PedidoService pedidoService;


    /**
     * GET "BUSCAR A LISTA DE CATEGORIA CADASTRADOS NO BLING".
     * Método responsável por buscar a lista de produtos, tanto na API externa quanto no banco de dados local.
     *
     * @throws ApiPedidoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getAllPedido() throws ApiPedidoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/pedidos/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiPedidoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiPedidoException("Erro ao chamar API", e);
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     * Método responsável por localizar uma categoria a partir do seu idCategoria, tanto na API externa quanto no banco de dados local.
     *
     * @param numero idCategoria a ser localizado.
     * @throws ApiPedidoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getPedidoByIdPedido(String numero) throws ApiPedidoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(numero, headers);

            String url = apiBaseUrl + "/pedido/" + numero + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiPedidoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiPedidoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     * Método responsável por cadastrar uma categoria, tanto na API externa quanto no banco de dados local.
     *
     * @param xmlPedido xml com os dados do cadastro da nova categoria.
     * @throws ApiPedidoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonRequest createPedido(String xmlPedido) throws ApiPedidoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlPedido);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url  = apiBaseUrl + "/pedido/json/";
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest response = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiPedidoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiPedidoException("Erro ao chamar API", e);
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".
     * Método responsável por atualizar uma categoria, tanto na API externa quanto no banco de dados local.
     *
     * @param xmlPedido xml com os dados do cadastro da nova categoria.
     * @param numero  Id para acesso direto a categoria cadastrada.
     * @throws ApiPedidoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonRequest updatePedido(String xmlPedido, String numero) throws ApiPedidoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlPedido);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/pedido/" + numero + "/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiPedidoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiPedidoException("Erro ao chamar API", e);
        }
    }
}

