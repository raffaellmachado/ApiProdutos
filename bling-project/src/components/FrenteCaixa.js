import React from "react";

import Spinner from 'react-bootstrap/Spinner';
import '../css/FrenteCaixa.css';
import Button from 'react-bootstrap/Button';
import { IonIcon } from '@ionic/react';
import { trashOutline } from 'ionicons/icons';


class FrenteCaixa extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            buscaProduto: '',
            buscaContato: '',
            buscaVendedor: '',
            produtos: [],
            contatos: [],
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
            dinheiro: 0
        };
        this.atualizaDesconto = this.atualizaDesconto.bind(this);

    }

    componentDidMount() {
        this.calcularTotal();
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
                    const vendedoresFiltrados = dados.retorno.contatos.filter(
                        (contato) =>
                            contato[0].contato.tiposContato[0].tipoContato.descricao &&
                            contato[0].contato.tiposContato[0].tipoContato.descricao
                                .toLowerCase()
                                .includes(value.toLowerCase()) &&
                            contato[0].contato.tiposContato[0].tipoContato.descricao
                                .toLowerCase()
                                .includes("Vendedor")
                    );
                    console.log("Objeto retornado:", vendedoresFiltrados);
                    this.setState({
                        vendedor: vendedoresFiltrados,
                        vendedorSelecionado: null,
                        carregando: false,
                    });
                } else {
                    this.setState({ vendedor: [], carregando: false });
                }
            })
            .catch((error) => {
                console.log("Erro ao buscar vendedor:", error);
                this.setState({ vendedor: [], carregando: false });
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
            alert('Nenhum produto selecionado!');
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
        const { produtosSelecionados } = this.state;
        produtosSelecionados.splice(index, 1);
        this.setState({ produtosSelecionados });
    }

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
                valorDesconto: '',
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
        this.setState({
            nome: e.target.value
        });
    };

    atualizaTipo = (e) => {
        this.setState({
            tipo: e.target.value
        });
    };

    atualizaCpfCnpj = (e) => {
        this.setState({
            cnpj: e.target.value
        });
    };

    atualizaCodigo = (e) => {
        this.setState({
            codigo: e.target.value
        });
    };

    atualizaFantasia = (e) => {
        this.setState({
            fantasia: e.target.value
        });
    };


    render() {


        const { produtos, produtoSelecionado, produtosSelecionados, buscaProduto, carregandoProduto, preco, valorTotal, quantidade, desconto } = this.state;
        const { contatos, contatoSelecionado, buscaContato, carregandoContato, cnpj, nome, tipo, codigo, fantasia, buscaVendedor, valorDesconto, totalComDesconto, total, index, troco, dinheiro } = this.state;

        return (


            <div className="container-fluid">

                <div className="card">
                    <div className="produto-header">Cliente</div>
                    <div>
                        <div className="busca-cliente d-grid gap-2">
                            <label htmlFor="produto">Nome</label>
                            <input type="text" id="cliente" className="form-control" placeholder="Digite a nome do cliente" value={buscaContato} onChange={this.atualizarBuscaContato} />
                            <Button variant="secondary" onClick={() => this.buscarContato(buscaContato)}>Buscar</Button>
                        </div>
                        <ul className="lista-contatos">
                            {contatos.map((contato) => (
                                <li key={contato.contato.id} onClick={() => this.selecionarContato(contato)}>
                                    Nome: {contato.contato.nome} - CPF/CNPJ: {contato.contato.cnpj}
                                </li>
                            ))}
                        </ul>
                    </div>

                    <div className="linha-1">
                        <div className="campo-nome">
                            <label htmlFor="nome">Nome</label>
                            <input type="text" id="nome" className="form-control" name="nome" value={nome || ''} onChange={this.atualizaNome} />
                        </div>
                        <div className="campo-tipo">
                            <label htmlFor="tipo">Tipo</label>
                            <select id="tipo" className="form-control" name="tipo" value={tipo || ''} onChange={this.atualizaTipo}>
                                <option value="J">Pessoa Jurídica</option>
                                <option value="F">Pessoa Física</option>
                                <option value="E">Estrangeiro</option>
                            </select>
                        </div>
                    </div>
                    <div className="linha-2">
                        <div>
                            <label htmlFor="cpf">CPF</label>
                            <input type="text" id="cpf" className="form-control" name="cpf" value={cnpj || ''} onChange={this.atualizaCpfCnpj} />
                        </div>
                        <div>
                            <label htmlFor="codigo">Código</label>
                            <input type="text" id="codigo" className="form-control" name="codigo" value={codigo || ''} onChange={this.atualizaCodigo} />
                        </div>
                        <div>
                            <label htmlFor="fantasia">Fantasia</label>
                            <input type="text" id="fantasia" className="form-control" name="fantasia" value={fantasia || ''} onChange={this.atualizaFantasia} />
                        </div>
                    </div>

                    <div className="divisa"></div>

                    <div>
                        <div className="cliente-header">Produto</div>
                        <div>
                            <div className="d-grid gap-2">
                                <label htmlFor="produto" >Produto</label>
                                <input type="text" id="produto" className="form-control" placeholder="Digite a descrição do produto" value={buscaProduto} onChange={this.atualizarBuscaProduto} />
                                <Button variant="secondary" onClick={() => this.buscarProdutos(buscaProduto)}>Buscar</Button>
                            </div>
                            <ul className="lista-produtos">
                                {produtos.map((produto) => (
                                    <li key={produto.produto.id} onClick={() => this.selecionarProduto(produto)}>
                                        {produto.produto.descricao} - R${produto.produto.preco}
                                    </li>
                                ))}
                            </ul>
                            {produtoSelecionado && (
                                <div className="produto-selecionado">
                                    <h2>Produto selecionado: {produtoSelecionado.produto.descricao}</h2>
                                </div>
                            )}
                        </div>
                        <div style={{ display: 'flex' }}>
                            <div style={{ width: '25%' }}>
                                <label htmlFor="quantidade">Quantidade</label>
                                <div>
                                    <input type="text" id="quantidade" className="form-control" name="quantidade" value={quantidade || ''} onChange={this.atualizaQuantidade} />
                                </div>
                            </div>
                            <div style={{ width: '25%' }}>
                                <label htmlFor="desconto">Desconto (%)</label>
                                <div>
                                    <input type="text" id="desconto" className="form-control" name="desconto" placeholder="00,00" value={desconto || ''} onChange={this.atualizarDesconto} disabled />
                                </div>
                            </div>
                            <div style={{ width: '25%' }}>
                                <label htmlFor="preco">Valor unitário</label>
                                <input type="text" id="preco" className="form-control" name="preco" placeholder="00,00" value={preco || ''} onChange={this.atualizaPreco} />
                            </div>
                            <div style={{ width: '25%' }}>
                                <label htmlFor="valorTotal">Sub total</label>
                                <div>
                                    <input type="number" id="valorTotal" className="form-control" name="valorTotal" placeholder="00,00" value={this.state.valorTotal || ''} onChange={this.atualizarValorTotal} readOnly />
                                </div>
                            </div>
                        </div>

                        <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '10px' }}>
                            <div style={{ width: '75%' }}>
                                <label htmlFor="observacao">Comentário</label>
                                <textarea className="form-control" id="observacao" rows="3"></textarea>
                            </div>
                            <div style={{ clear: 'both' }} className="botao-inserir">
                                <Button onClick={() => this.adicionarProdutoSelecionado(produtoSelecionado)}>Inserir</Button>
                            </div>
                        </div>
                        {carregandoProduto && <div>Carregando...</div>}

                        <div className="divisa"></div>
                        <div className="pagamento-header">Pagamento</div>

                        {/* <div className="busca-cliente d-grid gap-2">
                            <label htmlFor="vendedor">Vendedor</label>
                            <input type="text" id="vendedor" className="form-control" placeholder="Digite o nome do vendedor" value={buscaVendedor} onChange={this.atualizarBuscaVendedor} />
                            <Button variant="secondary" onClick={() => this.buscarVendedor(buscaVendedor)}>Buscar</Button>
                        </div>
                        <ul className="lista-contatos">
                            {contatos.map((contato) => (
                                <li key={contato.contato.id} onClick={() => this.selecionarVendedor(contato)}>
                                    Nome: {contato.contato.nome} - CPF/CNPJ: {contato.contato.cnpj}
                                </li>
                            ))}
                        </ul> */}
                        <div>
                            <h3>Totais</h3>
                            <div>
                                <div className="linha-1">
                                    <div className="pagamento-subtotal">
                                        <label htmlFor="subtotal">Sub total</label>
                                        <input type="text" id="subtotal" className="form-control" name="subtotal" placeholder="00,00" value={this.state.subTotal || ''} disabled />
                                    </div>
                                    <div className="pagamento-desconto">
                                        <label htmlFor="desconto">Desconto</label>
                                        <input type="text" id="desconto" className="form-control" name="desconto" placeholder="00,00" value={valorDesconto || ''} onChange={this.atualizaDesconto} />
                                    </div>
                                    <div className="pagamento-totaldavenda">
                                        <label htmlFor="totaldavenda">Total da venda</label>
                                        <input type="text" id="totaldavenda" className="form-control" name="totaldavenda" placeholder="00,00" value={totalComDesconto || ''} disabled />
                                    </div>

                                </div>
                                <div className="linha-2">
                                    <div className="pagamento-totaldinheiro">
                                        <label htmlFor="totaldinheiro">Total recebido em dinheiro</label>
                                        <input type="text" id="totaldinheiro" className="form-control" name="totaldinheiro" placeholder="00,00" value={dinheiro || ''} onInput={this.atualizaTroco} />
                                    </div>
                                    <div className="pagamento-trocodinheiro">
                                        <label htmlFor="trocodinheiro">Troco em dinheiro</label>
                                        <input type="text" id="trocodinheiro" className="form-control" name="trocodinheiro" placeholder="00,00" value={troco || ''} disabled />
                                    </div>
                                </div>
                                <h3>Forma de Pagamento</h3>
                            </div>
                        </div>
                    </div>
                </div>


                <div className="card">
                    <div>
                        <table>
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
                        </table>
                    </div>
                </div>

                <div className="rodape">
                    <div className="botoes">
                        <div className="botao-excluirvenda">
                            <button>Excluir venda</button>
                        </div>
                        <div className="botao-finalizarvenda">
                            <button>Finalizar Venda</button>
                        </div>
                    </div>
                    <div className="div_total_venda">
                        <div>
                            <span className="span-total">Total:</span>
                            <span className="span-valor">R$ {this.state.subTotal}</span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default FrenteCaixa;

