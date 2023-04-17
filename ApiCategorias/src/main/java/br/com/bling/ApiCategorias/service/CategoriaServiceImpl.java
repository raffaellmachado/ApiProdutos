package br.com.bling.ApiCategorias.service;

import br.com.bling.ApiCategorias.controllers.request.CategoriaRequest;
import br.com.bling.ApiCategorias.controllers.request.JsonRequest;
import br.com.bling.ApiCategorias.controllers.request.RetornoRequest;
import br.com.bling.ApiCategorias.controllers.response.CategoriaResponse;
import br.com.bling.ApiCategorias.controllers.response.JsonResponse;
import br.com.bling.ApiCategorias.controllers.response.RetornoResponse;
import br.com.bling.ApiCategorias.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategorias.repositories.CategoriaRequestRepository;
import br.com.bling.ApiCategorias.repositories.CategoriaResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
     * Método responsável por buscar a lista de produtos, tanto na API externa quanto no banco de dados local.
     *
     * @throws ApiCategoriaException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getAllCategory() throws ApiCategoriaException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/categorias/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            // Cria uma lista de Categorias com os valores da API Bling.
            List<CategoriaResponse> categorias = new ArrayList<>();
            for (RetornoResponse.Categorias categoria : jsonResponse.getRetorno().getCategorias()) {
                categorias.add(categoria.getCategoria());
            }
            // Cria uma lista de Categorias de resposta para enviar de volta
            ArrayList<RetornoResponse.Categorias> categoriasResponse = new ArrayList<>();
            // Percorre todas as categorias da lista
            for (CategoriaResponse categoria : categorias) {
                // Verifica se a categoria existe no banco de dados
                Optional<CategoriaResponse> categoriaExistente = categoriaResponseRepository.findById(categoria.getId());
                if (categoriaExistente.isPresent()) {
                    // Se a categoria já existir, atualiza seus campos.
                    CategoriaResponse categoriaAtualizada = categoriaExistente.get();
                    categoriaAtualizada.setId(categoria.getId());
                    categoriaAtualizada.setDescricao(categoria.getDescricao());
                    categoriaAtualizada.setIdCategoriaPai(categoria.getIdCategoriaPai());
                    categoriaResponseRepository.save(categoriaAtualizada);
                } else {
                    // Sea categoria não existir, insere uma nova cattegoria no banco de dados
                    categoriaResponseRepository.save(categoria);
                }
                // Adiciona a categoria de resposta à lista de categorias de resposta
                RetornoResponse.Categorias categoriaResponse = new RetornoResponse.Categorias();
                categoriaResponse.setCategoria(categoria);
                categoriasResponse.add(categoriaResponse);
            }
            // Cria o objeto de resposta final
            RetornoResponse retornoResponse = new RetornoResponse();
            retornoResponse.setCategorias(categoriasResponse);

            JsonResponse jsonRetornoResponse = new JsonResponse();
            jsonRetornoResponse.setRetorno(retornoResponse);

            // Retorna a resposta final em formato JSON
            return jsonRetornoResponse;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            // Busca todas as categorias salvas no banco de dados
            List<CategoriaResponse> categorias = categoriaResponseRepository.findAll();
            // Verifica se a lista de categorias está vazia, ou seja, se não há nenhuma categoria cadastrada no banco de dados
            if (categorias.isEmpty()) {
                // Se a lista de categoria estiver vazia, lança uma exceção ApiCategoriaException com uma mensagem de erro e a exceção original
                throw new ApiCategoriaException("Erro ao chamar API: ", e);
            } else {
                // Se houver categorias cadastradas no banco de dados, cria uma nova lista de categorias para o retorno da API
                RetornoResponse retornoResponse = new RetornoResponse();
                ArrayList<RetornoResponse.Categorias> categoriasResponse = new ArrayList<>();
                // Para cada categoria salva no banco de dados, cria um novo objeto RetornoResponse.Categorias com a categoria correspondente e adiciona na lista de categorias do retorno
                for (CategoriaResponse categoria : categorias) {
                    RetornoResponse.Categorias categoriaResponse = new RetornoResponse.Categorias();
                    categoriaResponse.setCategoria(categoria);
                    categoriasResponse.add(categoriaResponse);
                }
                // Define a lista de categorias do retorno e cria um objeto JsonResponse com esse retorno
                retornoResponse.setCategorias(categoriasResponse);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(retornoResponse);
                // Retorna o objeto JsonResponse
                return jsonResponse;
            }
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     * Método responsável por localizar uma categoria a partir do seu idCategoria, tanto na API externa quanto no banco de dados local.
     *
     * @param idCategoria idCategoria a ser localizado.
     * @throws ApiCategoriaException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getCategoryByIdCategoria(String idCategoria) throws ApiCategoriaException {
        try {
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            String url = apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            // Busca a categoria com o código informado no banco de dados
            Optional<CategoriaResponse> categoriaExistente = categoriaResponseRepository.findById(Long.valueOf(idCategoria));
            // Se a categoria existir no banco de dados, cria um objeto CategoriaResponse com a categoria encontrada e retorna como resposta
            if (categoriaExistente.isPresent()) {
                RetornoResponse.Categorias categoria = new RetornoResponse.Categorias();
                categoria.setCategoria(categoriaExistente.get());
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(new RetornoResponse());
                jsonResponse.getRetorno().setCategorias(new ArrayList<>());
                jsonResponse.getRetorno().getCategorias().add(categoria);
                return jsonResponse;
                // Se o produto não existir no banco de dados, lança uma exceção ApiProdutoException com a mensagem informando que a API está indisponível e a categoria não foi encontrada no banco de dados.
            } else {
                throw new ApiCategoriaException("A API está indisponível e a categoria não foi encontrada no banco de dados.", e);
            }
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     * Método responsável por cadastrar uma categoria, tanto na API externa quanto no banco de dados local.
     *
     * @param xmlCategoria xml com os dados do cadastro da nova categoria.
     * @throws ApiCategoriaException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public CategoriaRequest createCategory(String xmlCategoria) throws ApiCategoriaException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
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

//            categoriaRequestRepository.save(categoriaRequest);

            return categoriaRequest;
        } catch (RestClientException e) {
            // Em caso de erro ao chamar a API, salva os dados no banco de dados
            CategoriaRequest categoriaRequest = new CategoriaRequest();
            // Preenche os dados de categoriaRequest com os valores passados em xmlCategoria
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(xmlCategoria));
                Document doc = builder.parse(is);

                // Preenchimento dos campos da categoria
                Node nodeCategoria = doc.getElementsByTagName("categoria").item(0);
                Element elementoCategoria = (Element) nodeCategoria;
                categoriaRequest.setDescricao(elementoCategoria.getElementsByTagName("descricao").item(0).getTextContent());

                String idCategoriaPai = elementoCategoria.getElementsByTagName("idCategoriaPai").item(0).getTextContent();
                if (!idCategoriaPai.isEmpty()) {
                    categoriaRequest.setIdCategoriaPai(Long.parseLong(idCategoriaPai));
                }
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                throw new ApiCategoriaException("Erro ao processar XML: ", ex);
            }
            if (categoriaRequest.getId() == null) {
                categoriaRequest.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            }
            categoriaRequestRepository.save(categoriaRequest);
            throw new ApiCategoriaException("Erro ao chamar API", e);
        } catch (JsonProcessingException e) {
            throw new ApiCategoriaException("Erro ao processar JSON: ", e);
        }
    }

    @Scheduled(fixedDelayString = "${api.check.delay}")
    public void checkApiStatus() {
        try {
            System.out.println("Chamei o Scheduled");

            ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrl + "/categorias/json/" + apikeyparam + apiKey, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                List<CategoriaRequest> categorias = categoriaRequestRepository.findAll();
                for (CategoriaRequest categoria : categorias) {
                    try {
                        if (findCategoryByDescricao(categoria.getDescricao()) != null) {
                            System.out.println("Categoria não encontrada na API, adicionando...");
                            String xmlCategoria = "<categorias>";
                            xmlCategoria += "<categoria>";
                            xmlCategoria += "<descricao>" + categoria.getDescricao() + "</descricao>";
                            xmlCategoria += "<idCategoriaPai>" + categoria.getIdCategoriaPai() + "</idCategoriaPai>";
                            xmlCategoria += "</categoria>";
                            xmlCategoria += "</categorias>";

                            createCategory(xmlCategoria);
                            categoriaRequestRepository.delete(categoria);
                        } else {
                            System.out.println("Categoria já existe na API");
                        }

                    } catch (ApiCategoriaException e) {
                        // Erro ao adicionar categoria na API, não faz nada
                        System.out.println("Ta off");
                    }
                }
            }
        } catch (RestClientException e) {
            // API está offline, nada a fazer
        }
    }

    private CategoriaRequest findCategoryByDescricao(String descricao) throws ApiCategoriaException {
        List<CategoriaRequest> categorias = categoriaRequestRepository.findByDescricao(descricao);
        if (!categorias.isEmpty()) {
            return categorias.get(0);
        } else {
            return null;
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

    /**
     * ---------------------------------------------------- VERSÃO 1 - SEM CONEXÃO AO BANCO DE DADOS. ----------------------------------------------------------
     */

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
}

