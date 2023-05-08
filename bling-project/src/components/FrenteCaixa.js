import React from "react";

import '../css/FrenteCaixa.css';

import { IonIcon } from '@ionic/react';
import { trashOutline } from 'ionicons/icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faExclamationTriangle } from '@fortawesome/free-solid-svg-icons'

import { format } from 'date-fns';
import ptBR from 'date-fns/locale/pt-BR';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import Spinner from 'react-bootstrap/Spinner';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Container from 'react-bootstrap/Container'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import { Stack } from "react-bootstrap"
import Form from 'react-bootstrap/Form'
import Table from "react-bootstrap/Table";

import FloatingLabel from 'react-bootstrap/FloatingLabel';
import { parse } from 'js2xmlparser';




class FrenteCaixa extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            buscaProduto: '',
            buscaContato: '',
            buscaVendedor: '',
            produtos: [],
            contatos: [],
            depositos: [],
            vendedores: [],
            produtoSelecionado: null,
            contatoSelecionado: null,
            vendedorSelecionado: null,
            carregandoProduto: false,
            carregandoContato: false,
            carregandoVendedor: false,
            preco: null, // mover o preco para fora do produtoSelecionado
            produtosSelecionados: [],
            contatosSelecionados: [],
            vendedoresSelecionados: [],
            precoProdutoSelecionado: '',
            quantidade: 1,
            valorTotal: '',
            nome: '',
            cnpj: '',
            valorDesconto: '',
            totalComDesconto: '',
            total: 0,
            subTotal: 0,
            dinheiroRecebido: 0,
            troco: 0,
            dinheiro: 0,
            dataPrevista: null,
            depositoSelecionado: null,
            vendedoresFiltrados: [],
            carregando: false,
            observacoes: '',
            observacaointerna: '',
            itensSelecionados: [],
            comentario: '',
            ModalFinalizarVendaSemItem: false,
            ModalExcluirPedido: false,
            modalInserirProduto: false
        };
        this.atualizaDesconto = this.atualizaDesconto.bind(this);
    }

    ModalFinalizarVendaSemItem = () => {
        this.setState({ ModalFinalizarVendaSemItem: !this.state.ModalFinalizarVendaSemItem });
    }

    ModalExcluirPedido = () => {
        this.setState({ ModalExcluirPedido: !this.state.ModalExcluirPedido });
    }

    modalInserirProduto = () => {
        this.setState({ modalInserirProduto: !this.state.modalInserirProduto });
    }

    componentDidMount() {
        this.calcularTotal();
        this.buscarDeposito();
        this.buscarVendedor();
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.nome !== this.state.nome) {
            this.atualizaNome({ target: { value: this.state.nome } });
        }
        if (prevState.cnpj !== this.state.cnpj) {
            this.atualizaCpfCnpj({ target: { value: this.state.cnpj } });
        }
    }

    buscarProdutos = (value) => {
        console.log("Buscando produto por:", value);
        this.setState({ buscaProduto: value, carregando: true });
        fetch(`http://localhost:8081/api/v1/produtos`)
            .then((resposta) => {
                if (!resposta.ok) {
                    throw new Error('Erro na chamada da API');
                }
                return resposta.json();
            })
            .then((dados) => {
                if (dados.retorno.produtos) {
                    const produtosFiltrados = dados.retorno.produtos.filter(
                        (produto) =>
                            (produto.produto.descricao && produto.produto.descricao.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.codigo && produto.produto.codigo.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.gtin && produto.produto.gtin.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.gtinEmbalagem && produto.produto.gtinEmbalagem.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.descricaoFornecedor && produto.produto.descricaoFornecedor.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.idFabricante && produto.produto.idFabricante.toLowerCase().includes(value.toLowerCase()))
                    );
                    console.log("Produto objeto retornado:", produtosFiltrados);
                    this.setState({
                        produtos: produtosFiltrados,
                        produtoSelecionado: null,
                        carregando: false,
                    });
                } else {
                    this.setState({ produtos: [], carregando: false });
                }
            })
            .catch((error) => {
                console.log("Erro ao buscar produtos:", error);
                this.setState({ produtos: [], carregando: false });
            });
    };


    buscarContato = (value) => {
        console.log("Buscando contato por:", value);
        this.setState({ buscaContato: value, carregando: true });
        fetch(`http://localhost:8080/api/v1/contatos`)
            .then((resposta) => {
                if (!resposta.ok) {
                    throw new Error('Erro na chamada da API');
                }
                return resposta.json();
            })
            .then((dados) => {
                if (dados.retorno.contatos) {
                    const contatosFiltrados = dados.retorno.contatos.filter(
                        (contato) =>
                            (contato.contato.nome && contato.contato.nome.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.cnpj && contato.contato.cnpj.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.rg && contato.contato.rg.toLowerCase().includes(value.toLowerCase()))
                    );
                    console.log("Contato objeto retornado:", contatosFiltrados);
                    this.setState({
                        contatos: contatosFiltrados,
                        contatoSelecionado: null,
                        carregando: false,
                    });
                } else {
                    this.setState({ contatos: [], carregando: false });
                }
            })
            .catch((error) => {
                console.log("Erro ao buscar contatos:", error);
                this.setState({ contatos: [], carregando: false });
            });
    };

    buscarVendedor = (value) => {
        console.log("Buscando vendedor por:", value);
        this.setState({ buscaVendedor: value, carregando: true });
        fetch(`http://localhost:8080/api/v1/contatos`)
            .then((resposta) => {
                if (!resposta.ok) {
                    throw new Error("Erro na chamada da API");
                }
                return resposta.json();
            })
            .then((dados) => {
                if (dados.retorno.contatos) {
                    console.log("Vendedor objeto retornado:", dados);

                    const vendedoresFiltrados = dados.retorno.contatos.filter(
                        (contato) => contato?.contato?.tiposContato?.some((tipoContato) => tipoContato?.tipoContato?.descricao?.toLowerCase().includes('vendedor'))
                    );
                    this.setState({
                        vendedores: vendedoresFiltrados,
                        vendedorSelecionado: null,
                        carregando: false,
                        vendedoresFiltrados: vendedoresFiltrados // adiciona essa linha
                    });
                } else {
                    this.setState({ vendedores: [], carregando: false });
                }
            })
            .catch((error) => {
                console.log("Erro ao buscar vendedor:", error);
                this.setState({ vendedores: [], carregando: false });
            });
    };

    buscarDeposito = () => {
        fetch("http://localhost:8083/api/v1/depositos")
            .then(resposta => resposta.json())
            .then(dados => {
                if (dados.retorno.depositos) {
                    console.log("Deposito objeto retornado:", dados);

                    this.setState({
                        depositos: dados.retorno.depositos,
                        depositoSelecionado: dados.retorno.depositos[0].deposito.id // define o primeiro depósito como selecionado
                    })
                } else {
                    this.setState({ depositos: [] })
                }
                this.setState({ carregando: false })
            })
            .catch((error) => {
                console.log("Erro ao buscar deposito:", error);
                this.setState({ depositos: [], carregando: false });
            });
    }


    cadastrarPedido = (xmlPedido) => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(xmlPedido, 'text/xml');
        const stringXml = new XMLSerializer().serializeToString(xml);

        fetch('http://localhost:8085/api/v1/cadastrarpedido', {
            method: 'POST',
            body: stringXml,
            headers: {
                'Content-Type': 'application/xml'
            }
        });
    };


    selecionarProduto = (produto) => {
        const precoEmReais = parseFloat(produto.produto.preco).toFixed(2).replace('.', ',');
        const valorTotal = (parseFloat(produto.produto.preco.replace(',', '.')) * 1).toFixed(2);
        this.setState({
            produtoSelecionado: produto,
            preco: precoEmReais,
            produtos: [],
            quantidade: 1,
            valorTotal: valorTotal,
            comentario: '',
        });
        this.atualizarBuscaProduto({ target: { value: '' } });
    };


    selecionarContato = (contato) => {
        this.setState({
            contatoSelecionado: contato,
            nome: contato.contato.nome,
            tipo: contato.contato.tipo,
            cnpj: contato.contato.cnpj,
            codigo: contato.contato.codigo,
            fantasia: contato.contato.fantasia,
            contatos: [],
        });
        this.atualizarBuscaContato({ target: { value: '' } });
    };

    selecionarVendedor = (contato) => {
        this.setState({
            contatoSelecionado: contato,
            descricao: contato.contato[0].contato.tiposContato[0].tipoContato.descricao,
            contatos: [],
        });
        this.atualizarBuscaContato({ target: { value: '' } });
    };

    adicionarProdutoSelecionado = (produtoSelecionado) => {
        if (!produtoSelecionado) {
            this.modalInserirProduto()
            return;
        }

        const { produtosSelecionados, quantidade } = this.state;
        const produtoExistenteIndex = produtosSelecionados.findIndex((produto) => produto.produto.id === produtoSelecionado.produto.id);

        if (produtoExistenteIndex !== -1) {
            produtosSelecionados[produtoExistenteIndex].quantidade += quantidade;
        } else {
            produtosSelecionados.push({
                produto: produtoSelecionado.produto,
                quantidade: quantidade,
            });
        }

        this.setState({
            produtosSelecionados: produtosSelecionados,
            produtoSelecionado: null,
            buscaProduto: '',
            produtos: [],
            quantidade: 1,
            valorTotal: '',
            desconto: '',
            preco: '',
            comentario: '',
        }, () => {
            this.calcularTotal();
        });
    }


    adicionarContatoSelecionado = (contatoSelecionado) => {
        const { contatosSelecionados, cnpj } = this.state;
        const contatoExistente = contatosSelecionados.find((contato) => contato.contato.id === contatoSelecionado.contato.id);

        if (contatoExistente) {
            contatoExistente.cnpj += cnpj; // Adiciona a quantidade selecionada
        } else {
            contatosSelecionados.push({
                nome: contatoSelecionado.nome,
                cnpj: cnpj, // Salva a quantidade selecionada
            });
        }

        this.setState({
            contatosSelecionados: contatosSelecionados,
            contatoSelecionado: null,
            cnpj: '',
            contatos: [],
            buscaContato: '',
        });
    };

    adicionarVendedorSelecionado = (vendedorSelecionado) => {
        const { contatosSelecionados, cnpj } = this.state;
        const contatoExistente = contatosSelecionados.find((contato) => contato.contato.id === vendedorSelecionado.contato.id);

        if (contatoExistente) {
            contatoExistente.cnpj += cnpj; // Adiciona a quantidade selecionada
        } else {
            contatosSelecionados.push({
                nome: vendedorSelecionado.nome,
                descricao: vendedorSelecionado[0].contato.tiposContato[0].tipoContato.descricao
            });
        }

        this.setState({
            contatosSelecionados: contatosSelecionados,
            contatoSelecionado: null,
            nome: '',
            descricao: '',
            contatos: [],
            buscaContato: '',
        });
    };

    excluirProdutoSelecionado = (index) => {
        const produtosSelecionados = [...this.state.produtosSelecionados];
        const produtoExcluido = produtosSelecionados.splice(index, 1)[0];
        const quantidadeExcluida = produtoExcluido.quantidade;
        const produto = produtoExcluido.produto;
        const subTotalAnterior = this.state.subTotal;
        const subTotal = (parseFloat(subTotalAnterior) - this.calcularSubTotal(produto, quantidadeExcluida)).toFixed(2);
        console.log("Novo Subtotal:", subTotal);
        this.setState({
            produtosSelecionados,
            subTotal,
        });
    };


    calcularValorTotalInicial = () => {
        const { preco } = this.state;
        const quantidade = 1;
        const valorTotal = quantidade * parseFloat(preco.replace(',', '.'));
        this.setState({ quantidade, valorTotal });
    };


    calcularTotal() {
        let total = 0;
        this.state.produtosSelecionados.forEach((produto) => {
            total += this.calcularSubTotal(produto.produto, produto.quantidade);
        });
        const subTotal = total.toFixed(2);
        console.log("Subtotal:", subTotal);

        if (this.state.subTotal !== subTotal) {
            this.setState({ subTotal });
        }
        return parseFloat(subTotal); // retorna o valor do subtotal como número
    }

    calcularSubTotal = (produto, quantidade) => {
        const preco = produto.preco;
        return preco * quantidade;
    }

    calcularTotalComDesconto = (desconto, subTotal) => {
        const subtotal = subTotal || this.calcularTotal();
        const valorDesconto = desconto || 0;
        const totalComDesconto = subtotal - valorDesconto;

        const formattedValorDesconto = valorDesconto.toFixed(2);
        const formattedTotalComDesconto = totalComDesconto.toFixed(2);

        console.log("valorDesconto: ", formattedValorDesconto);
        console.log("totalComDesconto: ", formattedTotalComDesconto);

        if (isNaN(totalComDesconto)) {
            console.log("Total com desconto é NaN!");
        }

        return {
            valorDesconto: formattedValorDesconto,
            totalComDesconto: formattedTotalComDesconto
        };
    }


    calcularTotalComDinheiro = (dinheiro, totalComDesconto) => {
        const totalRecebidoEmDinheiro = parseFloat(dinheiro) || 0;

        if (isNaN(totalRecebidoEmDinheiro)) {
            console.log("O valor total recebido em dinheiro não é um número!");
            return {
                dinheiroRecebido: 0,
                troco: 0,
            };
        }

        const troco = totalRecebidoEmDinheiro - totalComDesconto;

        console.log("totalRecebidoEmDinheiro:", totalRecebidoEmDinheiro);
        console.log("troco:", troco);

        return {
            dinheiroRecebido: totalRecebidoEmDinheiro.toFixed(2),
            troco: troco.toFixed(2),
        };
    };

    atualizaTroco = (event) => {
        const valorRecebido = event.target.value;

        // Verifica se o valor recebido é um número válido antes de chamar a função
        if (!isNaN(parseFloat(valorRecebido))) {
            const { totalComDesconto } = this.state;
            const { dinheiroRecebido, troco } = this.calcularTotalComDinheiro(totalComDesconto, valorRecebido);

            this.setState({ dinheiroRecebido, troco });
        }
    };


    atualizarBuscaProduto = (e) => {
        this.setState({
            buscaProduto: e.target.value
        });
    };

    atualizarBuscaContato = (e) => {
        this.setState({
            buscaContato: e.target.value
        });
    };

    atualizarBuscaVendedor = (e) => {
        this.setState({
            buscaVendedor: e.target.value
        });
    };

    atualizaPreco = (e) => {
        const preco = parseFloat(e.target.value.replace(',', '.'));
        this.setState({ preco }, this.atualizarValorTotal);
    };

    atualizaQuantidade = (e) => {
        const quantidade = Number(e.target.value);
        this.setState({ quantidade }, this.atualizarValorTotal);
    };

    atualizarValorTotal = () => {
        const { quantidade, preco } = this.state;
        const valorTotal = (quantidade * parseFloat(preco.replace(',', '.')));
        this.setState({ valorTotal });
    };

    atualizaDesconto(event) {
        console.log("Evento de digitação capturado!");

        const descontoString = event.target.value.replace(',', '.'); // Substitui a vírgula decimal por ponto
        const descontoNumber = parseFloat(descontoString.replace(/[^\d.-]/g, '')); // remove caracteres não-numéricos

        if (isNaN(descontoNumber)) {
            // Se não for um número válido, limpa o valor do campo e retorna
            this.setState({
                valorDesconto: 0,
                totalComDesconto: ''
            });
            return;
        }

        const resultado = this.calcularTotalComDesconto(descontoNumber);
        console.log(resultado)
        this.setState({
            valorDesconto: descontoString,
            totalComDesconto: resultado.totalComDesconto
        });
    }


    incrementarQuantidade = () => {
        this.setState((prevState) => ({ quantidade: prevState.quantidade + 1 }), this.atualizarValorTotal);
    };

    decrementarQuantidade = () => {
        this.setState((prevState) => ({
            quantidade: prevState.quantidade > 1 ? prevState.quantidade - 1 : 1
        }), this.atualizarValorTotal);
    };

    atualizaNome = (e) => {
        console.log("Nome:", e.target.value);
        this.setState({
            nome: e.target.value
        });
    };

    atualizaTipo = (e) => {
        console.log("Tipo:", e.target.value);
        this.setState({
            tipo: e.target.value
        });
    };

    atualizaCpfCnpj = (e) => {
        console.log("CPF/CNPJ:", e.target.value);
        this.setState({
            cnpj: e.target.value
        });
    };

    atualizaCodigo = (e) => {
        console.log("Codigo:", e.target.value);
        this.setState({
            codigo: e.target.value
        });
    };

    atualizaFantasia = (e) => {
        console.log("Fantasia:", e.target.value);
        this.setState({
            fantasia: e.target.value
        });
    };

    atualizaDataPrevista = (dataPrevista) => {
        console.log(dataPrevista);
        this.setState({
            dataPrevista
        });
    }



    atualizaDepositoSelecionado = (event) => {
        const depositoSelecionado = event.target.value;
        console.log(depositoSelecionado); //Esta retornando ID
        this.setState({ depositoSelecionado });
    };

    atualizaVendedorSelecionado = (event) => {
        const vendedorSelecionado = event.target.value;
        console.log("vendedor selecionado:", vendedorSelecionado);
        this.setState({
            vendedorSelecionado: vendedorSelecionado,
            vendedor: vendedorSelecionado
        }, () => {
            console.log("vendedor:", this.state.vendedor);
        });
    };

    atualizaObservacoes = (event) => {
        const observacoes = event.target.value;
        console.log(event.target.value);
        this.setState({ observacoes });
    }

    atualizaObservacaoInterna = (event) => {
        const observacaointerna = event.target.value;
        console.log(event.target.value);
        this.setState({ observacaointerna });
    }

    atualizarComentario = (event) => {
        const comentario = event.target.value;
        console.log("Comentario", event.target.value);
        this.setState({ comentario });
    }

    //Ação para limpar o campos do modal para cadastrar um novo cliente.
    excluirPedido = () => {
        this.setState({
            nome: '',
            tipo: '',
            cnpj: '',
            codigo: '',
            fantasia: '',
            quantidade: 1,
            desconto: '',
            preco: 0,
            valorTotal: 0,
            subTotal: 0,
            valorDesconto: 0,
            totalComDesconto: 0,
            dinheiro: 0,
            troco: 0,
            dataPrevista: '',
            depositoSelecionado: '',
            frete: 0,
            observacoes: '',
            observacaointerna: '',
            produtosSelecionados: [], // adiciona a limpeza da lista aqui
            modalAberto: false, // fecha o modal após a exclusão
            comentario: ''
        });
        this.ModalExcluirPedido()
    };

    finalizaVenda = () => {
        const nome = this.state.nome;
        const cnpj = this.state.cnpj;
        const vendedor = this.state.nome;
        const dataPrevista = this.state.dataPrevista;
        const observacoes = this.state.observacoes;
        const observacaointerna = this.state.observacaointerna;
        const itens = [];

        this.state.produtosSelecionados.forEach((produto) => {
            const item = {
                codigo: produto.produto.codigo,
                descricao: produto.produto.descricao,
                qtde: produto.quantidade,
                vlr_unit: produto.produto.preco,
            };
            itens.push(item);
        });
        console.log(itens);

        return { nome, cnpj, itens, vendedor, dataPrevista, observacoes, observacaointerna };
    };

    gerarXmlItensParaEnvio = () => {
        const { nome, cnpj, itens, vendedor, dataPrevista, observacoes, observacaointerna } = this.finalizaVenda();

        if (itens.length === 0) {
            this.ModalFinalizarVendaSemItem();
            return;
        }

        const xml = `<?xml version="1.0"?>
          <pedido>
            <data_prevista>${dataPrevista}</data_prevista>
            <obs>${observacoes}</obs>
            <obs_internas>${observacaointerna}</obs_internas>
            <vendedor>${vendedor}</vendedor>
            <cliente>
              <nome>${nome}</nome>
              <cnpj>${cnpj}</cnpj>
            </cliente>
            <itens>
              ${itens.map((item) => `
                <item>
                  <codigo>${item.codigo}</codigo>
                  <descricao>${item.descricao}</descricao>
                  <qtde>${item.qtde}</qtde>
                  <vlr_unit>${item.vlr_unit}</vlr_unit>
                </item>
              `).join('')}
            </itens>
          </pedido>`;

        console.log(xml);

        const xmlContato = ('xml', xml);
        this.cadastrarPedido(xmlContato);
        this.excluirProdutoSelecionado();

        return xml;
    };







    render() {


        const { produtos, produtoSelecionado, produtosSelecionados, buscaProduto, carregandoProduto, preco, valorTotal, quantidade, desconto, comentario } = this.state;
        const { contatos, contatoSelecionado, buscaContato, carregandoContato, cnpj, nome, tipo, codigo, fantasia, buscaVendedor, valorDesconto, totalComDesconto, total, index, troco, dinheiro, dataPrevista, vendedorSelecionado, observacoes, observacaointerna } = this.state;

        return (

            <Container fluid className="pb-5" >
                <Row className="d-flex">
                    <Col md={6} className="bg-light">
                        <Form>
                            <div className="grid-1">
                                <div className="produto-header">Vendedor</div>
                                <div className="col">
                                    <Form.Group className="mb-3">
                                        <Form.Label htmlFor="vendedor" className="texto-campos">Vendedor</Form.Label>
                                        <Form.Select className="campos-pagamento" id="vendedor" name="vendedor" value={this.state.vendedorSelecionado} onChange={this.atualizaVendedorSelecionado} >
                                            <option value="">Selecione um vendedor</option>
                                            {this.state.vendedores.map((contato) => (
                                                <option key={contato.contato.id} value={contato.contato.nome}>
                                                    {contato.contato.nome}
                                                </option>
                                            ))}
                                        </Form.Select>
                                    </Form.Group>
                                </div>
                                <div className="produto-header">Cliente</div>
                                <div>
                                    <div className="busca-cliente d-grid gap-2">
                                        <Form.Label htmlFor="produto" className="texto-campos">Nome</Form.Label>
                                        <Form.Control type="text" id="cliente" className="form-control" placeholder="Digite a nome do cliente" value={buscaContato} onChange={this.atualizarBuscaContato} />
                                        <Button variant="secondary" onClick={() => this.buscarContato(buscaContato, nome, cnpj)}>Buscar</Button>
                                    </div>
                                    <ul className="lista-contatos">
                                        {contatos.map((contato) => (
                                            <li key={contato.contato.id}
                                                onClick={() => this.selecionarContato(contato)}
                                                onKeyDown={(e) => {
                                                    if (e.key === 'Enter' || e.key === " ") {
                                                        e.preventDefault();
                                                        this.selecionarContato(contato);
                                                    }
                                                }}
                                                tabIndex={0}
                                            >
                                                Nome: {contato.contato.nome} - CPF/CNPJ: {contato.contato.cnpj}
                                            </li>
                                        ))}
                                    </ul>
                                    {contatoSelecionado && (
                                        <div className="produto-selecionado">
                                            <h2>Cliente selecionado: {contatoSelecionado.contato.nome}</h2>
                                        </div>
                                    )}
                                </div>

                                <div className="row">
                                    <div className="col">
                                        <Form.Group className="mb-3">
                                            <Form.Label htmlFor="nome" className="texto-campos">Nome</Form.Label>
                                            <Form.Control type="text" id="nome" className="form-control" name="nome" value={nome || ''} onChange={this.atualizaNome} />
                                        </Form.Group>
                                    </div>
                                    <div className="col">
                                        <Form.Group className="mb-3">
                                            <Form.Label htmlFor="tipo" className="texto-campos">Tipo</Form.Label>
                                            <Form.Select as="select" id="tipo" className="form-control" name="tipo" value={tipo || ''} onChange={this.atualizaTipo}>
                                                <option value="J">Pessoa Jurídica</option>
                                                <option value="F">Pessoa Física</option>
                                                <option value="E">Estrangeiro</option>
                                            </Form.Select>
                                        </Form.Group>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <Form.Group className="mb-3">
                                            <Form.Label htmlFor="cpf" className="texto-campos">CPF</Form.Label>
                                            <Form.Control type="text" id="cpf" className="form-control" name="cpf" value={cnpj || ''} onChange={this.atualizaCpfCnpj} />
                                        </Form.Group>
                                    </div>
                                    <div className="col">
                                        <Form.Group className="mb-3">
                                            <Form.Label htmlFor="codigo" className="texto-campos">Código</Form.Label>
                                            <Form.Control type="text" id="codigo" className="form-control" name="codigo" value={codigo || ''} onChange={this.atualizaCodigo} />
                                        </Form.Group>
                                    </div>
                                    <div className="col">
                                        <Form.Group className="mb-3">
                                            <Form.Label htmlFor="fantasia" className="texto-campos">Fantasia</Form.Label>
                                            <Form.Control type="text" id="fantasia" className="form-control" name="fantasia" value={fantasia || ''} onChange={this.atualizaFantasia} />
                                        </Form.Group>
                                    </div>
                                </div>

                                <div className="divisa"></div>

                                <div>
                                    <div className="cliente-header">Produto</div>
                                    <div>
                                        <div className="d-grid gap-2">
                                            <Form.Label htmlFor="produto" className="texto-campos">Produto</Form.Label>
                                            <Form.Control type="text" id="produto" className="form-control" placeholder="Digite a descrição do produto" value={buscaProduto} onChange={this.atualizarBuscaProduto} />
                                            <Button variant="secondary" onClick={() => this.buscarProdutos(buscaProduto)}>Buscar</Button>
                                        </div>
                                        <ul className="lista-produtos">
                                            {produtos.map((produto) => (
                                                <li
                                                    key={produto.produto.id}
                                                    onClick={() => this.selecionarProduto(produto)}
                                                    onKeyDown={(e) => {
                                                        if (e.key === 'Enter' || e.key === " ") {
                                                            e.preventDefault();
                                                            this.selecionarProduto(produto);
                                                        }
                                                    }}
                                                    tabIndex={0}
                                                >
                                                    Nome: {produto.produto.descricao} - R${produto.produto.preco}
                                                </li>
                                            ))}
                                        </ul>
                                        {produtoSelecionado && (
                                            <div className="produto-selecionado">
                                                <h2>Produto selecionado: {produtoSelecionado.produto.descricao}</h2>
                                            </div>
                                        )}
                                    </div>
                                    <div className="row">
                                        <div className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="quantidade" className="texto-campos">Quantidade</Form.Label>
                                                <Form.Control type="text" id="quantidade" className="form-control" name="quantidade" value={quantidade || ''} onChange={this.atualizaQuantidade} disabled={!produtoSelecionado} />
                                            </Form.Group>
                                        </div>
                                        <div className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="desconto" className="texto-campos">Desconto (%)</Form.Label>
                                                <Form.Control type="text" id="desconto" className="form-control" name="desconto" placeholder="00,00" value={desconto || ''} onChange={this.atualizarDesconto} disabled />
                                            </Form.Group>
                                        </div>
                                        <div className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="preco" className="texto-campos">Valor unitário</Form.Label>
                                                <Form.Control type="text" id="preco" className="form-control" name="preco" placeholder="00,00" value={preco || ''} onChange={this.atualizaPreco} disabled={!produtoSelecionado} />
                                            </Form.Group>
                                        </div>
                                        <div className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="valorTotal" className="texto-campos">Sub total</Form.Label>
                                                <Form.Control type="number" id="valorTotal" className="form-control" name="valorTotal" placeholder="00,00" value={this.state.valorTotal || ''} onChange={this.atualizarValorTotal} readOnly />
                                            </Form.Group>
                                        </div>
                                    </div>
                                    <div>
                                        <Form.Label htmlFor="valorTotal" className="texto-campos">Comentário</Form.Label>
                                    </div>

                                    <div className="container">
                                        <Form.Control as="textarea" id="observacao" value={this.state.comentario || ''} onChange={this.atualizarComentario} disabled={!produtoSelecionado} style={{ height: '75px', width: '600px' }} />
                                        <Button variant="success" onClick={() => this.adicionarProdutoSelecionado(produtoSelecionado)}>Inserir produto </Button>
                                    </div>
                                    {carregandoProduto &&
                                        <div>Carregando produto na lista...</div>
                                    }

                                    <div className="divisa"></div>
                                    <div className="pagamento-header">Pagamento</div>
                                    <div>
                                        <h5>Totais</h5>
                                        <div>
                                            <div className="row">
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="subtotal" className="texto-campos">Sub total</Form.Label>
                                                        <Form.Control type="text" id="subtotal" className="campos-pagamento" name="subtotal" placeholder="00,00" defaultValue={this.state.subTotal || ''} disabled />
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="desconto" className="texto-campos">Desconto</Form.Label>
                                                        <Form.Control type="text" id="desconto" className="campos-pagamento" name="desconto" placeholder="00,00" value={valorDesconto || ''} onChange={this.atualizaDesconto} />
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="totaldavenda" className="texto-campos">Total da venda</Form.Label>
                                                        <Form.Control type="text" id="totaldavenda" className="campos-pagamento" name="totaldavenda" placeholder="00,00" defaultValue={totalComDesconto || ''} disabled />
                                                    </Form.Group>
                                                </div>
                                            </div>
                                            <div className="row">
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="totaldinheiro" className="texto-campos">Total recebido em dinheiro</Form.Label>
                                                        <Form.Control type="text" id="totaldinheiro" className="campos-pagamento" name="totaldinheiro" placeholder="00,00" value={dinheiro || ''} onChange={this.atualizaTroco} />
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="trocodinheiro" className="texto-campos">Troco em dinheiro</Form.Label>
                                                        <Form.Control type="text" id="trocodinheiro" className="campos-pagamento" name="trocodinheiro" placeholder="00,00" defaultValue={troco || ''} disabled />
                                                    </Form.Group>
                                                </div>
                                            </div>
                                            <div>
                                                <h5>Forma de pagamento</h5>
                                            </div>
                                            <div className="row">
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="condicao" className="texto-campos">Condição</Form.Label>
                                                        <Form.Control className="campos-pagamento" id="condicao" rows="3" />
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="gerarparcelas" className="texto-campos">.</Form.Label>
                                                        <Button variant="success" className="campos-pagamento" onClick={() => this.adicionarProdutoSelecionado(produtoSelecionado)}>Gerar parcelas</Button>
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    {/* <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="vendedor" className="texto-campos">Categoria</Form.Label>
                                                        <Form.Select className="campos-pagamento" id="vendedor" name="vendedor" value={this.state.vendedorSelecionado} onChange={this.atualizaVendedorSelecionado} >
                                                            <option value="">Selecione um vendedor</option>
                                                            {this.state.vendedor.map((contato) => (
                                                                <option key={contato.contato.id} value={contato.contato.id}>
                                                                    {contato.contato.nome}
                                                                </option>
                                                            ))}
                                                        </Form.Select>
                                                    </Form.Group> */}
                                                </div>
                                            </div>
                                            <div>
                                                <h5>Outras informações</h5>
                                            </div>
                                            <div className="row">
                                                {/* <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="vendedor" className="texto-campos">Vendedor</Form.Label>
                                                        <Form.Select className="campos-pagamento" id="vendedor" name="vendedor" value={this.state.vendedorSelecionado} onChange={this.atualizaVendedorSelecionado} >
                                                            <option value="">Selecione um vendedor</option>
                                                            {this.state.vendedor.map((contato) => (
                                                                <option key={contato.contato.id} value={contato.contato.id}>
                                                                    {contato.contato.nome}
                                                                </option>
                                                            ))}
                                                        </Form.Select>
                                                    </Form.Group>
                                                </div> */}
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="dataprevista" className="texto-campos">Data prevista</Form.Label>
                                                        <DatePicker
                                                            locale={ptBR}
                                                            id="dataprevista"
                                                            name="dataprevista"
                                                            selected={this.state.dataPrevista ? new Date(this.state.dataPrevista) : null}
                                                            onChange={this.atualizaDataPrevista}
                                                            placeholderText="Selecione uma data"
                                                            className="form-select"
                                                            dateFormat="dd/MM/yyyy" // adicionado aqui
                                                        />

                                                    </Form.Group>
                                                </div>
                                            </div>
                                            <div className="row">
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="depositolancamento" className="texto-campos">Depósito para lançamento</Form.Label>
                                                        <Form.Select className="campos-informacoes" id="depositolancamento" name="depositolancamento" value={this.state.depositoSelecionado} onChange={this.atualizaDepositoSelecionado} >
                                                            {this.state.depositos.map((deposito) => (
                                                                <option key={deposito.deposito.id} value={deposito.deposito.id}>
                                                                    {deposito.deposito.descricao}
                                                                </option>
                                                            ))}
                                                        </Form.Select>
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="frete" className="texto-campos">Frete</Form.Label>
                                                        <Form.Control type="text" className="campos-informacoes" id="frete" name="frete" placeholder="00,00" value={dinheiro || ''} onChange={this.atualizaTroco} />
                                                    </Form.Group>
                                                </div>
                                            </div>
                                            <div className="col">
                                                <div >
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="observacoes" className="texto-campos">Observações</Form.Label>
                                                        <textarea className="form-control" id="observacoes" rows="3" value={observacoes || ''} onChange={this.atualizaObservacoes} ></textarea>
                                                    </Form.Group>
                                                </div>
                                                <div className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="observacaointerna" className="texto-campos">Observações internas </Form.Label>
                                                        <textarea className="form-control" id="observacaointerna" rows="3" value={observacaointerna || ''} onChange={this.atualizaObservacaoInterna} ></textarea>
                                                    </Form.Group>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Form>
                    </Col>
                    <Col md={6} className="">
                        <div className="grid-2">
                            <Table responsive="lg" striped>
                                <thead>
                                    <tr>
                                        <th>Produto</th>
                                        <th>Quantidade</th>
                                        <th>Preço</th>
                                        <th>Subtotal</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.state.produtosSelecionados.map((produto, index) => (
                                        <tr key={produto.produto.id}>
                                            <td>{produto.produto.codigo} - {produto.produto.descricao}</td>
                                            <td>{produto.quantidade}</td>
                                            <td>R$ {typeof produto.produto.preco === "number"
                                                ? produto.produto.preco.toFixed(2).replace(".", ",")
                                                : parseFloat(produto.produto.preco.replace(",", ".")).toFixed(2).replace(".", ",")}</td>
                                            <td>R$ {this.calcularSubTotal(produto.produto, produto.quantidade).toFixed(2).replace(".", ",")}</td>
                                            <td><button className="transparent-button" onClick={() => {
                                                if (window.confirm('Deseja excluir o item?\nEssa ação não poderá ser desfeita.')) {
                                                    this.excluirProdutoSelecionado(index);
                                                }
                                            }}>
                                                <IonIcon icon={trashOutline} className="red-icon" size="medium" />
                                            </button>
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </Table>
                        </div>
                    </Col>
                </Row>
                <Row className="fixed-bottom">
                    <Col>
                        <div className="rodape">
                            <div>
                                <div className="botao-excluirvenda">
                                    <div>
                                        <Button variant="success" onClick={this.ModalExcluirPedido}>Excluir pedido</Button>
                                    </div>
                                </div>
                                <div className="botao-finalizarvenda">
                                    <Button variant="success" onClick={this.gerarXmlItensParaEnvio}>Finalizar Venda</Button>
                                </div>
                            </div>
                            <div className="div_total_venda">
                                <div>
                                    <span className="span-total">Total:</span>
                                    <span className="span-valor">R$ {this.state.subTotal}</span>
                                </div>
                            </div>
                        </div>
                    </Col>
                </Row>
                <Modal show={this.state.ModalFinalizarVendaSemItem} onHide={this.ModalFinalizarVendaSemItem} centered>
                    <Modal.Header closeButton>
                        <Modal.Title>Atenção </Modal.Title>
                        <FontAwesomeIcon icon={faExclamationTriangle} size="2x" className="text-warning mr-2mr-3" />
                    </Modal.Header>
                    <Modal.Body>
                        Este pedido não possui itens ou cliente, insira-os para realizar essa operação.
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="success" onClick={this.ModalFinalizarVendaSemItem}>Fechar</Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.ModalExcluirPedido} onHide={this.ModalExcluirPedido} centered>
                    <Modal.Header closeButton>
                        <Modal.Title>Atenção </Modal.Title>
                        <FontAwesomeIcon icon={faExclamationTriangle} size="2x" className="text-warning mr-2mr-3" />
                    </Modal.Header>
                    <Modal.Body>
                        Deseja excluir o pedido? Essa ação não poderá ser desfeita.
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="outline-success" onClick={this.ModalExcluirPedido}>Não</Button>
                        <Button variant="success" onClick={this.excluirPedido}>Sim</Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.modalInserirProduto} onHide={this.modalInserirProduto} centered>
                    <Modal.Header closeButton>
                        <Modal.Title>Atenção </Modal.Title>
                        <FontAwesomeIcon icon={faExclamationTriangle} size="2x" className="text-warning mr-2mr-3" />
                    </Modal.Header>
                    <Modal.Body>
                        Nenhum produto selecionado!
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.modalInserirProduto}>Fechar</Button>
                    </Modal.Footer>
                </Modal>
            </Container >
        );
    }
}

export default FrenteCaixa;

