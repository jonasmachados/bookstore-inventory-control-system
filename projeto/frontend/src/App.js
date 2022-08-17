import './App.css';
import Footer from './components/Footer/Footer';
import Livro from './components/Livro/Livro';
import Navbar from './components/NaviBar/Navibar';

function App() {
  return (
    <div className="App">
     <Navbar />
     <Livro />
     <Footer />
    </div>
  );
}

export default App;
