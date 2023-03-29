import Home from './components/Home'
import Contato from './components/Contato';
import Produto from "./components/Produto";

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

import { BrowserRouter, Routes, Link, Route } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import CadastroProduto from './components/CadastroProduto';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar bg="dark" expand="lg" variant="dark" fixed="top" >
          <Container fluid>
            <Navbar.Brand href="/">Aplicação Bling</Navbar.Brand>
            <Navbar.Toggle aria-controls="navbar-dark-example" />
            <Navbar.Collapse id="navbar-dark-example">
              <Nav>
                <Nav.Link href="/" className="text-light">Página inicial</Nav.Link>

                <NavDropdown title="Cadastros" id="nav-dropdown-dark-example" menuVariant="dark">
                  <NavDropdown.Item as={Link} to="/Contato">Clientes e Fornecedores</NavDropdown.Item>
                  <NavDropdown.Item as={Link} to="/Produto">Produtos</NavDropdown.Item>
                  <NavDropdown.Item as={Link} to="/CadastroProduto">Cadastro Prroduto</NavDropdown.Item>
                </NavDropdown>

                <NavDropdown title="Suprimentos" id="nav-dropdown-dark-example" menuVariant="dark">
                  <NavDropdown.Item as={Link} to="/Contato">Clientes e Fornecedores</NavDropdown.Item>
                  <NavDropdown.Item as={Link} to="/Produto" >Produtos</NavDropdown.Item>
                  <NavDropdown.Item href="#action/3.3" >Something</NavDropdown.Item>
                </NavDropdown>

                <NavDropdown title="Vendas" id="nav-dropdown-dark-example" menuVariant="dark">
                  <NavDropdown.Item as={Link} to="/Contato" >Clientes e Fornecedores</NavDropdown.Item>
                  <NavDropdown.Item as={Link} to="/Produto" >Produtos</NavDropdown.Item>
                  <NavDropdown.Item href="#action/3.3" >Something</NavDropdown.Item>
                </NavDropdown>

                <NavDropdown title="Finanças" id="nav-dropdown-dark-example" menuVariant="dark">
                  <NavDropdown.Item as={Link} to="/Contato" >Clientes e Fornecedores</NavDropdown.Item>
                  <NavDropdown.Item as={Link} to="/Produto" >Produtos</NavDropdown.Item>
                  <NavDropdown.Item href="#action/3.3" >Something</NavDropdown.Item>
                </NavDropdown>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>


        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/Contato" element={<Contato />}></Route>
          <Route path="/Produto" element={<Produto />}></Route>
          <Route path="/CadastroProduto" element={<CadastroProduto />}></Route>
        </Routes>

      </BrowserRouter>
    </div >
  );
}

export default App;
