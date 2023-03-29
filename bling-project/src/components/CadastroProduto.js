import React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Row } from "react-bootstrap";
import { Col } from "react-bootstrap";


class CadastroProduto extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            id: 0,
            descricao: '',
            codigo: '',
            unidade: '',
            preco: '',
            estoqueMaximo: '',
        };
    }

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        // Lógica para enviar o formulário
    }

    render() {
        return (
            <>
                <Form>
                    <Row className="mb-3">
                        <Col>
                            <Form.Group controlId="id" className="mb-3 form-row" as={Col} onSubmit={this.handleSubmit}>
                                <Form.Label>ID</Form.Label>
                                <Form.Control type="text" value={this.state.id || ''} readOnly={true} />
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group controlId="codigo" className="mb-3">
                                <Form.Label>Código</Form.Label>
                                <Form.Control type="text" placeholder="Enter código" value={this.state.codigo || ''} onChange={this.atualizaCodigo} />
                            </Form.Group>
                        </Col>
                    </Row>

                    <div className="col">
                        <Form.Group controlId="nome" className="mb-3">
                            <Form.Label>Nome</Form.Label>
                            <Form.Control type="text" placeholder="Digite o nome" value={this.state.nome || ''} onChange={this.atualizaNome} />
                        </Form.Group>
                    </div>
                    <div className="col">
                        <Form.Group controlId="fantasia" className="mb-3">
                            <Form.Label>Fantasia</Form.Label>
                            <Form.Control type="text" placeholder="Enter fantasia" value={this.state.fantasia || ''} onChange={this.atualizaFantasia} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="tipo" className="mb-3">
                            <Form.Label>Tipo Pessoa</Form.Label>
                            <Form.Select as="select" placeholder="Tipo de pessoa" value={this.state.tipoPessoa = this.state.tipo || ''} onChange={this.atualizaTipoPessoa}>
                                <option value="">Selecione o tipo de pessoa</option>
                                <option value="J">Pessoa Jurídica</option>
                                <option value="F">Pessoa Física</option>
                                <option value="E">Estrangeiro</option>
                            </Form.Select>
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="contribuinte" className="mb-3">
                            <Form.Label>Contribuinte</Form.Label>
                            <Form.Select as="select" placeholder="Contribuinte" value={this.state.contribuinte || ''} onChange={this.atualizaContribuinte}>
                                <option value="">Selecione o tipo de pessoa</option>
                                <option value="1">1 - Contribuinte ICMS</option>
                                <option value="2">2 - Contribuinte isento de Inscrição no Cadastro de Contribuintes</option>
                                <option value="9">9 - Não contribuinte, que pode ou não possuir Inscrição Estadual no Cadastro de Contribuintes</option>
                            </Form.Select>
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="cnpj" className="mb-3">
                            <Form.Label>CPF/CNPJ</Form.Label>
                            <Form.Control type="text" placeholder="Digite o CPF ou CNPJ" value={this.state.cpf_cnpj = this.state.cnpj || ''} onChange={this.atualizaCpfCnpj} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="ie_rg" className="mb-3">
                            <Form.Label>IE/RG</Form.Label>
                            <Form.Control type="text" placeholder="Enter IE/RG" value={this.state.ie_rg || ''} onChange={this.atualizaIe_Rg} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="cep" className="mb-3">
                            <Form.Label>CEP</Form.Label>
                            <Form.Control type="text" placeholder="Enter CEP" value={this.state.cep || ''} onChange={this.atualizaCep} onBlur={this.checkCEP} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="endereco" className="mb-3">
                            <Form.Label>Endereço</Form.Label>
                            <Form.Control type="text" placeholder="Insira o endereço" value={this.state.logradouro} onChange={this.atualizaEndereco} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="numero" className="mb-3">
                            <Form.Label>Número</Form.Label>
                            <Form.Control type="text" placeholder="Enter número" value={this.state.numero || ''} onChange={this.atualizaNumero} ref={this.numeroRef} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="complemento" className="mb-3">
                            <Form.Label>Complemento</Form.Label>
                            <Form.Control type="text" placeholder="Enter complemento" value={this.state.complemento || ''} onChange={this.atualizaComplemento} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="bairro" className="mb-3">
                            <Form.Label>Bairro</Form.Label>
                            <Form.Control type="text" placeholder="Enter bairro" value={this.state.bairro || ''} onChange={this.atualizaBairro} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="cidade" className="mb-3">
                            <Form.Label>Cidade</Form.Label>
                            <Form.Control type="text" placeholder="Enter cidade" value={this.state.localidade} onChange={this.atualizaCidade} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="uf" className="mb-3">
                            <Form.Label>UF</Form.Label>
                            <Form.Select as="select" placeholder="Enter UF" value={this.state.uf || ''} onChange={this.atualizaUf} >
                                <option value="AC">Acre</option>
                                <option value="AL">Alagoas</option>
                                <option value="AP">Amapá</option>
                                <option value="AM">Amazonas</option>
                                <option value="BA">Bahia</option>
                                <option value="CE">Ceará</option>
                                <option value="DF">Distrito Federal</option>
                                <option value="ES">Espírito Santo</option>
                                <option value="GO">Goiás</option>
                                <option value="MA">Maranhão</option>
                                <option value="MT">Mato Grosso</option>
                                <option value="MS">Mato Grosso do Sul</option>
                                <option value="MG">Minas Gerais</option>
                                <option value="PA">Pará</option>
                                <option value="PB">Paraíba</option>
                                <option value="PR">Paraná</option>
                                <option value="PE">Pernambuco</option>
                                <option value="PI">Piauí</option>
                                <option value="RJ">Rio de Janeiro</option>
                                <option value="RN">Rio Grande do Norte</option>
                                <option value="RS">Rio Grande do Sul</option>
                                <option value="RO">Rondônia</option>
                                <option value="RR">Roraima</option>
                                <option value="SC">Santa Catarina</option>
                                <option value="SP">São Paulo</option>
                                <option value="SE">Sergipe</option>
                                <option value="TO">Tocantins</option>
                                <option value="EX">Estrangeiro</option>
                            </Form.Select>
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="fone" className="mb-3">
                            <Form.Label>Fone</Form.Label>
                            <Form.Control type="text" placeholder="Digite o numero de telefone" value={this.state.fone || ''} onChange={this.atualizaFone} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="celular" className="mb-3">
                            <Form.Label>Celular</Form.Label>
                            <Form.Control type="text" placeholder="Digite o numero de celular" value={this.state.celular || ''} onChange={this.atualizaCelular} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="email" className="mb-3">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" placeholder="Digite o e-mail NFE" value={this.state.email || ''} onChange={this.atualizaEmail} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="emailNfe" className="mb-3">
                            <Form.Label>Email NFE</Form.Label>
                            <Form.Control type="email" placeholder="Digite o e-mail NFE" value={this.state.emailNfe || ''} onChange={this.atualizaEmailNfe} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="informacaoContato" className="mb-3">
                            <Form.Label>Informação Contato</Form.Label>
                            <Form.Control type="text" placeholder="Digite a informação do contato" value={this.state.informacaoContato || ''} onChange={this.atualizaInformacaoContato} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="limiteCredito" className="mb-3">
                            <Form.Label>Limite Crédito</Form.Label>
                            <Form.Control type="text" placeholder="Digite o limite de crédito" value={this.state.limiteCredito || ''} onChange={this.atualizaLimiteCredito} />
                        </Form.Group>
                    </div>

                    <div className="col">
                        <Form.Group controlId="descricao" className="mb-3">
                            <Form.Label>Tipo Contato</Form.Label>
                            <Form.Control as="select" placeholder="Tipo de contato" value={this.state.descricao || ''} onChange={this.atualizaDescricao}>
                                <option value="">Selecione o tipo de contato</option>
                                <option value="Cliente">Cliente</option>
                                <option value="Fornecedor">Fornecedor</option>
                                <option value="Tecnico">Tecnico</option>
                                <option value="Transportador">Transportador</option>
                            </Form.Control>
                        </Form.Group>
                    </div>
                </Form>
            </>
        )
    }
}

export default CadastroProduto;
