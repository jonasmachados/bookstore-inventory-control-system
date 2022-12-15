 // eslint-disable-next-line
import React, { useState, useEffect } from 'react'
import styled from 'styled-components'
import BurguerButton from './BurguerButton'
import "./Nav.css";
import { AiFillHome, AiOutlinePlus } from 'react-icons/ai';
import { GiWhiteBook } from 'react-icons/gi';
import { BsFillPeopleFill } from 'react-icons/bs';
import { MdPointOfSale } from 'react-icons/md';
import { BiPurchaseTagAlt } from 'react-icons/bi';
import { HiDocumentReport } from 'react-icons/hi';

import clientesPDF from "../../report/cliente/Clientes";
import livroToPDF from "../../report/cliente/LivroToPDF";
import compraToPDF from "../../report/cliente/CompraToPDF";
import VendaToPDF from "../../report/cliente/VendaToPDF";

import ClienteService from "../../services/ClienteService"
import LivroService from "../../services/LivroService"
import CompraService from "../../services/CompraService";
import VendaService from "../../services/VendaService";

function Navbar() {

  const [cliente, setCliente] = useState([]);
  const [livros, setlivros] = useState([]);
  const [compras, setCompras] = useState([]);
  const [vendas, setVendas] = useState([]);

  useEffect(() => {
    getAllClientes();
    getAllLivros();
    getAllCompras();
    getAllVendas();
  }, []);

  const [clicked, setClicked] = useState(false)
  const handleClick = () => {
    setClicked(!clicked)
  }

  const getAllClientes = () => {
    ClienteService.getAllClientes()
      .then((response) => {
        setCliente(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const getAllLivros = () => {
    LivroService.getAllLivros()
      .then((response) => {
        setlivros(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getAllCompras = () => {
    CompraService.getAllCompras()
      .then((response) => {
        setCompras(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getAllVendas = () => {
    VendaService.getAllVendas()
      .then((response) => {
        setVendas(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className='container-nav'>
      <ul className={`links ${clicked ? 'active' : ''}`}>
        <li><a onClick={handleClick} href={`/`}><p><AiFillHome />Home</p></a> </li>
        <li><a onClick={handleClick} href={`/livros`}><p><GiWhiteBook />Livros</p></a> </li>
        <li><a onClick={handleClick} href={`/clientes`}><p><BsFillPeopleFill />Clientes</p></a> </li>
        <li><a onClick={handleClick} href={`/compras`}><p><BiPurchaseTagAlt />Compras</p></a> </li>
        <li><a onClick={handleClick} href={`/vendas`}><p><MdPointOfSale />Vendas</p></a> </li>
        <li>
          <a onClick={handleClick} href={`/`}><p><HiDocumentReport />Relatórios</p></a>
          <ul className="sub-menu">
            <li><a href="#" onClick={(e) => livroToPDF(livros)}><p>Livros</p></a></li>
            <li><a href="#" onClick={(e) => clientesPDF(cliente)}><p>Clientes</p></a></li>
            <li><a href="#" onClick={(e) => compraToPDF(compras)}><p>Compras</p></a>
            </li>
            <li> <a href={"#"}><p> Vendas <AiOutlinePlus /> </p></a>
              <ul className="sub-sub-menu">
                <li><a href="#" onClick={(e) => VendaToPDF(vendas)}><p>Todas as vendas</p></a></li>
                <li><a href={`/report-sale-by-client`}><p>Vendas por cliente</p></a></li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
      <div className='burguer'>
        <BurguerButton clicked={clicked} handleClick={handleClick} />
      </div>
      <BgDiv className={`initial ${clicked ? ' active' : ''}`}></BgDiv>
    </div>
  )
}

export default Navbar

const BgDiv = styled.div`
  background: var(--primary-color);
  position: absolute;
  top: -1000px;
  left: -1000px;
  width: 100%;
  height: 100%;
  z-index: -1;
  transition: all .6s ease ;
  
  &.active{
    top: 0;
    left: 0;
    width: 100%;
    height: 580%;
  }
`
