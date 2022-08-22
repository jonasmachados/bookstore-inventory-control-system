
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListaLivro from '../Livro/ListaLivro.js';
import AddUpdateLivroComponent from '../Livro/AddUpdateLivroComponent';
import ListaClientes from '../Cliente/ListaClientes.js';

import AddUpdatePF from '../Cliente/AddUpdatePF.js';
import AddUpdatePJ from '../Cliente/AddUpdatePJ.js';
import Cliente from '../Cliente/Cliente.js';

import Compra from '../Compra/Compra.js';

const RouteComponents = () => {
  return (
    <section>
      <Router>
        <div >

          <Routes>
            <Route path="/" element={<ListaLivro />} />
            <Route path="/add-livro" element={<AddUpdateLivroComponent />} />
            <Route path="/edit-livro/:id" element={<AddUpdateLivroComponent />} />
          
            <Route path="/clientes" element={<ListaClientes />} />
            <Route path="/add-cliente/" element={<Cliente />} />
            <Route path="/add-cliente/pf" element={<AddUpdatePF />} />
            <Route path="/add-cliente/pj" element={<AddUpdatePJ/>} />
            <Route path="/edit-cliente-pj/:id" element={<AddUpdatePJ />} />
            <Route path="/edit-cliente-pf/:id" element={<AddUpdatePF />} />
            <Route path="/compras" element={<Compra />} />

          </Routes>

        </div>
      </Router>

    </section>
  );
};

export default RouteComponents;
