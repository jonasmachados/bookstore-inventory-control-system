
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListaLivro from './ListaLivro.js';
import AddUpdateLivroComponent from './AddUpdateLivroComponent';

import ListaClientes from '../Cliente/ListaClientes.js';

import "./Livro.css";

const Livro = () => {
  return (
    <section>
      <Router>
        <div >

          <Routes>
            <Route path="/" element={<ListaLivro />} />
            <Route path="/add-livro" element={<AddUpdateLivroComponent />} />
            <Route path="/edit-livro/:id" element={<AddUpdateLivroComponent />} />
          
            <Route path="/clientes" element={<ListaClientes />} />
          </Routes>

        </div>
      </Router>

    </section>
  );
};

export default Livro;
