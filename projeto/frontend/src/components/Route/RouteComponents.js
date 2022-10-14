
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListaLivro from '../Livro/ListaLivro.js';
import AddUpdateLivroComponent from '../Livro/AddUpdateLivroComponent';
import ListaClientes from '../Cliente/ListaClientes.js';

import AddUpdatePF from '../Cliente/AddUpdatePF.js';
import AddUpdatePJ from '../Cliente/AddUpdatePJ.js';
import Cliente from '../Cliente/Cliente.js';

import ListaCompra from '../Compra/ListaCompra.js';
import AddUpdateCompra from '../Compra/AddUpdateCompra.js';

import ListaVendas from '../Venda/ListaVendas.js';
import AddUpdateVenda from '../Venda/AddUpdateVenda.js';
import Teste from '../Venda/Teste.js';

import Dashboard from '../DashBoard/Dashboard.js';

const RouteComponents = () => {
  return (
    <section>
      <Router>
        <div >

          <Routes>
            <Route path="/livros" element={<ListaLivro />} />
            <Route path="/add-livro" element={<AddUpdateLivroComponent />} />
            <Route path="/edit-livro/:id" element={<AddUpdateLivroComponent />} />
          
            <Route path="/clientes" element={<ListaClientes />} />
            <Route path="/add-cliente/" element={<Cliente />} />
            <Route path="/add-cliente/pf" element={<AddUpdatePF />} />
            <Route path="/add-cliente/pj" element={<AddUpdatePJ/>} />
            <Route path="/edit-cliente-pj/:id" element={<AddUpdatePJ />} />
            <Route path="/edit-cliente-pf/:id" element={<AddUpdatePF />} />
            
            <Route path="/compras" element={<ListaCompra />} />
            <Route path="/add-compra/" element={<AddUpdateCompra />} />
            <Route path="/edit-compra/:id" element={<AddUpdateCompra />} />

            <Route path="/vendas" element={<ListaVendas />} />
            <Route path="/add-venda/" element={<AddUpdateVenda />} />
            <Route path="/edit-venda/:id" element={<AddUpdateVenda />} />

            <Route path="/" element={<Dashboard />} />

            <Route path="/teste/" element={<Teste />} />

          </Routes>

        </div>
      </Router>

    </section>
  );
};

export default RouteComponents;
