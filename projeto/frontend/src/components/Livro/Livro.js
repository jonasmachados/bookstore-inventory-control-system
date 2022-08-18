
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListaLivro from './ListaLivro.js';

import "./Livro.css";

const Livro = () => {
  return (
    <section className="container-livro" >
      <Router>
        <div >

          <Routes>
            <Route path="/" element={<ListaLivro />} />
          
          </Routes>

        </div>
      </Router>

    </section>
  );
};

export default Livro;
