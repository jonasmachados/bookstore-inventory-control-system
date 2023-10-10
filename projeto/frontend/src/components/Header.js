import React, { useState } from "react";
import "../styles/header.css"
import { FaBars, FaTimes } from "react-icons/fa";
import { AiFillHome } from 'react-icons/ai';
import { GiWhiteBook } from 'react-icons/gi';
import { BsFillPeopleFill } from 'react-icons/bs';
import { MdPointOfSale } from 'react-icons/md';
import { BiPurchaseTagAlt, BiChevronDown } from 'react-icons/bi';
import { HiDocumentReport } from 'react-icons/hi';
import logo from "../assets/img/logo2.png";

function Header() {

    const [click, setClick] = useState(false);

    const handleClick = () => setClick(!click);

    return (
        <div className="header-container">

            <div className="logo">
                <img src={logo} alt="Logo" />
            </div>

            <div className="menu-toggle-icon" onClick={handleClick} >
                {click ? (
                    <FaTimes />
                ) : (
                    <FaBars />
                )}
            </div>

            <ul className={`nav-menu ${click ? "active" : ""}`}>
                <li>
                    <a href="/#">
                        <AiFillHome className="nav-menu-icon" />
                        Página inicial
                    </a>
                </li>

                <li>
                    <a href="/livros">
                        <GiWhiteBook className="nav-menu-icon" />
                        Livros
                    </a>
                </li>

                <li>
                    <a href="/">
                        <BsFillPeopleFill className="nav-menu-icon" />
                        Clientes
                    </a>
                </li>

                <li>
                    <a href="/categoria/1">
                        <BiPurchaseTagAlt className="nav-menu-icon" />
                        Compras
                    </a>
                </li>

                <li>
                    <a href="/">
                        <MdPointOfSale className="nav-menu-icon" />
                        Vendas
                    </a>
                </li>

                <li className="sub-meunu-li">
                    <a href={`/`}>
                        <HiDocumentReport className="nav-menu-icon" />
                        Relatórios
                        <BiChevronDown className="nav-menu-icon" />
                    </a>
                    <ul className="sub-menu">
                        <li><a href="/" >Livros</a></li>
                        <li><a href="/" >Clientes</a></li>
                        <li><a href="/" >Compras</a>
                        </li>
                    </ul>
                </li>
            </ul>

        </div>
    );
}

export default Header;