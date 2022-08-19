import './App.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/NaviBar/Navibar';
import Footer from './components/Footer/Footer';

import RouteComponents from './components/Route/RouteComponents';

function App() {
  return (
    <div>
   
      <Navbar />
      <RouteComponents />
      <Footer />
   
  </div >
  );
}

export default App;
