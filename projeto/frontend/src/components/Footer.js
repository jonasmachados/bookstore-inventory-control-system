import "../styles/footer.css"
import logo from "../assets/img/logo3.png";
import { FaLinkedinIn, FaTwitter } from 'react-icons/fa';
import { AiFillInstagram } from 'react-icons/ai';

function Footer() {

  return (
    <div className="footer-container">

      <div>
        <h2>EXPLORE</h2>
        <p>
          <a href="/#">Homepage</a>
        </p>
        <p>
          <a href="/Livros">Livros</a>
        </p>
        <p>
          <a href="/Clientes">Clientes</a>
        </p>
        <p>
          <a href="/Compras">Compras</a>
        </p>
      </div>

      <div>
        <h2>INFORMAÇOES DE CONTATO</h2>
        <p>WhatsApp: (44) 99115-4370</p>
        <p>Email: atendimento@ descricao.com.br</p>
        <div className="footer-social-icon">
          <a href="https://twitter.com/">
            <i><FaTwitter /></i>
          </a>
          <a href="https://www.instagram.com/">
            <i> <AiFillInstagram /></i>
          </a>
          <a href="https://www.linkedin.com/">
            <i><FaLinkedinIn /></i>
          </a>
        </div>
      </div>

      <div>
        <img
          src={logo}
          alt="Logo"
          className="footer-logo" />
        <h2>© 2023 Noticias do Sul de Minas. Todos os direitos reservados.</h2>
      </div>

    </div>
  );
}

export default Footer;