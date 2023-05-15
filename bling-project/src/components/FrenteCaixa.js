import React from "react";
import '../css/FrenteCaixa.css';

import { IonIcon } from '@ionic/react';
import { trashOutline } from 'ionicons/icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faExclamationTriangle, faCalendarAlt } from '@fortawesome/free-solid-svg-icons';

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
import { Offcanvas } from 'react-bootstrap';
import FloatingLabel from 'react-bootstrap/FloatingLabel';

import { parse } from 'js2xmlparser';
import Autosuggest from 'react-autosuggest';


class FrenteCaixa extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            buscaProduto: '',
            buscaContato: '',
            buscaVendedor: '',
            nome: '',
            cnpj: '',
            observacoes: '',
            observacaointerna: '',
            comentario: '',
            produtos: [],
            contatos: [],
            depositos: [],
            vendedores: [],
            pedidos: [],
            produtosSelecionados: [],
            contatosSelecionados: [],
            vendedoresSelecionados: [],
            vendedoresFiltrados: [],
            itensSelecionados: [],
            produtoSelecionado: null,
            contatoSelecionado: null,
            vendedorSelecionado: null,
            carregandoProduto: false,
            carregandoContato: false,
            carregandoVendedor: false,
            preco: 0,
            precoProdutoSelecionado: 0,
            quantidade: 1,
            valorTotal: 0,
            valorDesconto: 0,
            totalComDesconto: 0,
            subTotalComDesconto: 0,
            desconto: 0,
            total: 0,
            subTotal: 0,
            dinheiroRecebido: 0,
            troco: 0,
            dinheiro: 0,
            dataPrevista: '',
            dataPrevistaFormatted: '',
            depositoSelecionado: null,
            ModalFinalizarVendaSemItem: false,
            ModalExcluirPedido: false,
            modalInserirProduto: false,
            modalSalvarPedido: false,
            modalInserirParcela: false,
            canvasFinalizarPedido: false,
            subtotalComFrete: 0,
            frete: 0,
            freteInserido: false,
            parcelas: [{ dias: 0 }],
            condicao: 0,
            forma: [],
            subtotalGeral: 0,
            carregando: false,
            numLinhas: 0,
            newParcelas: 0,
            numeroPedido: 0,
            ultimoPedido: 0,
            endereco: '',
            numero: '',
            bairro: '',
            cidade: '',
            uf: '',
        };

        this.atualizaDesconto = this.atualizaDesconto.bind(this);
        this.atualizaTotalComFrete = this.atualizaTotalComFrete.bind(this);
        this.adicionarParcela = this.adicionarParcela.bind(this);
        this.handleChange = this.handleChange.bind(this);
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

    modalInserirParcela = () => {
        this.setState({ modalInserirParcela: !this.state.modalInserirParcela });
    }

    modalSalvarPedido = () => {
        this.setState({ modalSalvarPedido: !this.state.modalSalvarPedido }, () => {
            setTimeout(() => {
                this.setState({ modalSalvarPedido: false });
            }, 3000);
        });
    }

    canvasFinalizarPedido = () => {
        this.setState({ canvasFinalizarPedido: !this.state.canvasFinalizarPedido });
    }

    componentDidMount() {
        this.buscarDeposito();
        this.buscarVendedor();
        this.buscarPedido()
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.nome !== this.state.nome) {
            this.atualizaNome({ target: { value: this.state.nome } });
        }
        if (prevState.cnpj !== this.state.cnpj) {
            this.atualizaCpfCnpj({ target: { value: this.state.cnpj } });
        }
        if (prevState.produtosSelecionados !== this.state.produtosSelecionados ||
            prevState.subTotal.toFixed(2) !== this.state.subTotal.toFixed(2) ||
            prevState.valorDesconto !== this.state.valorDesconto ||
            prevState.subTotalComFrete !== this.state.subTotalComFrete) {
            const subtotalGeral = this.calcularSubTotalGeral().toFixed(2);
            // console.log("Subtotal Geral:", subtotalGeral);
            this.setState({ subTotalGeral: subtotalGeral });
        }
    }

    //----------------------------------------- CHAMADAS DE API´S ----------------------------------------------------------


    buscarProdutos = (value) => {
        // console.log("Buscando produto por:", value);
        this.setState({ buscaProduto: value, carregando: false });
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
        // console.log("Buscando contato por:", value);
        this.setState({ buscaContato: value, carregando: false });
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
                            (contato.contato.rg && contato.contato.rg.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.endereco && contato.contato.endereco.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.numero && contato.contato.numero.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.bairro && contato.contato.bairro.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.cidade && contato.contato.cidade.toLowerCase().includes(value.toLowerCase())) ||
                            (contato.contato.uf && contato.contato.uf.toLowerCase().includes(value.toLowerCase()))
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
        // console.log("Buscando vendedor por:", value);
        this.setState({ buscaVendedor: value, carregando: false });
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

    buscarPedido = (value) => {
        this.setState({ buscaPedido: value, carregando: true });
        fetch("http://localhost:8085/api/v1/pedidos")
            .then(resposta => resposta.json())
            .then(dados => {
                if (dados.retorno.pedidos) {
                    console.log("pedidos retornados:", dados);
                    const ultimoPedido = dados.retorno.pedidos[dados.retorno.pedidos.length - 1];
                    console.log("posicao NUMERO:", ultimoPedido.pedido.numero);
                    this.setState({
                        numeroPedido: ultimoPedido.pedido.numero,
                        ultimoPedido: ultimoPedido.pedido.numero // Adiciona o valor de ultimoPedido ao estado
                    });
                    console.log("Numero do ultimo pedido:", ultimoPedido);

                } else {
                    this.setState({ pedidos: [] })
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

    //--------------------------------------------- FUNÇÕES DE TELA ---------------------------------------------
    // -------------------------------------------- FUNÇÕES VENDEDOR --------------------------------------------

    atualizaVendedorSelecionado = (event) => {
        const vendedorSelecionado = event.target.value;
        // console.log("vendedor selecionado:", vendedorSelecionado);
        this.setState({
            vendedorSelecionado: vendedorSelecionado,
            vendedor: vendedorSelecionado
        }, () => {
            // console.log("vendedor:", this.state.vendedor);
        });
    };

    // atualizarBuscaVendedor = (e) => {
    //     this.setState({
    //         buscaVendedor: e.target.value
    //     });
    // };

    // selecionarVendedor = (contato) => {
    //     this.setState({
    //         contatoSelecionado: contato,
    //         descricao: contato.contato[0].contato.tiposContato[0].tipoContato.descricao,
    //         contatos: [],
    //     });
    //     this.atualizarBuscaContato({ target: { value: '' } });
    // };

    // adicionarVendedorSelecionado = (vendedorSelecionado) => {
    //     const { contatosSelecionados, cnpj } = this.state;
    //     const contatoExistente = contatosSelecionados.find((contato) => contato.contato.id === vendedorSelecionado.contato.id);

    //     if (contatoExistente) {
    //         contatoExistente.cnpj += cnpj; // Adiciona a quantidade selecionada
    //     } else {
    //         contatosSelecionados.push({
    //             nome: vendedorSelecionado.nome,
    //             descricao: vendedorSelecionado[0].contato.tiposContato[0].tipoContato.descricao
    //         });
    //     }

    //     this.setState({
    //         contatosSelecionados: contatosSelecionados,
    //         contatoSelecionado: null,
    //         nome: '',
    //         descricao: '',
    //         contatos: [],
    //         buscaContato: '',
    //     });
    // };

    // -------------------------------------------- FUNÇÕES CONTATO ---------------------------------------------

    atualizarBuscaContato = (event) => {
        const buscaContato = event.target.value
        // console.log("atualizarBuscaContato (buscaContato): ", buscaContato)
        this.setState({
            buscaContato
        });
    };

    atualizaNome = (e) => {
        // console.log("Nome:", e.target.value);
        this.setState({
            nome: e.target.value
        });
    };

    atualizaTipo = (e) => {
        // console.log("Tipo:", e.target.value);
        this.setState({
            tipo: e.target.value
        });
    };

    atualizaCpfCnpj = (e) => {
        // console.log("CPF/CNPJ:", e.target.value);
        this.setState({
            cnpj: e.target.value
        });
    };

    atualizaCodigo = (e) => {
        // console.log("Codigo:", e.target.value);
        this.setState({
            codigo: e.target.value
        });
    };

    atualizaFantasia = (e) => {
        // console.log("Fantasia:", e.target.value);
        this.setState({
            fantasia: e.target.value
        });
    };

    // -------------------------------------------- FUNÇÕES CONTATO ---------------------------------------------


    selecionarContato = (contato) => {
        // console.log("selecionarContato (contatoSelecionado)", contatoSelecionado)
        this.setState({
            contatoSelecionado: contato,
            nome: contato.contato.nome,
            tipo: contato.contato.tipo,
            cnpj: contato.contato.cnpj,
            codigo: contato.contato.codigo,
            fantasia: contato.contato.fantasia,
            endereco: contato.contato.endereco,
            numero: contato.contato.numero,
            bairro: contato.contato.bairro,
            cidade: contato.contato.cidade,
            uf: contato.contato.uf,
            contatos: [],
        });
        this.atualizarBuscaContato({ target: { value: '' } });
    };

    // adicionarContatoSelecionado = (contatoSelecionado) => {
    //     const { contatosSelecionados, cnpj } = this.state;
    //     const contatoExistente = contatosSelecionados.find((contato) => contato.contato.id === contatoSelecionado.contato.id);

    //     if (contatoExistente) {
    //         contatoExistente.cnpj += cnpj; // Adiciona a quantidade selecionada
    //     } else {
    //         contatosSelecionados.push({
    //             nome: contatoSelecionado.nome,
    //             cnpj: cnpj, // Salva a quantidade selecionada
    //         });
    //     }
    //     console.log("contatoscontatosSelecionados: ", contatosSelecionados, "contatoExistente: ", contatoExistente)
    //     this.setState({
    //         contatosSelecionados: contatosSelecionados,
    //         contatoSelecionado: null,
    //         cnpj: '',
    //         contatos: [],
    //         buscaContato: '',
    //     });
    // };

    // -------------------------------------------- FUNÇÕES PRODUTO ---------------------------------------------


    atualizarBuscaProduto = (e) => {
        this.setState({
            buscaProduto: e.target.value
        });
    };

    selecionarProduto = (produto) => {
        const precoEmReais = parseFloat(produto.produto.preco).toFixed(2);
        const valorTotal = (parseFloat(produto.produto.preco) * 1).toFixed(2);
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
    };

    excluirProdutoSelecionado = (index) => {
        const produtosSelecionados = [...this.state.produtosSelecionados];
        const produtoExcluido = produtosSelecionados.splice(index, 1)[0];
        const quantidadeExcluida = produtoExcluido.quantidade;
        const produto = produtoExcluido.produto;
        const subTotalAnterior = this.state.subTotal.toFixed(2);
        const subTotal = (parseFloat(subTotalAnterior) - this.calcularSubTotal(produto, quantidadeExcluida)).toFixed(2);
        // console.log("Novo Subtotal:", subTotal);
        this.setState({
            produtosSelecionados,
            subTotal,
        });
    };


    // -------------------------------------------- FUNÇÕES CALCULOS ---------------------------------------------


    calcularTotal() {
        let total = 0;
        this.state.produtosSelecionados.forEach((produto) => {
            total += this.calcularSubTotal(produto.produto, produto.quantidade);
        });
        const subTotal = total;
        // console.log("Subtotal:", subTotal);

        if (this.state.subTotal !== subTotal) {
            this.setState({ subTotal });
        }
        return parseFloat(subTotal.toFixed(2));
    }

    calcularSubTotal = (produto, quantidade) => {
        const preco = produto.preco;
        return preco * quantidade;
    };

    atualizaQuantidade = (e) => {
        const quantidade = Number(e.target.value);
        this.setState({ quantidade }, this.atualizarValorTotal);
    };

    incrementarQuantidade = () => {
        this.setState(prevState => ({
            quantidade: prevState.quantidade + 1
        }), this.atualizarValorTotal);
    };

    decrementarQuantidade = () => {
        this.setState(prevState => ({
            quantidade: prevState.quantidade > 1 ? prevState.quantidade - 1 : 1
        }), this.atualizarValorTotal);
    };

    atualizaPreco = (e) => {
        const preco = parseFloat(e.target.value);
        this.setState({ preco }, this.atualizarValorTotal);
    };

    atualizarValorTotal = () => {
        const { quantidade, preco } = this.state;
        const valorTotal = (quantidade * parseFloat(preco)).toFixed(2);
        this.setState({ valorTotal });
    };

    calcularTotalComDesconto = (desconto, subTotal) => {
        const subtotal = subTotal || this.calcularTotal();
        const valorDesconto = desconto || 0;
        const totalComDesconto = subtotal - valorDesconto;

        let formattedValorDesconto = 0;
        let formattedTotalComDesconto = 0;

        if (typeof valorDesconto === "number") {
            formattedValorDesconto = valorDesconto.toFixed(2);
        }

        if (typeof totalComDesconto === "number") {
            formattedTotalComDesconto = totalComDesconto.toFixed(2);
        }

        // console.log("valorDesconto: ", formattedValorDesconto);
        // console.log("totalComDesconto: ", formattedTotalComDesconto);

        if (isNaN(totalComDesconto)) {
            // console.log("Total com desconto é NaN!");
        }

        return {
            valorDesconto: formattedValorDesconto,
            totalComDesconto: formattedTotalComDesconto
        };
    };

    atualizaDesconto(event) {
        // console.log("Evento de digitação capturado!");

        const descontoString = event.target.value; // Substitui a vírgula decimal por ponto
        const descontoNumber = parseFloat(descontoString.replace(/[^\d.-]/g, '')); // remove caracteres não-numéricos

        if (isNaN(descontoNumber)) {
            // Se não for um número válido, limpa o valor do campo e retorna
            this.setState({
                valorDesconto: 0,
                desconto: false,
                totalComDesconto: '',
            });
            return;
        }

        const resultado = this.calcularTotalComDesconto(descontoNumber);
        // console.log(resultado)
        this.setState({
            valorDesconto: descontoString,
            desconto: descontoNumber,
            totalComDesconto: resultado.totalComDesconto,
        });
    };

    calcularTotalComDinheiro = (dinheiro) => {
        const totalRecebidoEmDinheiro = parseFloat(dinheiro) || 0;
        const subTotalGeral = this.state.subTotalGeral;

        if (isNaN(totalRecebidoEmDinheiro)) {
            // console.log("O valor total recebido em dinheiro não é um número!");
            return {
                dinheiroRecebido: 0,
                troco: 0,
            };
        }

        let troco = totalRecebidoEmDinheiro - subTotalGeral;

        if (troco < 0) {
            troco = 0;
        }

        // console.log("totalRecebidoEmDinheiro:", totalRecebidoEmDinheiro);
        // console.log("troco:", troco);

        return {
            dinheiroRecebido: dinheiro,
            troco: troco.toFixed(2),
        };
    };

    atualizaTroco = (event) => {
        const valorRecebido = event.target.value;

        // Verifica se o valor recebido é vazio ou nulo
        if (!valorRecebido) {
            this.setState({ dinheiroRecebido: 0, troco: 0 });
            return;
        }

        // Verifica se o valor recebido é um número válido antes de chamar a função
        if (!isNaN(parseFloat(valorRecebido)) && parseFloat(valorRecebido) > 0) {
            const { totalComDesconto } = this.state;
            const { dinheiroRecebido, troco } = this.calcularTotalComDinheiro(valorRecebido, totalComDesconto);

            this.setState({ dinheiroRecebido, troco: Math.abs(troco).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }) });
        }
    };


    atualizaTotalComFrete(event) {
        const valor = event.target.value;
        // console.log('valor:', valor);
        let frete = 0;
        if (typeof valor === 'string') {
            frete = parseFloat(valor);
        }
        // console.log('frete:', frete);
        const subTotal = this.state.subTotal.toFixed(2);
        const totalComDesconto = this.state.totalComDesconto;
        // console.log('subTotal:', subTotal);
        // console.log('totalComDesconto:', totalComDesconto);
        let subTotalComFrete;
        if (totalComDesconto && totalComDesconto.length > 0) {
            subTotalComFrete = (parseFloat(totalComDesconto) + frete).toFixed(2);
        } else {
            subTotalComFrete = (parseFloat(subTotal) + frete).toFixed(2);
        }
        // console.log('subTotalComFrete:', subTotalComFrete);
        this.setState({
            subTotalComFrete: subTotalComFrete,
            frete: frete,
            freteInserido: true
        });
    };

    calcularSubTotalGeral = () => {
        const subTotal = this.calcularTotal().toFixed(2);
        const desconto = this.state.valorDesconto;
        const totalComDesconto = this.calcularTotalComDesconto(desconto, subTotal).totalComDesconto;
        const frete = this.state.frete;
        const subTotalComFrete = parseFloat(totalComDesconto) + parseFloat(frete);
        const subTotalGeral = subTotalComFrete.toFixed(2);

        // console.log("Subtotal Geral:", subTotalGeral);

        return parseFloat(subTotalGeral);
    };

    atualizaDataPrevista = (novaDataPrevista) => {
        // console.log(novaDataPrevista);
        const dataPrevista = novaDataPrevista.toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });

        this.setState({
            dataPrevista: novaDataPrevista
        });
        // console.log(dataPrevista);
    };

    atualizaDepositoSelecionado = (event) => {
        const depositoSelecionado = event.target.value;
        // console.log(depositoSelecionado); //Esta retornando ID
        this.setState({ depositoSelecionado });
    };

    atualizaObservacoes = (event) => {
        const observacoes = event.target.value;
        // console.log(event.target.value);
        this.setState({ observacoes });
    };

    atualizaObservacaoInterna = (event) => {
        const observacaointerna = event.target.value;
        // console.log(event.target.value);
        this.setState({ observacaointerna });
    };

    atualizarComentario = (event) => {
        const comentario = event.target.value;
        // console.log("Comentario", event.target.value);
        this.setState({ comentario });
    };


    // handleChangeDesconto = (event) => {
    //     const { value } = event.target;
    //     const desconto = parseFloat(value);

    //     if (isNaN(desconto)) {
    //         this.setState({ desconto: "" });
    //         return;
    //     }

    //     const { subTotal } = this.state;
    //     const totalComDesconto = (subTotal - desconto).toFixed(2);

    //     this.setState({ desconto, totalComDesconto });
    // };


    // calcularValorTotalInicial = () => {
    //     const { preco } = this.state;
    //     const quantidade = 1;
    //     const valorTotal = quantidade * parseFloat(preco);
    //     this.setState({ quantidade, valorTotal });
    // };

    // calcularTotalComDesconto = (desconto, subTotal) => {
    //     const subtotal = subTotal || this.calcularTotal();
    //     const valorDesconto = desconto || 0;
    //     const totalComDesconto = subtotal - valorDesconto;

    //     const formattedValorDesconto = valorDesconto.toFixed(2);
    //     const formattedTotalComDesconto = totalComDesconto.toFixed(2);

    //     console.log("valorDesconto: ", formattedValorDesconto);
    //     console.log("totalComDesconto: ", formattedTotalComDesconto);


    //     if (isNaN(totalComDesconto)) {
    //         console.log("Total com desconto é NaN!");
    //     }

    //     return {
    //         valorDesconto: formattedValorDesconto,
    //         totalComDesconto: formattedTotalComDesconto
    //     };
    // }



    // -------------------------------------------- FUNÇÕES BOTÕES ---------------------------------------------


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
            comentario: '',
            contatoSelecionado: '',
            vendedorSelecionado: '',
            dinheiroRecebido: '',
            condicao: '',
            parcelas: []
        });
        this.ModalExcluirPedido()
        this.modalSalvarPedido()
    };

    limparPedido = () => {
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
            comentario: '',
            vendedor: '',
            contatoSelecionado: '',
            vendedorSelecionado: '',
            dinheiroRecebido: '',
            condicao: '',
            parcelas: []
        });
    };

    finalizaVenda = () => {
        const nome = this.state.nome;
        const cnpj = this.state.cnpj;
        const vendedor = this.state.vendedor;
        const dataPrevista = new Date(this.state.dataPrevista); // converte para objeto Date
        const dataPrevistaFormatted = `${dataPrevista.toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' })} ${dataPrevista.toLocaleTimeString('pt-BR')}`;
        // console.log(this.state.dataPrevista)
        // console.log(dataPrevistaFormatted)
        const observacoes = this.state.observacoes;
        const observacaointerna = this.state.observacaointerna;
        const valorDesconto = this.state.desconto;
        const itens = [];
        const prazo = [];


        this.state.produtosSelecionados.forEach((produto) => {
            const item = {
                codigo: produto.produto.codigo,
                descricao: produto.produto.descricao,
                qtde: produto.quantidade,
                vlr_unit: produto.produto.preco,
            };
            itens.push(item);
        });

        console.log(prazo);

        this.state.parcelas.forEach((parcela) => {
            const parcelas = {
                data: parcela.data,
                valor: parcela.valor,
                observacao: parcela.observacao,
            };
            prazo.push(parcelas);
        });


        if (itens.length === 0) {
            this.ModalFinalizarVendaSemItem();
            return;
        } else {
            this.canvasFinalizarPedido()
        }

        return { nome, cnpj, itens, vendedor, dataPrevista, dataPrevistaFormatted, observacoes, observacaointerna, valorDesconto, prazo };
    };

    gerarXmlItensParaEnvio = () => {
        const { itens, prazo, dataPrevistaFormatted } = this.finalizaVenda();

        const xml = `<?xml version="1.0"?>
          <pedido>
          <vlr_desconto>${this.state.valorDesconto}</vlr_desconto>
            <data_prevista>${dataPrevistaFormatted}</data_prevista>
            <obs>${this.state.observacoes}</obs>
            <obs_internas>${this.state.observacaointerna}</obs_internas>
            <vendedor>${this.state.vendedor}</vendedor>
            <vlr_frete>${this.state.frete}</vlr_frete>
            <cliente>
              <nome>${this.state.nome}</nome>
              <cnpj>${this.state.cnpj}</cnpj>
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
            <parcelas>
            ${prazo.map((parcelas) => `
                <parcela>
                    <data>${parcelas.data}</data>
                    <vlr>${parcelas.valor}</vlr>
                    <obs>${parcelas.observacao}</obs>
               </parcela>
                `).join('')}
            </parcelas>
          </pedido > `;

        console.log(xml);

        const xmlContato = ('xml', xml);

        this.cadastrarPedido(xmlContato);
        this.limparPedido();
        this.modalSalvarPedido();
        this.canvasFinalizarPedido();

        return xml;
    };


    // -------------------------------------------- FUNÇÕES PARCELAS ---------------------------------------------

    handleChange(event) {
        this.setState({ condicao: event.target.value });
    };

    handleChangeParcela(index, campo, valor) {
        const parcelas = [...this.state.parcelas];
        parcelas[index][campo] = valor;
        this.setState({ parcelas });
    };

    adicionarParcela() {
        const { condicao } = this.state;
        const dias = parseInt(condicao);
        const hoje = new Date();
        const data = new Date(hoje.getTime() + dias * 24 * 60 * 60 * 1000);
        const dataFormatada = data.toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });
        const novaParcela = {
            dias,
            data: dataFormatada,
            valor: '',
            forma: '',
            observacao: '',
            acao: '',
        };

        let parcelas = [...this.state.parcelas];
        const index = parcelas.findIndex(parcela => parcela.dias === 0);
        if (index !== -1) {
            parcelas.splice(index, 1);
        }
        parcelas = [...parcelas, novaParcela];

        // Divide o valor total entre as parcelas
        const numParcelas = parcelas.length;
        const valorParcela = (this.state.subTotalGeral / numParcelas).toFixed(2);

        // Atualiza o valor de cada parcela
        const newParcelas = parcelas.map((parcela, i) => ({
            ...parcela,
            valor: valorParcela,
        }));

        this.setState({ parcelas, numLinhas: this.state.numLinhas + 1, parcelas: newParcelas });
    };

    handleValorChangeParcela(index, campo, valor) {
        const parcelas = [...this.state.parcelas];
        const numParcelas = parcelas.length;

        // Atualiza o valor da parcela selecionada
        parcelas[index][campo] = valor;

        // Calcula a soma total dos valores das parcelas
        const valorTotalParcelas = parcelas.reduce((acc, curr) => acc + parseFloat(curr.valor || 0), 0);

        // Calcula o valor médio das parcelas após a atualização
        const valorMedioParcelas = valorTotalParcelas / numParcelas;

        // Calcula a diferença entre o valor médio das parcelas e o valor de cada parcela
        const diferenca = valorMedioParcelas - valor;

        // Atualiza os valores das outras parcelas com a diferença calculada
        const newParcelas = parcelas.map((parcela, i) => {
            if (i === index) {
                return { ...parcela, [campo]: valor };
            } else {
                const novoValor = (parseFloat(parcela.valor) || 0) + diferenca;
                return { ...parcela, valor: novoValor.toFixed(2) };
            }
        });

        this.setState({ parcelas: newParcelas });
    };

    handleFormaChange = (index, event) => {
        const parcelas = [...this.state.parcelas];
        parcelas[index].forma = event.target.value;
        this.setState({ parcelas });
    };

    handleObservacaoChange = (index, event) => {
        const parcelas = [...this.state.parcelas];
        parcelas[index].observacao = event.target.value;
        this.setState({ parcelas });
    };

    handleDeleteParcela(index) {
        const parcelas = [...this.state.parcelas];
        const valorExcluido = parseFloat(parcelas[index].valor || 0);
        parcelas.splice(index, 1);

        const numParcelas = parcelas.length;
        const valorParcela = this.state.subTotalGeral / numParcelas;
        const valorRestante = this.state.subTotalGeral - valorExcluido;

        const diferenca = valorRestante - parcelas.reduce((acc, cur) => acc + parseFloat(cur.valor || 0), 0);
        const valorRestanteParcela = diferenca / numParcelas;

        const newParcelas = parcelas.map((parcela, i) => {
            return {
                ...parcela,
                valor: (valorParcela + valorRestanteParcela).toFixed(2)
            };
        });

        let parcelasRestantes = [];
        if (numParcelas < this.state.numParcelas) {
            const numParcelasRestantes = this.state.numParcelas - numParcelas;
            parcelasRestantes = new Array(numParcelasRestantes).fill().map((_, i) => {
                return {
                    numeroParcela: numParcelas + i + 1,
                    valor: valorRestanteParcela.toFixed(2)
                };
            });
        }

        this.setState({
            parcelas: [...newParcelas, ...parcelasRestantes],
            valorTotalParcela: valorRestanteParcela.toFixed(2),
        });
    };

    calcularData = (dias) => {
        const dataAtual = new Date();
        const dataCalculada = new Date(dataAtual.getTime() + dias * 24 * 60 * 60 * 1000);
        const dia = dataCalculada.getDate().toString().padStart(2, '0');
        const mes = (dataCalculada.getMonth() + 1).toString().padStart(2, '0');
        const ano = dataCalculada.getFullYear();
        return `${dia}/${mes}/${ano}`;
    };

    // handleChangeParcela(index, campo, valor) {
    //     const parcelas = [...this.state.parcelas];
    //     parcelas[index][campo] = valor;
    //     this.setState({ parcelas });
    // }

    // adicionarParcela() {
    //     const { condicao } = this.state;
    //     const dias = parseInt(condicao);
    //     const hoje = new Date();
    //     const data = new Date(hoje.getTime() + dias * 24 * 60 * 60 * 1000);
    //     const dataFormatada = data.toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });

    //     const novaParcela = {
    //         dias,
    //         data: dataFormatada,
    //         valor: '',
    //         forma: '',
    //         observacao: '',
    //         acao: '',
    //     };

    //     console.log(dataFormatada)

    //     let parcelas = [...this.state.parcelas];
    //     const index = parcelas.findIndex(parcela => parcela.dias === 0);
    //     if (index !== -1) {
    //         parcelas.splice(index, 1);
    //     }
    //     parcelas = [...parcelas, novaParcela];

    //     this.setState({ parcelas });
    // }

    // --------------------------------------- FUNÇÕES ACRESCENTA .00 CAMPOS ----------------------------------------


    formatarDesconto = (e) => {
        let valor = e.target.value.trim();
        let desconto = valor !== '' && !isNaN(valor) ? parseFloat(valor).toFixed(2) : 0;
        this.setState({ valorDesconto: desconto });
    };

    formatarFrete = (e) => {
        const valorFrete = e.target.value.trim();
        const frete = valorFrete !== '' && !isNaN(valorFrete) ? parseFloat(valorFrete).toFixed(2) : 0;
        this.setState({ frete: frete });
    };

    formatarTroco = (e) => {
        const dinheiroRecebido = e.target.value.trim();
        const dinheiro = dinheiroRecebido !== '' && !isNaN(dinheiroRecebido) ? parseFloat(dinheiroRecebido).toFixed(2) : 0;
        // console.log(dinheiroRecebido, dinheiro)
        this.setState({ dinheiroRecebido: dinheiro });
    };




    render() {


        const { produtos, produtoSelecionado, produtosSelecionados, buscaProduto, carregandoProduto, preco, valorTotal, quantidade, desconto, comentario, subTotal, subTotalComDesconto } = this.state;
        const { contatos, contatoSelecionado, buscaContato, carregandoContato, cnpj, nome, tipo, codigo, fantasia, endereco, numero, bairro, cidade, uf, buscaVendedor, total, index, dinheiro, dataPrevista, vendedorSelecionado, observacoes, observacaointerna, valorDesconto, totalComDesconto, dinheiroRecebido, troco, subTotalComFrete, frete } = this.state;
        const { subTotalGeral, condicao } = this.state;


        let quantidadeTotal = 0;
        for (const produto of this.state.produtosSelecionados) {
            quantidadeTotal += produto.quantidade;
        }
        if (this.state.carregando) {
            return (
                <div className="spinner-container">
                    <div className="d-flex align-items-center justify-content-center">
                        <Spinner variant="secondary" animation="border" role="status">
                            <span className="visually-hidden">Carregando Frente de caixa...</span>
                        </Spinner>
                    </div>
                    <div>
                        <p>Carregando Frente de caixa...</p>
                    </div>
                </div>
            )
        } else {

            return (

                <Container fluid className="pb-5" >
                    <Row className="d-flex">
                        <Col md={6} className="">
                            <Form>
                                <div className="grid-1">
                                    <div className="produto-header">Vendedor</div>
                                    <Col className="col">
                                        <Form.Group className="mb-3">
                                            <Form.Label htmlFor="vendedor" className="texto-campos">Vendedor</Form.Label>
                                            <Form.Select className="campos-vendedor" id="vendedor" name="vendedor" value={this.state.vendedorSelecionado || ''} onChange={this.atualizaVendedorSelecionado} >
                                                <option>Selecione um vendedor</option>
                                                {this.state.vendedores.map((contato) => (
                                                    <option key={contato.contato.id} value={contato.contato.nome}>
                                                        {contato.contato.nome}
                                                    </option>
                                                ))}
                                            </Form.Select>
                                        </Form.Group>
                                    </Col>
                                    <div className="produto-header">Cliente</div>
                                    <div>
                                        <div className="busca-cliente d-grid gap-2">
                                            <Form.Label htmlFor="produto" className="texto-campos">Nome</Form.Label>
                                            <Form.Control type="text" id="cliente" className="form-control" placeholder="Digite a nome do cliente" value={buscaContato || ''} onChange={this.atualizarBuscaContato} />
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
                                    <Row className="row">
                                        <Col className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="nome" className="texto-campos">Nome</Form.Label>
                                                <Form.Control type="text" id="nome" className="form-control" name="nome" value={nome || ''} onChange={this.atualizaNome} />
                                            </Form.Group>
                                        </Col>
                                        <Col className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="tipo" className="texto-campos">Tipo</Form.Label>
                                                <Form.Select as="select" id="tipo" className="form-control" name="tipo" value={tipo || ''} onChange={this.atualizaTipo}>
                                                    <option value="J">Pessoa Jurídica</option>
                                                    <option value="F">Pessoa Física</option>
                                                    <option value="E">Estrangeiro</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row className="row">
                                        <Col className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="cpf" className="texto-campos">CPF</Form.Label>
                                                <Form.Control type="text" id="cpf" className="form-control" name="cpf" value={cnpj || ''} onChange={this.atualizaCpfCnpj} />
                                            </Form.Group>
                                        </Col>
                                        <Col className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="codigo" className="texto-campos">Código</Form.Label>
                                                <Form.Control type="text" id="codigo" className="form-control" name="codigo" value={codigo || ''} onChange={this.atualizaCodigo} />
                                            </Form.Group>
                                        </Col>
                                        <Col className="col">
                                            <Form.Group className="mb-3">
                                                <Form.Label htmlFor="fantasia" className="texto-campos">Fantasia</Form.Label>
                                                <Form.Control type="text" id="fantasia" className="form-control" name="fantasia" value={fantasia || ''} onChange={this.atualizaFantasia} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <div className="divisa"></div>
                                    <div>
                                        <div className="cliente-header">Produto</div>
                                        <div>
                                            <div className="d-grid gap-2">
                                                <Form.Label htmlFor="produto" className="texto-campos">Produto</Form.Label>
                                                <Form.Control type="text" id="produto" className="form-control" placeholder="Digite a descrição do produto" value={buscaProduto || ''} onChange={this.atualizarBuscaProduto} />
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
                                                        Cód: {produto.produto.codigo} Produto: {produto.produto.descricao} - Preço R$ {produto.produto.preco = parseFloat(produto.produto.preco).toFixed(2)}
                                                    </li>
                                                ))}
                                            </ul>
                                            {produtoSelecionado && (
                                                <div className="produto-selecionado">
                                                    <h2>Produto selecionado: {produtoSelecionado.produto.descricao}</h2>
                                                </div>
                                            )}
                                        </div>
                                        <Row className="row">
                                            <Col className="col">
                                                <Form.Group className="mb-3">
                                                    <Form.Label htmlFor="quantidade" className="texto-campos">Quantidade</Form.Label>
                                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                                        <Button variant="outline-success rounded-0" type="button" onClick={this.decrementarQuantidade} disabled={!produtoSelecionado}>
                                                            -
                                                        </Button>
                                                        <Form.Control type="text" id="quantidade" className="form-control rounded-0" name="quantidade" value={quantidade || ''} onChange={this.atualizaQuantidade} disabled={!produtoSelecionado} />
                                                        <Button variant="outline-success rounded-0" type="button" onClick={this.incrementarQuantidade} disabled={!produtoSelecionado}>
                                                            +
                                                        </Button>
                                                    </div>
                                                </Form.Group>
                                            </Col>
                                            <Col className="col">
                                                <Form.Group className="mb-3">
                                                    <Form.Label htmlFor="desconto" className="texto-campos">Desconto (%)</Form.Label>
                                                    <Form.Control type="text" id="desconto" className="form-control" name="desconto" placeholder="00,00" value={valorDesconto || ''} onChange={this.atualizarDesconto} disabled />
                                                </Form.Group>
                                            </Col>
                                            <Col className="col">
                                                <Form.Group className="mb-3">
                                                    <Form.Label htmlFor="preco" className="texto-campos">Valor unitário</Form.Label>
                                                    <Form.Control type="text" id="preco" className="form-control" name="preco" placeholder="00,00" value={preco || ''} onChange={this.atualizaPreco} disabled={!produtoSelecionado} />
                                                </Form.Group>
                                            </Col>
                                            <Col className="col">
                                                <Form.Group className="mb-3">
                                                    <Form.Label htmlFor="valorTotal" className="texto-campos">Sub total</Form.Label>
                                                    <Form.Control type="number" id="valorTotal" className="form-control" name="valorTotal" placeholder="00,00" value={valorTotal || ''} onChange={this.atualizarValorTotal} readOnly />
                                                </Form.Group>
                                            </Col>
                                        </Row>
                                        <div>
                                            <Form.Label htmlFor="valorTotal" className="texto-campos">Comentário</Form.Label>
                                        </div>
                                        <div className="container">
                                            <Form.Control as="textarea" id="observacao" value={this.state.comentario || ''} onChange={this.atualizarComentario} disabled={!produtoSelecionado} style={{ height: '75px', width: '600px' }} />
                                            <Button variant="outline-success" onClick={() => this.adicionarProdutoSelecionado(produtoSelecionado)}>Inserir produto </Button>
                                        </div>
                                        {carregandoProduto &&
                                            <div>Carregando produto na lista...</div>
                                        }
                                        <div className="divisa"></div>
                                        <div className="pagamento-header">Pagamento</div>
                                        <div>
                                            <h5>Totais</h5>
                                            <div>
                                                <Row className="row">
                                                    <Col className="col">
                                                        <Form.Group className="mb-3">
                                                            <Form.Label htmlFor="subtotal" className="texto-campos">Sub total</Form.Label>
                                                            <Form.Control type="text" id="subtotal" className="" name="subtotal" placeholder="00,00" value={this.state.subTotal || ''} disabled />
                                                        </Form.Group>
                                                    </Col>
                                                    <Col className="col">
                                                        <Form.Group className="mb-3">
                                                            <Form.Label htmlFor="desconto" className="texto-campos">Desconto</Form.Label>
                                                            <Form.Control type="text" id="desconto" className="" name="desconto" placeholder="00,00" value={valorDesconto || ''} onChange={this.atualizaDesconto} onBlur={this.formatarDesconto} />
                                                        </Form.Group>
                                                    </Col>
                                                    <Col className="col">
                                                        <Form.Group className="mb-3">
                                                            <Form.Label htmlFor="totaldavenda" className="texto-campos">Total da venda</Form.Label>
                                                            <Form.Control type="text" id="totaldavenda" className="" name="totaldavenda" placeholder="00,00" defaultValue={subTotalGeral || ''} disabled />
                                                        </Form.Group>
                                                    </Col>
                                                </Row>
                                                <Row className="row">
                                                    <Col className="col">
                                                        <Form.Group className="mb-3">
                                                            <Form.Label htmlFor="totaldinheiro" className="texto-campos">Total recebido em dinheiro</Form.Label>
                                                            <Form.Control
                                                                type="text"
                                                                id="totaldinheiro"
                                                                className=""
                                                                name="totaldinheiro"
                                                                placeholder="00,00"
                                                                value={dinheiroRecebido || ''}
                                                                onChange={this.atualizaTroco}
                                                                onBlur={this.formatarTroco}
                                                            />
                                                        </Form.Group>
                                                    </Col>
                                                    <Col className="col mb-3">
                                                        <Form.Group className="mb-3">
                                                            <Form.Label htmlFor="trocodinheiro" className="texto-campos">Troco em dinheiro</Form.Label>
                                                            <Form.Control type="text" id="trocodinheiro" className="" name="trocodinheiro" placeholder="00,00" defaultValue={troco || ''} disabled />
                                                        </Form.Group>
                                                    </Col>
                                                </Row>
                                                <div className="mb-3">
                                                    <h5>Forma de pagamento</h5>
                                                </div>
                                                <Row className="mb-3">
                                                    <div className="d-flex align-items-end">
                                                        <Col className="col">
                                                            <Form.Group className="mb-3" controlId="condicao">
                                                                <Form.Label>Condição</Form.Label>
                                                                <Form.Control type="number" placeholder="Digite a condição" value={condicao || ''} onChange={this.handleChange} />
                                                            </Form.Group>
                                                        </Col>
                                                        <Col className="col">
                                                            <Form.Group className="mb-3">
                                                                <Form.Label htmlFor="gerarparcelas" className="texto-campos" style={{ marginRight: '20px' }}></Form.Label>
                                                                <Button variant="outline-success" className="" onClick={() => {
                                                                    if (this.state.subTotalGeral === 0) {
                                                                        this.modalInserirParcela();
                                                                    } else {
                                                                        this.adicionarParcela();
                                                                    }
                                                                }}
                                                                    style={{ width: "200px" }}
                                                                >
                                                                    Gerar parcelas
                                                                </Button>
                                                            </Form.Group>
                                                        </Col>
                                                    </div>
                                                    <div>
                                                        <Table responsive="lg" className="table-responsive" striped>
                                                            <thead>
                                                                <tr>
                                                                    <th>Dias</th>
                                                                    <th>Data</th>
                                                                    <th>Valor</th>
                                                                    <th>Forma</th>
                                                                    <th>Observação</th>
                                                                    <th>Ação</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                {this.state.parcelas.map((parcela, index) => (
                                                                    <tr key={index}>
                                                                        <td>
                                                                            <Form.Control
                                                                                type="number"
                                                                                value={parcela.dias}
                                                                                onChange={(e) => this.handleChangeParcela(index, 'dias', e.target.value) || ''} />
                                                                        </td>
                                                                        <td>
                                                                            <Form.Control
                                                                                type="text"
                                                                                value={this.calcularData(parcela.dias) || ''}
                                                                                disabled
                                                                            />
                                                                        </td>
                                                                        <td>
                                                                            <Form.Control
                                                                                type="number"
                                                                                value={parcela.valor}
                                                                                onChange={(event) => this.handleValorChangeParcela(index, 'valor', event.target.value)}
                                                                                disabled
                                                                            />
                                                                        </td>
                                                                        <td>
                                                                            <Form.Select
                                                                                value={parcela.forma || ''}
                                                                                onChange={(e) => this.handleFormaChange(index, e)}>
                                                                                <option value="2">Conta a receber/pagar</option>
                                                                                <option value="1">Dinheiro</option>
                                                                            </Form.Select>

                                                                        </td>
                                                                        <td>
                                                                            <Form.Control
                                                                                type="text"
                                                                                value={parcela.observacao || ''}
                                                                                onChange={(e) => this.handleObservacaoChange(index, e) || ''} />
                                                                        </td>
                                                                        <td>
                                                                            <Button variant="light" onClick={() => this.handleDeleteParcela(index)}>
                                                                                <IonIcon icon={trashOutline} className="red-icon" size="medium" />
                                                                            </Button>
                                                                        </td>
                                                                    </tr>
                                                                ))}
                                                            </tbody>
                                                        </Table>
                                                        <div className="col d-flex justify-content-end">
                                                            <Button variant="light" onClick={() => {
                                                                if (this.state.subTotalGeral === 0) {
                                                                    this.modalInserirParcela();
                                                                } else {
                                                                    this.adicionarParcela();
                                                                }
                                                            }} defaultValue="0">+ Adicionar outra parcela</Button>
                                                        </div>
                                                    </div>
                                                    {/* <Col className="col">
                                                    <Form.Group className="mb-3">
                                                        <Form.Label htmlFor="vendedor" className="texto-campos">Categoria</Form.Label>
                                                        <Form.Select className="campos-pagamento" id="vendedor" name="vendedor" value={this.state.vendedorSelecionado || ''} onChange={this.atualizaVendedorSelecionado} >
                                                            <option>Selecione uma</option>
                                                            {this.state.vendedores.map((contato) => (
                                                                <option key={contato.contato.id} value={contato.contato.nome}>
                                                                    {contato.contato.nome}
                                                                </option>
                                                            ))}
                                                        </Form.Select>
                                                    </Form.Group>
                                                </Col> */}
                                                </Row>
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
                                                                selected={dataPrevista}
                                                                onChange={this.atualizaDataPrevista}
                                                                placeholderText="Selecione uma data"
                                                                className="form-select"
                                                                dateFormat="dd/MM/yyyy"
                                                            />
                                                        </Form.Group>
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col">
                                                        <Form.Group className="mb-3">
                                                            <Form.Label htmlFor="depositolancamento" className="texto-campos">Depósito para lançamento</Form.Label>
                                                            <Form.Select className="" id="depositolancamento" name="depositolancamento" value={this.state.depositoSelecionado || ''} onChange={this.atualizaDepositoSelecionado} >
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
                                                            <Form.Control type="text" className="" id="frete" name="frete" placeholder="00,00" value={frete || ''} onChange={this.atualizaTotalComFrete} onBlur={this.formatarFrete} />
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
                        <Col md={6}>
                            <div className="grid-2">
                                <Table responsive="lg" className="table-responsive" striped>
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
                                                    ? produto.produto.preco.toFixed(2)
                                                    : parseFloat(produto.produto.preco).toFixed(2)}</td>
                                                <td>R$ {this.calcularSubTotal(produto.produto, produto.quantidade).toFixed(2)}</td>
                                                <td><Button variant="light" className="transparent-button" onClick={() => {
                                                    if (window.confirm('Deseja excluir o item?\nEssa ação não poderá ser desfeita.')) {
                                                        this.excluirProdutoSelecionado(index);
                                                    }
                                                }}>
                                                    <IonIcon icon={trashOutline} className="red-icon" size="medium" />
                                                </Button>
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
                                        <Button variant="success" onClick={this.finalizaVenda} >Finalizar Venda</Button>
                                    </div>
                                </div>
                                <div className="div_total_venda">
                                    <div>
                                        <span className="span-total">Total:</span>
                                        <span className="span-valor">R$ {this.calcularSubTotalGeral().toFixed(2)}</span>
                                    </div>
                                </div>
                            </div>
                        </Col>
                    </Row>

                    <Modal show={this.state.ModalFinalizarVendaSemItem} onHide={this.ModalFinalizarVendaSemItem} centered>
                        <Modal.Header closeButton className="bg-success text-white">
                            <Modal.Title>
                                <FontAwesomeIcon icon={faExclamationTriangle} className="mr-2 fa-2x" style={{ marginRight: '10px' }} />
                                Atenção
                            </Modal.Title>
                        </Modal.Header>
                        <Modal.Body style={{ padding: '20px' }}>
                            Este pedido não possui itens ou cliente, insira-os para realizar essa operação.
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="success" onClick={this.ModalFinalizarVendaSemItem}>Fechar</Button>
                        </Modal.Footer>
                    </Modal>

                    <Modal show={this.state.ModalExcluirPedido} onHide={this.ModalExcluirPedido} centered>
                        <Modal.Header closeButton className="bg-success text-white">
                            <FontAwesomeIcon icon={faExclamationTriangle} className="mr-2 fa-2x" style={{ marginRight: '10px' }} />
                            <Modal.Title>Atenção </Modal.Title>
                        </Modal.Header>
                        <Modal.Body style={{ padding: '20px' }}>
                            <div>Deseja excluir o pedido {this.state.numeroPedido}?</div>
                            <div>Essa ação não poderá ser desfeita.</div>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="outline-success" onClick={this.ModalExcluirPedido}>Não</Button>
                            <Button variant="success" onClick={this.excluirPedido}>Sim</Button>
                        </Modal.Footer>
                    </Modal>

                    <Modal show={this.state.modalInserirProduto} onHide={this.modalInserirProduto} centered>
                        <Modal.Header closeButton className="bg-success text-white">
                            <FontAwesomeIcon icon={faExclamationTriangle} className="mr-2 fa-2x" style={{ marginRight: '10px' }} />
                            <Modal.Title>Atenção </Modal.Title>
                        </Modal.Header>
                        <Modal.Body style={{ padding: '20px' }}>
                            Nenhum produto selecionado!
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="success" onClick={this.modalInserirProduto}>Fechar</Button>
                        </Modal.Footer>
                    </Modal>

                    <Offcanvas show={this.state.canvasFinalizarPedido} onHide={this.canvasFinalizarPedido} size="lg" centered placement="end" style={{ width: '35%' }}>
                        <Offcanvas.Header closeButton className="bg-success text-white">
                            <FontAwesomeIcon icon={faExclamationTriangle} className="mr-2 fa-2x" style={{ marginRight: '10px' }} />
                            <Offcanvas.Title>Atenção </Offcanvas.Title>
                        </Offcanvas.Header>
                        <Offcanvas.Body style={{ padding: '20px' }}>
                            <div className="d-flex flex-column  mb-4">
                                <h5 className="mb-3">
                                    <strong>
                                        Resumo do pedido:
                                    </strong>
                                </h5>
                                <div className="d-flex justify-content-between mb-2">
                                    <strong>
                                        <FontAwesomeIcon icon={faCalendarAlt} className="mr-2" style={{ marginRight: '10px' }} />
                                        Data: {new Date().toLocaleString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit' })}</strong>
                                    <span>CPF/CNPJ: {cnpj}</span>
                                </div>
                                <span>Nome do Cliente: {nome}</span>
                                <span>Endereço: {endereco}, {numero}, {bairro}, {cidade} - {uf}</span>
                            </div>
                            <Table responsive="lg" className="table-responsive" striped>
                                <thead>
                                    <tr>
                                        <th>Nome do produto</th>
                                        <th>Qtde.</th>
                                        <th>Preço un.</th>
                                        <th>Sub Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.state.produtosSelecionados.map((produto, index) => (
                                        <tr key={produto.produto.id}>
                                            <td>{produto.produto.codigo} - {produto.produto.descricao}</td>
                                            <td>{produto.quantidade}</td>
                                            <td>R$ {typeof produto.produto.preco === "number"
                                                ? produto.produto.preco.toFixed(2)
                                                : parseFloat(produto.produto.preco).toFixed(2)}</td>
                                            <td>R$ {this.calcularSubTotal(produto.produto, produto.quantidade).toFixed(2)}</td>
                                        </tr>
                                    ))}
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colSpan="3" className="text-right"><strong>Desconto:</strong></td>
                                        <td><strong>R$ {desconto},00</strong></td>
                                    </tr>
                                    <tr>
                                        <td colSpan="3" className="text-right"><strong>Quantidade total:</strong></td>
                                        <td><strong>{quantidadeTotal}</strong></td>
                                    </tr>
                                    <tr>
                                        <td colSpan="3" className="text-right"><strong>Total:</strong></td>
                                        <td><strong>R$ {this.calcularSubTotalGeral().toFixed(2)}</strong></td>
                                    </tr>
                                </tfoot>
                            </Table>

                            <div className="divisa"></div>

                            <div className="d-flex flex-column  mb-4">
                                <h5 className="mb-3">
                                    <strong>
                                        O que fazer após fechar o pedido?
                                    </strong>
                                </h5>
                            </div>
                            <div class="d-flex justify-content-center">
                                <Button variant="outline-secondary" className="gerar-nota">Gerar NFC-e</Button>
                                <Button variant="outline-secondary" className="gerar-nota">Imprimir comprovante não fiscal</Button>
                            </div>
                            <div className="d-flex justify-content-end fixed-bottom mb-4" style={{ height: '40px' }}>
                                <Button variant="outline-success" className="mr-2" onClick={this.canvasFinalizarPedido} style={{ marginRight: '10px' }}>Cancelar</Button>
                                <Button variant="success" onClick={this.gerarXmlItensParaEnvio} style={{ marginRight: '10px' }}>Fechar pedido</Button>
                            </div>
                        </Offcanvas.Body>
                    </Offcanvas>

                    <Modal show={this.state.modalInserirParcela} onHide={this.modalInserirParcela} centered>
                        <Modal.Header closeButton className="bg-success text-white">
                            <FontAwesomeIcon icon={faExclamationTriangle} className="mr-2 fa-2x" style={{ marginRight: '10px' }} />
                            <Modal.Title>Atenção </Modal.Title>
                        </Modal.Header>
                        <Modal.Body style={{ padding: '20px' }}>
                            Não foi possível gerar as parcelas, pois o valor faturado está zerado.
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="success" onClick={this.modalInserirParcela}>Fechar</Button>
                        </Modal.Footer>
                    </Modal>
                    <Modal show={this.state.modalSalvarPedido} onHide={this.modalSalvarPedido} centered>
                        <Modal.Body>
                            <span style={{ display: 'block' }} ><strong>Pedido N.º: {this.state.numeroPedido} </strong></span>
                            <span style={{ display: 'block' }} ><strong>Salvo com sucesso!</strong></span>
                        </Modal.Body>
                    </Modal>
                </Container >
            );
        }
    }
}



export default FrenteCaixa;

