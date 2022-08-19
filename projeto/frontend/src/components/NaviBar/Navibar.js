import React, { useState } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import "./Nav.css";
import '../../App.css';

const Navbar = () => {
  const [click, setClick] = useState(false);

  const handleClick = () => setClick(!click);

  return (
    <div className="navbar">
      <ul className={click ? "nav-menu active" : "nav-menu"}>
        <li className="nav-item">
          <a href="/#home">Home</a>
        </li>
        <li className="nav-item">
          <a href="/#">Livros</a>
        </li>
        <li className="nav-item">
          <a href={`/clientes`}>Clientes</a>
        </li>
        <li className="nav-item">
          <a href="/#">Compras/Vendas</a>
        </li>
        <li className="nav-item">
          <a href="/#">Contact</a>
        </li>
      </ul>
      <div className="hamburger" onClick={handleClick}>
        {click ? (
          <FaTimes size={30} style={{ color: "#f8f8f8" }} />
        ) : (
          <FaBars size={30} style={{ color: "#f8f8f8" }} />
        )}
      </div>
    </div>
  );
};

export default Navbar;
