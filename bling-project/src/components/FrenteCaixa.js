import React from "react";

import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { Container } from "react-bootstrap";
import { FaSync } from 'react-icons/fa';
import Spinner from 'react-bootstrap/Spinner';

import '../css/FrenteCaixa.css';
import { parse } from 'js2xmlparser';
import Suggest from 'react-autosuggest';



class FrenteCaixa extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            buscaProduto: '',
            buscaContato: '',
            produtos: [],
            contatos: [],
            produtoSelecionado: null,
            contatoSelecionado: null,
            carregandoProduto: false,
            carregandoContato: false,
            preco: null, // mover o preco para fora do produtoSelecionado
            produtosSelecionados: [],
            contatosSelecionados: [],
            precoProdutoSelecionado: '',
            quantidade: 1,
            cnpj: '',
        };
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
                    console.log("Objeto retornado:", produtosFiltrados);
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
                    console.log("Objeto retornado:", contatosFiltrados);
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
        })
    }

    selecionarProduto = (produto) => {
        this.setState({
            produtoSelecionado: produto,
            preco: produto.produto.preco,
            produtos: [],
        });
        this.atualizarBuscaProduto({ target: { value: '' } });
    };

    selecionarContato = (contato) => {
        this.setState({
            contatoSelecionado: contato,
            cnpj: contato.contato.cnpj,
            contatos: [],
        });
        this.atualizarBuscaContato({ target: { value: '' } });
    };

    adicionarProdutoSelecionado = (produtoSelecionado) => {
        const { produtosSelecionados, quantidade } = this.state;
        const produtoExistente = produtosSelecionados.find((produto) => produto.produto.id === produtoSelecionado.produto.id);

        if (produtoExistente) {
            produtoExistente.quantidade += quantidade; // Adiciona a quantidade selecionada
        } else {
            produtosSelecionados.push({
                produto: produtoSelecionado.produto,
                quantidade: quantidade, // Salva a quantidade selecionada
            });
        }

        this.setState({
            produtosSelecionados: produtosSelecionados,
            produtoSelecionado: null,
            preco: 0,
            quantidade: 1, // Reinicia o campo de quantidade
            produtos: [],
            buscaProduto: '',
        });
    };

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


    calcularTotal() {
        let total = 0;
        this.state.produtosSelecionados.forEach((produto) => {
            total += Number(produto.produto.preco);
        });
        return total.toFixed(2);
    }

    atualizarBuscaProduto = (e) => {
        this.setState({
            buscaProduto: e.target.value
        })
    }

    atualizarBuscaContato = (e) => {
        this.setState({
            buscaContato: e.target.value
        })
    }

    atualizaPreco = (e) => {
        const preco = Number(e.target.value);
        this.setState({ preco }, this.atualizarValorTotal);
    };

    atualizaQuantidade = (e) => {
        const quantidade = Number(e.target.value);
        this.setState({ quantidade }, this.atualizarValorTotal);
    };


    atualizarDesconto = (e) => {
        this.setState({
            desconto: e.target.value
        })
    }

    atualizarValorTotal = () => {
        const { quantidade, preco } = this.state;
        const valorTotal = quantidade * preco;
        this.setState({ valorTotal });
    };




    render() {

        const { produtos, produtoSelecionado, buscaProduto, carregandoProduto, preco, valorTotal } = this.state;
        const { contatos, contatoSelecionado, buscaContato, carregandoContato, cnpj } = this.state;

        return (

            <div className="container">
                <div className="produtos">
                    <div>
                        <div>
                            <div>
                                <label htmlFor="produto">Cliente</label>
                                <input type="text" id="produto" placeholder="Digite a descrição do produto" value={buscaContato} onChange={this.atualizarBuscaContato} />
                                <button onClick={() => this.buscarContato(buscaContato)}>Buscar</button>
                            </div>
                            <ul>
                                {contatos.map((contato) => (
                                    <li key={contato.contato.id} onClick={() => this.selecionarContato(contato)}>
                                        Nome: {contato.contato.nome} - CPF/CNPJ: {contato.contato.cnpj}
                                    </li>
                                ))}
                            </ul>
                            {contatoSelecionado && (
                                <div>
                                    <div>
                                        <h2>Contato selecionado: {contatoSelecionado.contato.nome}</h2>
                                    </div>
                                    <div>
                                        <label htmlFor="tipo">Tipo</label>
                                        <input type="text" id="tipo" name="tipo" value={contatoSelecionado.contato.tipo} onChange={this.atualizaQuantidade} />
                                    </div>
                                    <div>
                                        <label htmlFor="cpf">CPF</label>
                                        <input type="text" id="cpf" name="cpf" value={contatoSelecionado.contato.cnpj} onChange={this.atualizarDesconto} />
                                    </div>
                                    <div>
                                        <label htmlFor="codigo">Código</label>
                                        <input type="text" id="codigo" name="codigo" value={contatoSelecionado.contato.codigo} onChange={this.atualizaPreco} disabled />
                                    </div>
                                </div>
                            )}


                            <div>
                                <label htmlFor="produto">Produto</label>
                                <input type="text" id="produto" placeholder="Digite a descrição do produto" value={buscaProduto} onChange={this.atualizarBuscaProduto} />
                                <button onClick={() => this.buscarProdutos(buscaProduto)}>Buscar</button>
                            </div>
                            <ul>
                                {produtos.map((produto) => (
                                    <li key={produto.produto.id} onClick={() => this.selecionarProduto(produto)}>
                                        {produto.produto.descricao} - R${produto.produto.preco}
                                    </li>
                                ))}
                            </ul>
                            {produtoSelecionado && (
                                <div>
                                    <h2>Produto selecionado: {produtoSelecionado.produto.descricao}</h2>
                                </div>
                            )}


                            <div>
                                <label htmlFor="quantidade">Quantidade</label>
                                <input type="number" id="quantidade" name="quantidade" min="1" value={this.state.quantidade} onChange={this.atualizaQuantidade} />
                            </div>
                            <div>
                                <label htmlFor="desconto">Desconto</label>
                                <input type="text" id="desconto" name="desconto" value={this.state.desconto} onChange={this.atualizarDesconto} />
                            </div>
                            <div>
                                <label htmlFor="preco">Valor unitário</label>
                                <input type="text" id="preco" name="preco" value={preco} onChange={this.atualizaPreco} disabled />
                            </div>
                            <div>
                                <label htmlFor="valorTotal">Sub total</label>
                                <input type="text" id="valorTotal" name="valorTotal" value={this.state.valorTotal} readOnly />
                                {produtoSelecionado && (
                                    <button onClick={() => this.adicionarProdutoSelecionado(produtoSelecionado)}>Adicionar</button>
                                )}
                            </div>
                            {carregandoProduto && <div>Carregando...</div>}
                        </div>
                    </div>
                </div>
                <div className="carrinho">
                    <div>
                        <h2>Total: R${this.calcularTotal()}</h2>
                        <ul>
                            {this.state.produtosSelecionados.map((produto) => (
                                <li key={produto.produto.id}>
                                    {produto.produto.descricao} - R${produto.produto.preco}
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div >
        );
    }
}






//                 <div className="form-group">
//                     <label htmlFor="inputAddress">Address</label>
//                     <input type="text" className="form-control" id="inputAddress" placeholder="1234 Main St" />
//                 </div>
//                 <div className="form-group">
//                     <label htmlFor="inputAddress2">Address 2</label>
//                     <input type="text" className="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" />
//                 </div>
//                 <div className="form-row">
//                     <div className="form-group col-md-6">
//                         <label htmlFor="inputCity">City</label>
//                         <input type="text" className="form-control" id="inputCity" />
//                     </div>
//                     <div className="form-group col-md-4">
//                         <label htmlFor="inputState">State</label>
//                         <select id="inputState" className="form-control">
//                             <option selected>Choose...</option>
//                             <option>...</option>
//                         </select>
//                     </div>
//                     <div className="form-group col-md-2">
//                         <label htmlFor="inputZip">Zip</label>
//                         <input type="text" className="form-control" id="inputZip" />
//                     </div>
//                 </div>
//                 <div className="form-group">
//                     <div className="form-check">
//                         <input className="form-check-input" type="checkbox" id="gridCheck" />
//                         <label className="form-check-label" htmlFor="gridCheck">
//                             Check me out
//                         </label>
//                     </div>
//                 </div>
//                 <button type="submit" className="btn btn-primary">Sign in</button>
//             </form >
//         );
//     }
// }

export default FrenteCaixa;

