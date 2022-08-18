import './App.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/NaviBar/Navibar';
import Footer from './components/Footer/Footer';

import Livro from './components/Livro/Livro';

function App() {
  return (
    <div>
   
      <Navbar />
      <Livro />
      <Footer />
   
  </div >
  );
}

export default App;
