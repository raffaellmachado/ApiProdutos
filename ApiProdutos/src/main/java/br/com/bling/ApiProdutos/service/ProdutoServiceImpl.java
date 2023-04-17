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
     * Método responsável por buscar a lista de produtos, tanto na API externa quanto no banco de dados local.
     *
     * @throws ApiProdutoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
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

            // Cria uma lista de Produtos com os valores da API Bling
            List<ProdutoResponse> produtos = new ArrayList<>();
            for (RetornoResponse.Produtos produto : jsonResponse.getRetorno().getProdutos()) {
                produtos.add(produto.getProduto());
            }
            // Cria uma lista de Produtos de resposta para enviar de volta
            ArrayList<RetornoResponse.Produtos> produtosResponse = new ArrayList<>();
            // Percorre todos os produtos da lista
            for (ProdutoResponse produto : produtos) {
                // Verifica se o produto existe no banco de dados
                Optional<ProdutoResponse> produtoExistente = produtoResponseRepository.findById(produto.getId());
                if (produtoExistente.isPresent()) {
                    // Se o produto já existir, atualiza seus campos * NECESSARIO ADICIONAR TODOS OS PRODUTOS AINDA *.
                    ProdutoResponse produtoAtualizado = produtoExistente.get();
                    produtoAtualizado.setId(produto.getId());
                    produtoAtualizado.setAlturaProduto(produto.getAlturaProduto());
                    produtoAtualizado.setCondicao(produto.getCondicao());
                    produtoResponseRepository.save(produtoAtualizado);
                } else {
                    // Se o produto não existir, insere o novo produto no banco de dados
                    produtoResponseRepository.save(produto);
                }
                // Adiciona o produto de resposta à lista de produtos de resposta
                RetornoResponse.Produtos produtoResponse = new RetornoResponse.Produtos();
                produtoResponse.setProduto(produto);
                produtosResponse.add(produtoResponse);
            }
            // Cria o objeto de resposta final
            RetornoResponse retornoResponse = new RetornoResponse();
            retornoResponse.setProdutos(produtosResponse);

            JsonResponse jsonRetornoResponse = new JsonResponse();
            jsonRetornoResponse.setRetorno(retornoResponse);

            // Retorna a resposta final em formato JSON
            return jsonRetornoResponse;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            // Busca todos os produtos salvos no banco de dados
            List<ProdutoResponse> produtos = produtoResponseRepository.findAll();
            // Verifica se a lista de produtos está vazia, ou seja, se não há nenhum produto cadastrado no banco de dados
            if (produtos.isEmpty()) {
                // Se a lista de produtos estiver vazia, lança uma exceção ApiProdutoException com uma mensagem de erro e a exceção original
                throw new ApiProdutoException("Banco de dados está vazio: ", e);
            } else {
                // Se houver produtos cadastrados no banco de dados, cria uma nova lista de produtos para o retorno da API
                RetornoResponse retornoResponse = new RetornoResponse();
                ArrayList<RetornoResponse.Produtos> produtosResponse = new ArrayList<>();
                // Para cada produto salvo no banco de dados, cria um novo objeto RetornoResponse.Produtos com o produto correspondente e adiciona na lista de produtos do retorno
                for (ProdutoResponse produto : produtos) {
                    RetornoResponse.Produtos produtoResponse = new RetornoResponse.Produtos();
                    produtoResponse.setProduto(produto);
                    produtosResponse.add(produtoResponse);
                }
                // Define a lista de produtos do retorno e cria um objeto JsonResponse com esse retorno
                retornoResponse.setProdutos(produtosResponse);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(retornoResponse);
                // Retorna o objeto JsonResponse
                return jsonResponse;
            }
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU)".
     * Método responsável por localizar um produto a partir do seu código, tanto na API externa quanto no banco de dados local.
     *
     * @param codigo Código do produto a ser localizado.
     * @throws ApiProdutoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
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
            // Busca o produto com o código informado no banco de dados
            Optional<ProdutoResponse> produtoExistente = produtoResponseRepository.findByCodigo(String.valueOf(codigo));
            // Se o produto existir no banco de dados, cria um objeto RetornoResponse.Produtos com o produto encontrado e retorna como resposta
            if (produtoExistente.isPresent()) {
                RetornoResponse.Produtos produto = new RetornoResponse.Produtos();
                produto.setProduto(produtoExistente.get());
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(new RetornoResponse());
                jsonResponse.getRetorno().setProdutos(new ArrayList<>());
                jsonResponse.getRetorno().getProdutos().add(produto);
                return jsonResponse;
            // Se o produto não existir no banco de dados, lança uma exceção ApiProdutoException com a mensagem informando que a API está indisponível e a categoria não foi encontrada no banco de dados.
            } else {
                throw new ApiProdutoException("A API está indisponível e os produtos não foram encontrados no banco de dados.", e);
            }
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CODIGO (SKU) E IDFORNECEDOR"".
     * Método responsável por localizar um produto a partir do seu cosigoFabricante e idFabricante, tanto na API externa quanto no banco de dados local.
     *
     * @param codigoFabricante Código do fabricante e idFabricante a ser localizados.
     * @throws ApiProdutoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
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
     * Método responsável por deletar um produto a partir do seu código, tanto na API externa quanto no banco de dados local.
     *
     * @param codigo Código do produto a ser deletado
     * @throws ApiProdutoException Caso ocorra algum erro na comunicação com a API externa ou na exclusão do produto no banco de dados local.
     */
    @Override
    public void deleteProductByCode(String codigo) throws ApiProdutoException {
        try {
            // Configuração dos cabeçalhos para a requisição HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            // Monta a URL com o código do produto a ser excluído e os parâmetros de autenticação da API externa
            String url = apiBaseUrl + "/produto/" + codigo + "/json/" + apikeyparam + apiKey;

            // Faz a requisição HTTP DELETE para a API externa, passando a URL, o cabeçalho e o tipo de retorno esperado
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

            // Verifica se a requisição na API externa foi bem sucedida
            if (response.getStatusCode() == HttpStatus.OK) {
                // Se sim, busca o produto no banco de dados local a partir do seu código
                Optional<ProdutoResponse> produtoExistente = produtoResponseRepository.findByCodigo(String.valueOf(codigo));
                if (produtoExistente.isPresent()) {
                    // Se o produto existe no banco de dados local, exclui-o
                    produtoResponseRepository.delete(produtoExistente.get());
                }
            } else {
                // Se a requisição na API externa falhou, lança uma exceção informando o erro
                throw new ApiProdutoException("Erro ao excluir produto da API externa", null);
            }
        } catch (RestClientException e) {
            // Caso ocorra algum erro na comunicação com a API externa, lança uma exceção informando o erro
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

    /**
     * DELETE "DELETA UM PRODUTO PELO CODIGO (SKU)".
     */
//    @Override
//    public void deleteProductByCode(String codigo) throws ApiProdutoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/produto/" + codigo + "/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
//
//        } catch (RestClientException e) {
//            throw new ApiProdutoException("Erro ao chamar API", e);
//        }
//    }
}