import Home from './components/Home'
import Contato from './components/Contato';
import Produto from "./components/Produto";
import Categoria from "./components/Categoria";
import FrenteCaixa from "./components/FrenteCaixa";

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import { BrowserRouter, Routes, Link, Route } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../src/css/App.css'



function App() {


  return (

    <div className="App">
      <Container fluid>
        <BrowserRouter>
          <Navbar bg="dark" expand="lg" variant="dark" fixed="top" label="Toggle navigation" >
            <Container fluid>
              <img alt="" src="/assets/min-logo.png" width="80" height="30" className="d-inline-block align-top" style={{ marginRight: '10px' }} />{' '}
              <Navbar.Brand href="/">Aplicação ERP</Navbar.Brand>
              <Navbar.Toggle aria-controls="navbar-dark-example" />
              <Navbar.Collapse id="navbar-dark-example">
                <Nav
                  className="me-auto my-2 my-lg-0"
                  style={{ maxHeight: '100px' }}
                  navbarScroll
                >                  <Nav.Link href="/" className="text-light">Página inicial</Nav.Link>

                  <NavDropdown title="Cadastros" id="nav-dropdown-dark-example" menuVariant="dark">
                    <NavDropdown.Item as={Link} to="/Contato">Clientes e Fornecedores</NavDropdown.Item>
                    <NavDropdown.Item as={Link} to="/Produto">Produtos</NavDropdown.Item>
                    <NavDropdown.Item as={Link} to="/Categoria">Categorias</NavDropdown.Item>
                  </NavDropdown>

                  <NavDropdown title="Suprimentos" id="nav-dropdown-dark-example" menuVariant="dark">
                    <NavDropdown.Item as={Link} to="/Contato">Clientes e Fornecedores</NavDropdown.Item>
                    <NavDropdown.Item as={Link} to="/Produto" >Produtos</NavDropdown.Item>
                    <NavDropdown.Item href="#action/3.3" >Something</NavDropdown.Item>
                  </NavDropdown>

                  <NavDropdown title="Vendas" id="nav-dropdown-dark-example" menuVariant="dark">
                    <NavDropdown.Item as={Link} to="/FrenteCaixa" >Frente de Caixa</NavDropdown.Item>
                  </NavDropdown>

                  <NavDropdown title="Finanças" id="nav-dropdown-dark-example" menuVariant="dark">
                    <NavDropdown.Item as={Link} to="/Contato" >Clientes e Fornecedores</NavDropdown.Item>
                    <NavDropdown.Item as={Link} to="/Produto" >Produtos</NavDropdown.Item>
                    <NavDropdown.Item href="#action/3.3" >Something</NavDropdown.Item>
                  </NavDropdown>
                </Nav>
                <Form className="d-flex">
                  <Form.Control
                    type="search" placeholder="Search" className="me-2" aria-label="Search" />
                  <Button variant="light">Busca</Button>
                </Form>
              </Navbar.Collapse>
            </Container>
          </Navbar>



          <Routes>
            <Route path="/" element={<Home />}></Route>
            <Route path="/Contato" element={<Contato />}></Route>
            <Route path="/Produto" element={<Produto />}></Route>
            <Route path="/Categoria" element={<Categoria />}></Route>
            <Route path="/FrenteCaixa" element={<FrenteCaixa />}></Route>
          </Routes>

        </BrowserRouter >
      </Container>
    </div >

  );
}

export default App;
