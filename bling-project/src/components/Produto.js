import React from "react";

import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import { Container } from "react-bootstrap";
import Spinner from 'react-bootstrap/Spinner';
import { FaSync, FaTrash } from 'react-icons/fa';


import '../css/Produto.css';
import { parse } from 'js2xmlparser';


class Produto extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            produtos: [],
            categorias: [],
            descricaoCategoria: '',
            categoriaId: 0,
            idCategoria: 0,
            id: 0,
            codigo: '',
            descricao: '',
            tipo: '',
            situacao: '',
            unidade: '',
            un: '',
            preco: '',
            precoCusto: '',
            descricaoCurta: '',
            descricaoComplementar: '',
            dataInclusao: '',
            dataAlteracao: '',
            imageThumbnail: '',
            urlVideo: '',
            nomeFornecedor: '',
            codigoFabricante: '',
            marca: '',
            class_fiscal: '',
            cest: '',
            origem: '',
            idGrupoProduto: '',
            linkExterno: '',
            observacoes: '',
            grupoProduto: '',
            garantia: '',
            descricaoFornecedor: '',
            idFabricante: '',
            pesoLiq: '',
            pesoBruto: '',
            estoqueMinimo: '',
            estoqueMaximo: '',
            gtin: '',
            gtinEmbalagem: '',
            larguraProduto: '',
            alturaProduto: '',
            profundidadeProduto: '',
            unidadeMedida: '',
            itensPorCaixa: '',
            volumes: '',
            localizacao: '',
            crossdocking: '',
            condicao: '',
            freteGratis: '',
            producao: '',
            dataValidade: '',
            spedTipoItem: '',
            carregando: true,
            modalAberta: false,
        };
    }

    componentDidMount() {
        this.buscarDados();
    }

    componentWillUnmount() {

    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.descricao !== this.state.descricao) {
            this.atualizaDescricao({ target: { value: this.state.descricao } });
        }
    }

    //GET - MÉTODO PARA CONSUMO DA API CONTATOS
    buscarDados = () => {
        this.setState({ carregando: true });

        Promise.all([
            fetch("http://localhost:8081/api/v1/produtos").then(resposta => resposta.json()),
            fetch("http://localhost:8082/api/v1/categorias").then(resposta => resposta.json())
        ])
            .then(dados => {
                console.log(dados[0], dados[1]);

                const produtos = dados[0].retorno.produtos || [];
                const categorias = dados[1].retorno.categorias || [];

                this.setState({
                    produtos: produtos,
                    categorias: categorias,
                    carregando: false
                });
            })
            .catch(erro => {
                console.log(erro);
                this.setState({ carregando: false });
            });
    }

    //GET - MÉTODO PARA CONSUMO DA API CONTATOS
    carregarProdutos = (codigo) => {
        fetch("http://localhost:8081/api/v1/produto/" + codigo, { method: 'GET' })
            .then(resposta => resposta.json())
            .then(dados => {
                if (dados.retorno.produtos) {
                    this.setState({
                        id: dados.retorno.produtos[0].produto.id,
                        codigo: dados.retorno.produtos[0].produto.codigo,
                        descricao: dados.retorno.produtos[0].produto.descricao,
                        tipo: dados.retorno.produtos[0].produto.tipo,
                        situacao: dados.retorno.produtos[0].produto.situacao,
                        unidade: dados.retorno.produtos[0].produto.unidade,
                        preco: dados.retorno.produtos[0].produto.preco,
                        precoCusto: dados.retorno.produtos[0].produto.precoCusto,
                        descricaoCurta: dados.retorno.produtos[0].produto.descricaoCurta,
                        descricaoComplementar: dados.retorno.produtos[0].produto.descricaoComplementar,
                        dataInclusao: dados.retorno.produtos[0].produto.dataInclusao,
                        dataAlteracao: dados.retorno.produtos[0].produto.dataAlteracao,
                        imageThumbnail: dados.retorno.produtos[0].produto.imageThumbnail,
                        urlVideo: dados.retorno.produtos[0].produto.urlVideo,
                        nomeFornecedor: dados.retorno.produtos[0].produto.nomeFornecedor,
                        codigoFabricante: dados.retorno.produtos[0].produto.codigoFabricante,
                        marca: dados.retorno.produtos[0].produto.marca,
                        class_fiscal: dados.retorno.produtos[0].produto.class_fiscal,
                        cest: dados.retorno.produtos[0].produto.cest,
                        origem: dados.retorno.produtos[0].produto.origem,
                        idGrupoProduto: dados.retorno.produtos[0].produto.idGrupoProduto,
                        linkExterno: dados.retorno.produtos[0].produto.linkExterno,
                        observacoes: dados.retorno.produtos[0].produto.observacoes,
                        grupoProduto: dados.retorno.produtos[0].produto.grupoProduto,
                        garantia: dados.retorno.produtos[0].produto.garantia,
                        descricaoFornecedor: dados.retorno.produtos[0].produto.descricaoFornecedor,
                        idFabricante: dados.retorno.produtos[0].produto.idFabricante,
                        pesoLiq: dados.retorno.produtos[0].produto.pesoLiq,
                        pesoBruto: dados.retorno.produtos[0].produto.pesoBruto,
                        estoqueMinimo: dados.retorno.produtos[0].produto.estoqueMinimo,
                        estoqueMaximo: dados.retorno.produtos[0].produto.estoqueMaximo,
                        gtin: dados.retorno.produtos[0].produto.gtin,
                        gtinEmbalagem: dados.retorno.produtos[0].produto.gtinEmbalagem,
                        larguraProduto: dados.retorno.produtos[0].produto.larguraProduto,
                        alturaProduto: dados.retorno.produtos[0].produto.alturaProduto,
                        profundidadeProduto: dados.retorno.produtos[0].produto.profundidadeProduto,
                        unidadeMedida: dados.retorno.produtos[0].produto.unidadeMedida,
                        itensPorCaixa: dados.retorno.produtos[0].produto.itensPorCaixa,
                        volumes: dados.retorno.produtos[0].produto.volumes,
                        localizacao: dados.retorno.produtos[0].produto.localizacao,
                        crossdocking: dados.retorno.produtos[0].produto.crossdocking,
                        condicao: dados.retorno.produtos[0].produto.condicao,
                        freteGratis: dados.retorno.produtos[0].produto.freteGratis,
                        producao: dados.retorno.produtos[0].produto.producao,
                        dataValidade: dados.retorno.produtos[0].produto.dataValidade,
                        spedTipoItem: dados.retorno.produtos[0].produto.spedTipoItem,
                        descricaoCategoria: dados.retorno.produtos[0].produto.categoria.descricao,
                        categoriaId: dados.retorno.produtos[0].produto.categoria.id
                    })

                } else {
                    this.setState({ produtos: [] })
                }
                this.setState({ carregando: false });
                this.abrirModal();
            })

            .catch(error => console.error(error));
    }

    excluirProduto(codigo) {
        fetch(`http://localhost:8081/api/v1/produto/${codigo}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(resposta => resposta.json())
            .then(dados => {
                console.log(dados);
                this.buscarDados(); // atualiza a lista de produtos após a exclusão
            })
            .catch(erro => console.error(erro));
    }


    //POST - MÉTODO PARA INSERIR UM NOVO CONTATO NA API CONTATOS
    cadastraProduto = (xmlProduto) => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(xmlProduto, 'text/xml');
        const stringXml = new XMLSerializer().serializeToString(xml);

        fetch('http://localhost:8081/api/v1/cadastrarproduto', {
            method: 'POST',
            body: stringXml,
            headers: {
                'Content-Type': 'application/xml'
            }
        })
    }

    //PUT - MÉTODO PARA ATUALIZAR UM CONTATO EXISTENTE NA API CONTATOS
    atualizarProduto = (xmlProduto) => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(xmlProduto, 'text/xml');
        const stringXml = new XMLSerializer().serializeToString(xml);
        const codigo = xml.querySelector('codigo').textContent;

        fetch('http://localhost:8081/api/v1/atualizarproduto/' + codigo, {
            method: 'POST',
            body: stringXml,
            headers: {
                'Content-Type': 'application/xml'
            }
        })
    }


    atualizaCodigo = (e) => {
        this.setState({
            codigo: e.target.value
        })
    }

    atualizaDescricao = (e) => {
        this.setState({
            descricao: e.target.value
        })
    }

    atualizaSituacao = (e) => {
        this.setState({
            situacao: e.target.value
        })
    }

    atualizaTipo = (e) => {
        this.setState({
            tipo: e.target.value
        })
    }

    atualizaPreco = (e) => {
        this.setState({
            preco: e.target.value
        })
    }

    atualizaUnidade = (e) => {
        this.setState({
            un: e.target.value,
            unidade: e.target.value
        })
    }

    atualizaCondicao = (e) => {
        this.setState({
            condicao: e.target.value
        })
    }

    atualizaMarca = (e) => {
        this.setState({
            marca: e.target.value
        })
    }

    atualizaProducao = (e) => {
        this.setState({
            producao: e.target.value
        })
    }

    atualizaDataValidade = (e) => {
        this.setState({
            dataValidade: e.target.value
        })
    }

    atualizaFreteGratis = (e) => {
        this.setState({
            freteGratis: e.target.value
        })
    }

    atualizaPesoLiq = (e) => {
        this.setState({
            pesoLiq: e.target.value
        })
    }

    atualizaPesoBruto = (e) => {
        this.setState({
            pesoBruto: e.target.value
        })
    }

    atualizaLarguraProduto = (e) => {
        this.setState({
            larguraProduto: e.target.value
        })
    }

    atualizaAlturaProduto = (e) => {
        this.setState({
            alturaProduto: e.target.value
        })
    }

    atualizaProfundidadeProduto = (e) => {
        this.setState({
            profundidadeProduto: e.target.value
        })
    }

    atualizaVolumes = (e) => {
        this.setState({
            volumes: e.target.value
        })
    }

    atualizaItensPorCaixa = (e) => {
        this.setState({
            itensPorCaixa: e.target.value
        })
    }

    atualizaUnidadeMedida = (e) => {
        this.setState({
            unidadeMedida: e.target.value
        })
    }

    atualizaGtin = (e) => {
        this.setState({
            gtin: e.target.value
        })
    }

    atualizaGtinEmbalagem = (e) => {
        this.setState({
            gtinEmbalagem: e.target.value
        })
    }

    atualizaDescricaoCurta = (e) => {
        this.setState({
            descricaoCurta: e.target.value
        })
    }

    atualizaDescricaoComplementar = (e) => {
        this.setState({
            descricaoComplementar: e.target.value
        })
    }

    atualizaLinkExterno = (e) => {
        this.setState({
            linkExterno: e.target.value
        })
    }

    atualizaUrlVideo = (e) => {
        this.setState({
            urlVideo: e.target.value
        })
    }

    atualizaObservacoes = (e) => {
        this.setState({
            observacoes: e.target.value
        })
    }

    atualizaEstoqueMinimo = (e) => {
        this.setState({
            estoqueMinimo: e.target.value
        })
    }

    atualizaEstoqueMaximo = (e) => {
        this.setState({
            estoqueMaximo: e.target.value
        })
    }

    atualizaCrossdocking = (e) => {
        this.setState({
            crossdocking: e.target.value
        })
    }

    atualizaLocalizacao = (e) => {
        this.setState({
            localizacao: e.target.value
        })
    }

    atualizaCategoriaDescricao = (e) => {
        this.setState({
            categoriaId: e.target.value, //Valor que capturo do Json servidor Bling
            idCategoria: e.target.value  // Valor que envio para o servidor do Bling.
        });
    }


    //Ações do botão SUBMIT (Cadastrar).
    submit = (event) => {
        event.preventDefault();

        if (this.state.id === 0) {
            const produto = {
                codigo: this.state.codigo,
                descricao: this.state.descricao,
                tipo: this.state.tipo,
                situacao: this.state.situacao,
                un: this.state.un,
                preco: this.state.preco,
                precoCusto: this.state.precoCusto,
                descricaoCurta: this.state.descricaoCurta,
                descricaoComplementar: this.state.descricaoComplementar,
                dataInclusao: this.state.dataInclusao,
                dataAlteracao: this.state.dataAlteracao,
                imageThumbnail: this.state.imageThumbnail,
                urlVideo: this.state.urlVideo,
                nomeFornecedor: this.state.nomeFornecedor,
                codigoFabricante: this.state.codigoFabricante,
                marca: this.state.marca,
                class_fiscal: this.state.class_fiscal,
                cest: this.state.cest,
                origem: this.state.origem,
                idGrupoProduto: this.state.idGrupoProduto,
                linkExterno: this.state.linkExterno,
                observacoes: this.state.observacoes,
                grupoProduto: this.state.grupoProduto,
                garantia: this.state.garantia,
                descricaoFornecedor: this.state.descricaoFornecedor,
                idFabricante: this.state.idFabricante,

                categoria: [],
                idCategoria: this.state.idCategoria,
                descricaoCategoria: this.state.descricaoCategoria,

                pesoLiq: this.state.pesoLiq,
                pesoBruto: this.state.pesoBruto,
                estoqueMinimo: this.state.estoqueMinimo,
                estoqueMaximo: this.state.estoqueMaximo,
                gtin: this.state.gtin,
                gtinEmbalagem: this.state.gtinEmbalagem,
                larguraProduto: this.state.larguraProduto,
                alturaProduto: this.state.alturaProduto,
                profundidadeProduto: this.state.profundidadeProduto,
                unidadeMedida: this.state.unidadeMedida,
                itensPorCaixa: this.state.itensPorCaixa,
                volumes: this.state.volumes,
                localizacao: this.state.localizacao,
                crossdocking: this.state.crossdocking,
                condicao: this.state.condicao,
                freteGratis: this.state.freteGratis,
                producao: this.state.producao,
                dataValidade: this.state.dataValidade,
                spedTipoItem: this.state.spedTipoItem
            }
            const xmlProduto = parse('produto', produto);
            this.cadastraProduto(xmlProduto);
        } else {
            const produto = {
                id: this.state.id,
                codigo: this.state.codigo,
                descricao: this.state.descricao,
                tipo: this.state.tipo,
                situacao: this.state.situacao,
                un: this.state.un,
                preco: this.state.preco,
                precoCusto: this.state.precoCusto,
                descricaoCurta: this.state.descricaoCurta,
                descricaoComplementar: this.state.descricaoComplementar,
                dataInclusao: this.state.dataInclusao,
                dataAlteracao: this.state.dataAlteracao,
                imageThumbnail: this.state.imageThumbnail,
                urlVideo: this.state.urlVideo,
                nomeFornecedor: this.state.nomeFornecedor,
                codigoFabricante: this.state.codigoFabricante,
                marca: this.state.marca,
                class_fiscal: this.state.class_fiscal,
                cest: this.state.cest,
                origem: this.state.origem,
                idGrupoProduto: this.state.idGrupoProduto,
                linkExterno: this.state.linkExterno,
                observacoes: this.state.observacoes,
                grupoProduto: this.state.grupoProduto,
                garantia: this.state.garantia,
                descricaoFornecedor: this.state.descricaoFornecedor,
                idFabricante: this.state.idFabricante,

                categoria: [],
                idCategoria: this.state.idCategoria,
                descricaoCategoria: this.state.descricaoCategoria,

                pesoLiq: this.state.pesoLiq,
                pesoBruto: this.state.pesoBruto,
                estoqueMinimo: this.state.estoqueMinimo,
                estoqueMaximo: this.state.estoqueMaximo,
                gtin: this.state.gtin,
                gtinEmbalagem: this.state.gtinEmbalagem,
                larguraProduto: this.state.larguraProduto,
                alturaProduto: this.state.alturaProduto,
                profundidadeProduto: this.state.profundidadeProduto,
                unidadeMedida: this.state.unidadeMedida,
                itensPorCaixa: this.state.itensPorCaixa,
                volumes: this.state.volumes,
                localizacao: this.state.localizacao,
                crossdocking: this.state.crossdocking,
                condicao: this.state.condicao,
                freteGratis: this.state.freteGratis,
                producao: this.state.producao,
                dataValidade: this.state.dataValidade,
                spedTipoItem: this.state.spedTipoItem
            }
            const xmlProduto = parse('produto', produto);
            this.atualizarProduto(xmlProduto);
        }
        const form = event.currentTarget;

        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation(); // se algum campo obrigatorio nãao for preenchidos o modal é travado
            this.setState({ validated: true }); // atribui true na validação
        } else {
            event.preventDefault();
            this.setState({ validated: true }); // atribui true na validação
            this.fecharModal(); // se todos os campos estiverem preenchidos o modal é fechado
            this.buscarDados(); // atualiza a lista de produtos após a exclusão
        }
    }

    //Ação para limpar o campos do modal para cadastrar um novo cliente.
    reset = () => {
        this.setState(
            {
                id: 0,
                codigo: '',
                descricao: '',
                tipo: '',
                situacao: '',
                unidade: '',
                preco: '',
                precoCusto: '',
                descricaoCurta: '',
                descricaoComplementar: '',
                dataInclusao: '',
                dataAlteracao: '',
                imageThumbnail: '',
                urlVideo: '',
                nomeFornecedor: '',
                codigoFabricante: '',
                marca: '',
                class_fiscal: '',
                cest: '',
                origem: '',
                idGrupoProduto: '',
                linkExterno: '',
                observacoes: '',
                grupoProduto: '',
                garantia: '',
                descricaoFornecedor: '',
                idFabricante: '',

                categoria: [],
                descricaoCategoria: '',

                pesoLiq: '',
                pesoBruto: '',
                estoqueMinimo: '',
                estoqueMaximo: '',
                gtin: '',
                gtinEmbalagem: '',
                larguraProduto: '',
                alturaProduto: '',
                profundidadeProduto: '',
                unidadeMedida: '',
                itensPorCaixa: '',
                volumes: '',
                localizacao: '',
                crossdocking: '',
                condicao: '',
                freteGratis: '',
                producao: '',
                dataValidade: '',
                spedTipoItem: '',
            })
        this.abrirModal();
    }

    //Ação para fechar o modal de cadastro e atualização.
    fecharModal = () => {
        this.setState({
            modalAberta: false,
            validated: false
        });

        this.buscarDados();
    }


    //Ação para abrir o modal de cadastro e atualização.
    abrirModal = () => {
        this.setState(
            {
                modalAberta: true
            }
        )
    }

    render() {
        if (this.state.carregando) {
            return (
                <div className="spinner-container" >
                    <div className="d-flex align-items-center justify-content-center">
                        <Spinner variant="secondary" animation="border" role="status">
                            <span className="visually-hidden">Carregando produtos...</span>
                        </Spinner>
                    </div>
                    <div>
                        <p>Carregando produtos...</p>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="background">
                    <div className="container">
                        <div>
                            <Button variant="success" bsPrefix="btn-cadastro-button" onClick={this.reset}>
                                + Incluir Cadastro
                            </Button>
                        </div>
                        <div>
                            <div className="table-tabela">
                                <Table striped bordered hover className="table-dark" responsive="sm">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th title="Descrição">Descrição</th>
                                            <th title="Código">Código</th>
                                            <th title="Unidade">Unidade</th>
                                            <th title="Preço">Preço</th>
                                            <th title="Estoque">Estoque</th>
                                            <th title="Opções">Opções</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.produtos.map((produtos) =>
                                                <tr key={produtos.produto.id}>
                                                    <td>{produtos.produto.id}</td>
                                                    <td>{produtos.produto.descricao}</td>
                                                    <td>{produtos.produto.codigo}</td>
                                                    <td>{produtos.produto.unidade}</td>
                                                    <td>{produtos.produto.preco}</td>
                                                    <td>{produtos.produto.estoqueMaximo}</td>
                                                    <td>
                                                        <div className="button-container-table">
                                                            <Button variant="warning" onClick={() => this.carregarProdutos(produtos.produto.codigo)}>
                                                                <FaSync />
                                                            </Button>
                                                            <Button variant="danger" onClick={() => this.excluirProduto(produtos.produto.codigo)}>
                                                                <FaTrash />
                                                            </Button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            )
                                        }
                                        {this.state.produtos.length === 0 && <tr><td colSpan="6">Nenhum produto cadastrado.</td></tr>}
                                    </tbody>
                                </Table>
                            </div>
                        </div>
                        {this.renderModal()}
                    </div>
                </div>
            )
        }
    }

    renderModal() {
        return (
            <Modal show={this.state.modalAberta} onHide={this.fecharModal} size="xl" backdrop="static" >
                <Modal.Header closeButton>
                    <Modal.Title>Cadastro/Atualização de Produto</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate validated={this.state.validated} onSubmit={this.submit}>
                        <Container>
                            <Row>
                                <Col xs={2} md={2}>
                                    <Form.Group controlId="id" className="mb-3 form-row" as={Col}>
                                        <Form.Label type="text">ID</Form.Label>
                                        <Form.Control type="text" value={this.state.id || ''} readOnly disabled />
                                    </Form.Group>
                                </Col>
                                <Col xs={6} md={8}>
                                    <Form.Group controlId="nome" className="mb-3">
                                        <Form.Label>Nome</Form.Label>
                                        <Form.Control type="text" placeholder="Digite o nome" value={this.state.descricao || ''} onChange={this.atualizaDescricao} required />
                                        <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={6} md={2}>
                                    <Form.Group controlId="Situacao" className="mb-3">
                                        <Form.Label>Situação</Form.Label>
                                        <Form.Select as="select" placeholder="Situacao" value={this.state.situacao || ''} onChange={this.atualizaSituacao} >
                                            <option value="Ativo">Ativo</option>
                                            <option value="Inativo">Inativo</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="codigo" className="mb-3">
                                        <Form.Label>Código (SKU)</Form.Label>
                                        <Form.Control type="text" placeholder="Digite o código" value={this.state.codigo || ''} onChange={this.atualizaCodigo} required />
                                        <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                {/* <Col xs={2} md={4}>
                                    <Form.Group controlId="Formato" className="mb-3">
                                        <Form.Label>Formato</Form.Label>
                                        <Form.Select as="select" placeholder="Tipo de formato" value={''} onChange={this.atualizaDescricao} >
                                            <option value="">Simples</option>
                                            <option value="">Com variação</option>
                                            <option value="">Com composição</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col> */}
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="Formato" className="mb-3">
                                        <Form.Label>Tipo</Form.Label>
                                        <Form.Select as="select" placeholder="Selecione o tipo" value={this.state.tipo || ''} onChange={this.atualizaTipo} >
                                            <option value="P">Produto</option>
                                            <option value="S">Serviço</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="nome" className="mb-3">
                                        <Form.Label>Preço venda</Form.Label>
                                        <Form.Control type="text" placeholder="Digite o preço de venda" value={this.state.preco || ''} onChange={this.atualizaPreco} />
                                    </Form.Group>
                                </Col>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="nome" className="mb-3">
                                        <Form.Label>Unidade</Form.Label>
                                        <Form.Control type="text" placeholder="Digite a unidade (pc, un, cx)" value={this.state.unidade || ''} onChange={this.atualizaUnidade} />
                                    </Form.Group>
                                </Col>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="Formato" className="mb-3">
                                        <Form.Label>Condição</Form.Label>
                                        <Form.Select as="select" placeholder="Selecione a condição" value={this.state.condicao || ''} onChange={this.atualizaCondicao} >
                                            <option value="Não Especificado">Não Especificado</option>
                                            <option value="Novo">Novo</option>
                                            <option value="Usado">Usado</option>
                                            <option value="Recondicionado">Recondicionado</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col>
                            </Row>

                            <Tabs defaultActiveKey="caracteristica" id="fill-tab-example" className="mb-3" fill>
                                <Tab eventKey="caracteristica" title="Caracteristica">
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="marca" className="mb-3">
                                                <Form.Label>Marca</Form.Label>
                                                <Form.Control type="text" placeholder="Digite o nome" value={this.state.marca || ''} onChange={this.atualizaMarca} />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="produção" className="mb-3">
                                                <Form.Label>Tipo Contato</Form.Label>
                                                <Form.Select as="select" placeholder="Tipo de contato" value={this.state.producao || ''} onChange={this.atualizaProducao} >
                                                    <option value="P">Própria</option>
                                                    <option value="T">Terceiros</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                        {/* <Col xs={2} md={3}>
                                            <Form.Group controlId="datavalidade" className="mb-3">
                                                <Form.Label>Data de validade</Form.Label>
                                                <Form.Control type="date" placeholder="Digite o nome" value={this.state.dataValidade || ''} onChange={this.atualizaDataValidade} />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col> */}
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="frete" className="mb-3">
                                                <Form.Label>Frete Grátis</Form.Label>
                                                <Form.Select as="select" placeholder="Selecione o frete" value={this.state.freteGratis || ''} onChange={this.atualizaFreteGratis} >
                                                    <option value="N">Não</option>
                                                    <option value="S">Sim</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="pesoliquido" className="mb-3">
                                                <Form.Label>Peso Líquido </Form.Label>
                                                <Form.Control type="text" placeholder="Insira o peso liquido" value={this.state.pesoLiq || ''} onChange={this.atualizaPesoLiq} />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="pesobruto" className="mb-3">
                                                <Form.Label>Peso Bruto</Form.Label>
                                                <Form.Control type="text" placeholder="Insira o peso bruto" value={this.state.pesoBruto || ''} onChange={this.atualizaPesoBruto} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="largura" className="mb-3">
                                                <Form.Label>Largura</Form.Label>
                                                <Form.Control type="text" placeholder="Insira a largura" value={this.state.larguraProduto || ''} onChange={this.atualizaLarguraProduto} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="altura" className="mb-3">
                                                <Form.Label>Altura</Form.Label>
                                                <Form.Control type="text" placeholder="Insira a altura" value={this.state.alturaProduto || ''} onChange={this.atualizaAlturaProduto} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="profundidade" className="mb-3">
                                                <Form.Label>Profundidade</Form.Label>
                                                <Form.Control type="text" placeholder="Insira a profundidade" value={this.state.profundidadeProduto || ''} onChange={this.atualizaProfundidadeProduto} />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="pesobruto" className="mb-3">
                                                <Form.Label>Volumes</Form.Label>
                                                <Form.Control type="text" placeholder="Insira o volume" value={this.state.volumes || ''} onChange={this.atualizaVolumes} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="itenscaixa" className="mb-3">
                                                <Form.Label>Itens p/ caixa</Form.Label>
                                                <Form.Control type="text" placeholder="Digite o volume" value={this.state.itensPorCaixa || ''} onChange={this.atualizaItensPorCaixa} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="unidade" className="mb-3">
                                                <Form.Label>Unidade de medida</Form.Label>
                                                <Form.Select as="select" placeholder="Selecione o frete" value={this.state.unidadeMedida || ''} onChange={this.atualizaUnidadeMedida} >
                                                    <option value="Metros">Metros</option>
                                                    <option value="Centímetros">Centimetros</option>
                                                    <option value="Milímetro">Milímetro</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="gtin" className="mb-3">
                                                <Form.Label>GTIN/EAN </Form.Label>
                                                <Form.Control type="text" placeholder="GTIN/EAN" value={this.state.gtin || ''} onChange={this.atualizaGtin} />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="gtintributario" className="mb-3">
                                                <Form.Label>GTIN/EAN tributário</Form.Label>
                                                <Form.Control type="text" placeholder="GTIN/EAN tributário" value={this.state.gtinEmbalagem || ''} onChange={this.atualizaGtinEmbalagem} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>

                                            <Form.Group controlId="descricaocurta" className="mb-3">
                                                <Form.Label>Descrição Curta (Descrição Principal) </Form.Label>
                                                <Form.Control as="textarea" rows={3} placeholder="Insira a descrição curta" value={this.state.descricaoCurta || ''} onChange={this.atualizaDescricaoCurta} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="descricaocurta" className="mb-3">
                                                <Form.Label>Descrição Complementar</Form.Label>
                                                <Form.Control as="textarea" rows={3} placeholder="Insira a descrição complementar" value={this.state.descricaoComplementar || ''} onChange={this.atualizaDescricaoComplementar} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="linkexterno" className="mb-3">
                                                <Form.Label>Link Externo</Form.Label>
                                                <Form.Control type="text" placeholder="insira o link externo" value={this.state.linkExterno || ''} onChange={this.atualizaLinkExterno} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="video" className="mb-3">
                                                <Form.Label>Video</Form.Label>
                                                <Form.Control type="text" placeholder="insira o link do video" value={this.state.urlVideo || ''} onChange={this.atualizaUrlVideo} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="observacoes" className="mb-3">
                                                <Form.Label>Observações</Form.Label>
                                                <Form.Control tas="textarea" rows={3} placeholder="insira as observações" value={this.state.observacoes || ''} onChange={this.atualizaObservacoes} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="categoria" className="mb-3">
                                                <Form.Label>Categoria</Form.Label>
                                                <Form.Control as="select" placeholder="insira as observações" value={this.state.categoriaId || ''} onChange={this.atualizaCategoriaDescricao}>
                                                    {this.state.categorias.map((categoria) => (
                                                        <option key={categoria.categoria.id} value={categoria.categoria.id}>
                                                            {categoria.categoria.descricao}
                                                        </option>
                                                    ))}
                                                </Form.Control>
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                </Tab>
                                <Tab eventKey="imagens" title="Imagens">
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="observacoes" className="mb-3">
                                                <Form.Label>Imagens</Form.Label>
                                                <Form.Control tas="textarea" rows={3} placeholder="insira as observações" value={''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                </Tab>
                                <Tab eventKey="estoque" title="Estoque">
                                    <Row>
                                        <Col xs={1} md={3}>
                                            <Form.Group controlId="minimo" className="mb-3">
                                                <Form.Label>Minimo</Form.Label>
                                                <Form.Control tas="text" placeholder="insira o minimo" value={this.state.estoqueMinimo || ''} onChange={this.atualizaEstoqueMinimo} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="maximo" className="mb-3">
                                                <Form.Label>Máximo</Form.Label>
                                                <Form.Control tas="text" placeholder="insira o maximo" value={this.state.estoqueMaximo || ''} onChange={this.atualizaEstoqueMaximo} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="crossdocking" className="mb-3">
                                                <Form.Label>Crossdocking</Form.Label>
                                                <Form.Control tas="text" placeholder="insira o crossdocking" value={this.state.crossdocking || ''} onChange={this.atualizaCrossdocking} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="localizacao" className="mb-3">
                                                <Form.Label>Localização</Form.Label>
                                                <Form.Control tas="text" placeholder="insira a localização" value={this.state.localizacao || ''} onChange={this.atualizaLocalizacao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                </Tab>
                                <Tab eventKey="fornecedores" title="Fornecedores">
                                </Tab>
                            </Tabs>
                            <Container>
                                <Row className="text-center">
                                    <Col>
                                        <Form.Group controlId="buttonSalvar" className="mb-3">
                                            <div className="button-container d-flex justify-content-center">
                                                <Button bsPrefix="btn-salvar-button" variant="primary" type="submit">
                                                    Salvar
                                                </Button>
                                                <Button bsPrefix="btn-cancelar-button" variant="warning" onClick={this.fecharModal}>
                                                    Cancelar
                                                </Button>
                                            </div>
                                        </Form.Group>
                                    </Col>
                                </Row>
                            </Container>
                        </Container>
                    </Form>
                </Modal.Body>
            </Modal >
        )
    }
}

export default Produto;