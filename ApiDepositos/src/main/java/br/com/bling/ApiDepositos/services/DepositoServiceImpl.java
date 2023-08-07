package br.com.bling.ApiDepositos.services;

import br.com.bling.ApiDepositos.controllers.request.DepositoRequest;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositoServiceImpl implements DepositoService {

    @Value("${external.api.url}")
    public String apiBaseUrl;

    @Value("${external.api.apikey}")
    public String apiKey;

    @Value("${external.api.apikeyparam}")
    public String apikeyparam;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public DepositoResponseRepository depositoResponseRepository;

    @Autowired
    public DepositoRequestRepository depositoRequestRepository;

    /**
     * GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     * Método responsável por buscar a lista de produtos, tanto na API externa quanto no banco de dados local.
     *
     * @throws ApiDepositoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getAllDeposit() throws ApiDepositoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";
            String url = apiBaseUrl + "/depositos/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            // Cria uma lista de Depositos com os valores da API Bling.
            List<DepositoResponse> depositos = new ArrayList<>();
            for (RetornoResponse.Depositos deposito : jsonResponse.getRetorno().getDepositos()) {
                depositos.add(deposito.getDeposito());
            }
            // Cria uma lista de Depositos de resposta para enviar de volta
            ArrayList<RetornoResponse.Depositos> depositosResponse = new ArrayList<>();
            // Percorre todas os depositos da lista
            for (DepositoResponse deposito : depositos) {
                // Verifica se o deposito existe no banco de dados
                Optional<DepositoResponse> depositoExistente = depositoResponseRepository.findById(deposito.getId());
                if (depositoExistente.isPresent()) {
                    // Se o deposito já existir, atualiza seus campos.
                    DepositoResponse depositoAtualizado = depositoExistente.get();
                    depositoAtualizado.setId(deposito.getId());
                    depositoAtualizado.setDescricao(deposito.getDescricao());
                    depositoAtualizado.setSituacao(deposito.getSituacao());
                    depositoAtualizado.setDepositoPadrao(deposito.depositoPadrao);
                    depositoAtualizado.setDesconsiderarSaldo(deposito.desconsiderarSaldo);

                    depositoResponseRepository.save(depositoAtualizado);
                } else {
                    // Se o deposito não existir, insere um novo deposito no banco de dados
                    depositoResponseRepository.save(deposito);
                }
                // Adiciona o deposito de resposta à lista de depositos de resposta
                RetornoResponse.Depositos depositoResponse = new RetornoResponse.Depositos();
                depositoResponse.setDeposito(deposito);
                depositosResponse.add(depositoResponse);
            }
            // Cria o objeto de resposta final
            RetornoResponse retornoResponse = new RetornoResponse();
            retornoResponse.setDepositos(depositosResponse);

            JsonResponse jsonRetornoResponse = new JsonResponse();
            jsonRetornoResponse.setRetorno(retornoResponse);

            // Retorna a resposta final em formato JSON
            return jsonRetornoResponse;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            // Busca todos os depositos salvas no banco de dados
            List<DepositoResponse> depositos = depositoResponseRepository.findAll();
            // Verifica se a lista de depositos está vazia, ou seja, se não há nenhum depositos cadastrada no banco de dados
            if (depositos.isEmpty()) {
                // Se a lista de depositos estiver vazia, lança uma exceção ApiDepositoException com uma mensagem de erro e a exceção original
                throw new ApiDepositoException("Erro ao chamar API: ", e);
            } else {
                // Se houver depositos cadastrados no banco de dados, cria uma nova lista de depositos para o retorno da API
                RetornoResponse retornoResponse = new RetornoResponse();
                ArrayList<RetornoResponse.Depositos> depositosResponse = new ArrayList<>();
                // Para cada deposito salva no banco de dados, cria um novo objeto RetornoResponse.depositos com a deposito correspondente e adiciona na lista de deposito do retorno
                for (DepositoResponse deposito : depositos) {
                    RetornoResponse.Depositos depositoResponse = new RetornoResponse.Depositos();
                    depositoResponse.setDeposito(deposito);
                    depositosResponse.add(depositoResponse);
                }
                // Define a lista de depositos do retorno e cria um objeto JsonResponse com esse retorno
                retornoResponse.setDepositos(depositosResponse);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(retornoResponse);
                // Retorna o objeto JsonResponse
                return jsonResponse;
            }
        }
    }

    /**
     * GET "BUSCA DEPOSITO PELO IDDEPOSITO".
     * Método responsável por localizar uma deposito a partir do seu idDeposito, tanto na API externa quanto no banco de dados local.
     *
     * @param idDeposito idDeposito a ser localizado.
     * @throws ApiDepositoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getDepositById(String idDeposito) throws ApiDepositoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(idDeposito, headers);
            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";
            String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            // Busca o deposito com o código informado no banco de dados
            Optional<DepositoResponse> depositoExistente = depositoResponseRepository.findById(Long.valueOf(idDeposito));
            // Se o deposito existir no banco de dados, cria um objeto DepositoResponse com o deposito encontrada e retorna como resposta
            if (depositoExistente.isPresent()) {
                RetornoResponse.Depositos deposito = new RetornoResponse.Depositos();
                deposito.setDeposito(depositoExistente.get());

                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setRetorno(new RetornoResponse());

                jsonResponse.getRetorno().setDepositos(new ArrayList<>());
                jsonResponse.getRetorno().getDepositos().add(deposito);

                return jsonResponse;
                // Se o produto não existir no banco de dados, lança uma exceção ApiProdutoException com a mensagem informando que a API está indisponível e o deposito não foi encontrada no banco de dados.
            } else {
                throw new ApiDepositoException("A API está indisponível e o contato não foi encontrado no banco de dados.", e);
            }
        }
    }

    /**
     * POST "CADASTRA UMA NOVO DEPOSITO UTILIZANDO XML".
     * Método responsável por cadastrar um deposito, tanto na API externa quanto no banco de dados local.
     *
     * @param xmlDeposito xml com os dados do cadastro do novo deposito.
     * @throws ApiDepositoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
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
            JsonRequest jsonRequest = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);

            return jsonRequest;

        } catch (RestClientException e) {
            // Em caso de erro ao chamar a API, salva os dados no banco de dados
            DepositoRequest depositoRequest = new DepositoRequest();
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(xmlDeposito));
                Document doc = builder.parse(is);

                Node nodeDeposito = doc.getElementsByTagName("depositos").item(0);
                Element elementoDeposito = (Element) nodeDeposito;

                depositoRequest.setDescricao(elementoDeposito.getElementsByTagName("descricao").item(0).getTextContent());
                depositoRequest.setSituacao(elementoDeposito.getElementsByTagName("situacao").item(0).getTextContent());
                String depositoPadrao = elementoDeposito.getElementsByTagName("depositoPadrao").item(0).getTextContent();
                depositoRequest.setDepositoPadrao(Boolean.parseBoolean(depositoPadrao));
                String desconsiderarSaldo = elementoDeposito.getElementsByTagName("desconsiderarSaldo").item(0).getTextContent();
                depositoRequest.setDesconsiderarSaldo(Boolean.parseBoolean(desconsiderarSaldo));
                depositoRequest.setFlag("POST");

                String nomeDeposito = elementoDeposito.getElementsByTagName("descricao").item(0).getTextContent();
                List<DepositoRequest> depositoExistente = depositoRequestRepository.findByDescricao(nomeDeposito);

                boolean depositoJaExiste = !depositoExistente.isEmpty();

                if (!depositoJaExiste) {
                    depositoRequestRepository.save(depositoRequest);
                }
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                throw new ApiDepositoException("Erro ao processar XML: ", ex);
            }
            throw new ApiDepositoException("Erro ao chamar API", e);
        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON: ", e);
        }
    }

    /**
     * PUT "ATUALIZA UM DEPOSITO EXISTENTE UTILIZANDO XML".
     * Método responsável por atualizar um deposito, tanto na API externa quanto no banco de dados local.
     *
     * @param xmlDeposito xml com os dados do cadastro do novo deposito.
     * @param idDeposito  Id para acesso direto ao deposito cadastrado.
     * @throws ApiDepositoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
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
            JsonRequest jsonRequest = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return jsonRequest;

        } catch (JsonProcessingException e) {
            throw new ApiDepositoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            // Caso haja algum erro de conexão ou a API esteja indisponível, tenta atualizar os dados no banco local
            System.out.println("API externa indisponível. Tentando atualizar os dados no banco local...");

            Optional<DepositoResponse> optionalDeposito = depositoResponseRepository.findById(Long.valueOf(idDeposito));
            if (optionalDeposito.isPresent()) {
                DepositoResponse deposito = optionalDeposito.get();

                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    InputSource is = new InputSource(new StringReader(xmlDeposito));
                    Document doc = builder.parse(is);

                    NodeList descricaoNodes = doc.getElementsByTagName("descricao");
                    String descricao = descricaoNodes.item(0).getTextContent();
                    NodeList situacaoNodes = doc.getElementsByTagName("situacao");
                    String situacao = situacaoNodes.item(0).getTextContent();
                    NodeList depositoPadraoNodes = doc.getElementsByTagName("depositoPadrao");
                    String depositoPadrao = depositoPadraoNodes.item(0).getTextContent();
                    NodeList desconsiderarSaldoNodes = doc.getElementsByTagName("desconsiderarSaldo");
                    String desconsiderarSaldo = desconsiderarSaldoNodes.item(0).getTextContent();

                    //Adiciona na tabela tb_deposito_request o deposito atualizado e adiciona uma FLAG PUT para posterior ser atualizado.
                    DepositoRequest depositoRequest = new DepositoRequest();
                    depositoRequest.setId(Long.valueOf(idDeposito));
                    depositoRequest.setDescricao(descricao);
                    depositoRequest.setSituacao(situacao);
                    depositoRequest.setDepositoPadrao(Boolean.parseBoolean(depositoPadrao));
                    depositoRequest.setDepositoPadrao(Boolean.parseBoolean(desconsiderarSaldo));
                    depositoRequest.setFlag("PUT");

                    depositoRequestRepository.save(depositoRequest);

                    //Atualiza na tabela tb_deposito_response o deposito atualizado para acesso imediato.
                    DepositoResponse depositoResponse = new DepositoResponse();
                    depositoResponse.setId(Long.valueOf(idDeposito));
                    depositoResponse.setDescricao(descricao);
                    depositoResponse.setSituacao(situacao);
                    depositoResponse.setDepositoPadrao(Boolean.parseBoolean(depositoPadrao));
                    depositoResponse.setDepositoPadrao(Boolean.parseBoolean(desconsiderarSaldo));

                    depositoResponseRepository.save(depositoResponse);

                    System.out.println("Dados atualizados no banco local.");

                    // Retorna um objeto vazio como indicação de que a operação foi concluída com sucesso
                    return new JsonRequest();
                } catch (NumberFormatException | ParserConfigurationException | IOException | SAXException ex) {
                    throw new ApiDepositoException("Erro ao processar XML", ex);
                }
            } else {
                throw new ApiDepositoException("Deposito não encontrada no banco de dados", e);
            }
        }
    }

    /**
     * Verifica o status da API externa e atualiza o banco de dados local com os depositos cadastradas na API.
     * Este método é executado periodicamente, com o intervalo de tempo definido na propriedade "api.check.delay".
     * Se um deposito existe apenas no banco de dados local, ela será adicionada na API.
     * Se um deposito existe tanto no banco de dados local quanto na API, ela será deletada do banco de dados local.
     *
     * @throws ApiDepositoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponível para a consulta.
     */
//    @Scheduled(fixedDelayString = "${api.check.delay}")
//    public void scheduledPostDeposit() {
//        try {
//            System.out.println("Chamei o Scheduled POST");
////            String url = "http://www.teste.com/";
//            String url = apiBaseUrl + "/depositos/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                List<DepositoRequest> depositos = depositoRequestRepository.findAll();
//                List<String> descricaoDepositos = depositoResponseRepository.findAllDescricao();
//
//                for (DepositoRequest deposito : depositos) {
//                    if (deposito.getFlag() != null && deposito.getFlag().equals("POST")) {
//                        if (!descricaoDepositos.contains(deposito.getDescricao())) {
//                            System.out.println("Deposito não encontrado na API, adicionando...");
//                            String xmlDeposito = "<depositos>";
//                            xmlDeposito += "<deposito>";
//                            xmlDeposito += "<descricao>" + deposito.getDescricao() + "</descricao>";
//                            xmlDeposito += "<situacao>" + deposito.getSituacao() + "</situacao>";
//                            xmlDeposito += "<depositoPadrao>" + deposito.depositoPadrao + "</depositoPadrao>";
//                            xmlDeposito += "<desconsiderarSaldo>" + deposito.desconsiderarSaldo + "</desconsiderarSaldo>";
//                            xmlDeposito += "</deposito>";
//                            xmlDeposito += "</depositos>";
//
//                            createDeposit(xmlDeposito);
//                            depositoRequestRepository.delete(deposito);
//                        } else {
//                            System.out.println("Depositos já existe na API, deletando...");
//                            depositoRequestRepository.delete(deposito);
//                        }
//                    }
//                }
//            }
//        } catch (RestClientException e) {
//            System.out.println("API está offline, nada a fazer");
//        }
//    }

    /**
     * Verifica o status da API externa e atualiza o banco de dados local com os depositos cadastradas na API.
     * Este método é executado periodicamente, com o intervalo de tempo definido na propriedade "api.check.delay".
     * Se um deposito existe apenas no banco de dados local, ela será adicionada na API.
     * Se um deposito existe tanto no banco de dados local quanto na API, ela será deletada do banco de dados local.
     *
     * @throws ApiDepositoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponível para a consulta.
     */
//    @Scheduled(fixedDelayString = "${api.check.delay}")
//    public void scheduledUpdateDeposit() {
//        try {
//            System.out.println("Chamei o Scheduled PUT");
////            String url = "http://www.teste.com/";
//            String url = apiBaseUrl + "/depositos/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                List<DepositoRequest> depositoRequests = depositoRequestRepository.findAll();
//
//                for (DepositoRequest depositoRequest : depositoRequests) {
//                    if ("PUT".equals(depositoRequest.getFlag())) { // verifica se a flag é "PUT"
//                        String xmlDeposito = "<depositos>";
//                        xmlDeposito += "<deposito>";
//                        xmlDeposito += "<descricao>" + depositoRequest.getDescricao() + "</descricao>";
//                        xmlDeposito += "<situacao>" + depositoRequest.getSituacao() + "</situacao>";
//                        xmlDeposito += "<depositoPadrao>" + depositoRequest.depositoPadrao + "</depositoPadrao>";
//                        xmlDeposito += "<desconsiderarSaldo>" + depositoRequest.desconsiderarSaldo + "</desconsiderarSaldo>";
//                        xmlDeposito += "</deposito>";
//                        xmlDeposito += "</depositos>";
//
//                        String idDeposito = String.valueOf(depositoRequest.getId());
//
//                        updateDeposit(xmlDeposito, idDeposito);
//
//                        System.out.println("Depositos já existe na API, deletando...");
//                        depositoRequestRepository.delete(depositoRequest);
//                    }
//                }
//            }
//        } catch (RestClientException e) {
//            System.out.println("API está offline, nada a fazer");
//        }
//    }

    /*
     * ---------------------------------------------------- VERSÃO 1 - SEM CONEXÃO AO BANCO DE DADOS. ----------------------------------------------------------
     */

    /*
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

    /*
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

    /*
     * POST "CADASTRAR UM NOVO DEPOSITO UTILIZANDO XML".
     */
//    @Override
//    public JsonRequest createDeposit(String xmlDeposito) throws ApiDepositoException {
//        try {
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
//            map.add("xml", xmlDeposito);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//            String url = apiBaseUrl + "/deposito/json/";
//            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonRequest jsonRequest = objectMapper.readValue(responseEntity.getBody(), JsonRequest.class);
//
//            return jsonRequest;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiDepositoException("Erro ao processar JSON", e);
//        } catch (RestClientException e) {
//            throw new ApiDepositoException("Erro ao chamar API", e);
//        }
//    }

    /*
     * PUT "ATUALIZAR UM DEPOSITO UTILIZANDO XML".
     */
//    @Override
//    public JsonRequest updateDeposit(String xmlDeposito, String idDeposito) throws ApiDepositoException {
//        try {
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
//            map.add("xml", xmlDeposito);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//            String url = apiBaseUrl + "/deposito/" + idDeposito + "/json/";
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiDepositoException("Erro ao processar JSON", e);
//        } catch (RestClientException e) {
//            throw new ApiDepositoException("Erro ao chamar API", e);
//        }
//    }
}