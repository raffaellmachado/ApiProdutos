import { Link } from 'react-router-dom';

function NotFound() {
  return (
    <div>
      <h2>Página não encontrada</h2>
      <p>A página que você está procurando não foi encontrada.</p>
      <Link to="/">Voltar à página inicial</Link>
    </div>
  );
}

export default NotFound;
