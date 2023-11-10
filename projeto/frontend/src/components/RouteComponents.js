import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage.js';
import NotFoundPage from '../pages/NotFoundPage.js';
import BookDetailsPage from '../pages/BookListPage.js';
import BookFormPage from '../pages/BookFormPage.js';
import CustomerListPage from '../pages/CustomerListPage.js';
import CustomerFormPage from '../pages/CustomerFormPage.js';
import PurchaseListPage from '../pages/PurchaseListPage.js';
import PurchaseFormPage from '../pages/PurchaseFormPage.js';
import SaleListPage from '../pages/SaleListPage.js';
import SaleFormPage from '../pages/SaleFormPage.js';

const RouteComponents = () => {
  return (
    <>
      <Router>

        <Routes>
          <Route path="/" element={<HomePage />} />

          <Route path="/*" element={<NotFoundPage />} />

          <Route path="/livros" element={<BookDetailsPage />} />
          <Route path="/add-livro" element={<BookFormPage />} />
          <Route path="/edit-livro/:id" element={<BookFormPage />} />

          <Route path="/clientes" element={<CustomerListPage />} />
          <Route path="/add-cliente/" element={<CustomerFormPage />} />
          <Route path="/edit-cliente/:id" element={<CustomerFormPage />} />

          <Route path="/compras" element={<PurchaseListPage />} />
          <Route path="/add-compra/" element={<PurchaseFormPage />} />
          <Route path="/edit-compra/:id" element={<PurchaseFormPage />} />

          <Route path="/vendas" element={<SaleListPage />} />
          <Route path="/add-venda/" element={<SaleFormPage />} />
          <Route path="/edit-venda/:id" element={<SaleFormPage />} />
        </Routes>

      </Router>
    </>
  );

};

export default RouteComponents;
