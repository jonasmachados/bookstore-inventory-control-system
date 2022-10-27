
import React, { useState, useEffect } from "react";
import "./styles.css"
import { AiOutlineDashboard } from 'react-icons/ai'
import { BsPeople } from 'react-icons/bs'
import { GoBook } from 'react-icons/go'
import { MdPointOfSale } from 'react-icons/md'
import { AiOutlineShoppingCart } from 'react-icons/ai'

import LivroService from "../../services/LivroService"
import ClienteService from "../../services/ClienteService"
import CompraService from "../../services/CompraService";
import VendaService from "../../services/VendaService";

import ListCompraDash from "./ListCompraDash";
import ListVendaDash from "./ListVendaDash";

const Dashboard = () => {

    const [livros, setlivros] = useState([]);
    const [cliente, setCliente] = useState([]);
    const [compras, setCompras] = useState([]);
    const [vendas, setVendas] = useState([]);

    useEffect(() => {
        getAllLivros();
        getAllClientes();
        getAllCompras();
        getAllVendas();
    }, []);

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

    function formatNumber(number) {
        return new Intl.NumberFormat('pt-BR',
            { style: 'currency', currency: 'BRL' }).format(number)
    }

    var totalCompra = 0;
    for (var i = 0; i < compras.length; i++) {
        totalCompra = totalCompra + compras[i].total;
    }

    var totalVenda = 0;
    for (i = 0; i < vendas.length; i++) {
        totalVenda = totalVenda + vendas[i].total;
    }

    return (
        <div className="dash-content">
            <div className="overview">
                <div className="title">
                    <i><AiOutlineDashboard /></i>
                    <span className="text">Dashboard</span>
                </div>

                <div className="boxes">
                    <div className="box box1">
                        <i><BsPeople /></i>
                        <span className="text">Clientes</span>
                        <span className="number">{cliente.length}</span>
                    </div>
                    <div className="box box2">
                        <i><GoBook /></i>
                        <span className="text">Livros</span>
                        <span className="number">{livros.length}</span>
                    </div>
                    <div className="box box3">
                        <i><MdPointOfSale /></i>
                        <span className="text">Total Vendas</span>
                        <span className="number">{formatNumber(totalVenda)}</span>
                    </div>
                    <div className="box box4">
                        <i><AiOutlineShoppingCart /></i>
                        <span className="text">Total Compra</span>
                        <span className="number">{formatNumber(totalCompra)}</span>
                    </div>
                </div>

                <div class="activity">
                    <div class="title">
                        <i><AiOutlineShoppingCart /></i>
                        <span class="text">Compras Recentes</span>
                    </div>
                    <ListCompraDash/>

                    <div class="title">
                        <i><MdPointOfSale /></i>
                        <span class="text">Vendas Recentes</span>
                    </div>
                    <ListVendaDash />

                </div>
            </div>
        </div>
    )
}

export default Dashboard;