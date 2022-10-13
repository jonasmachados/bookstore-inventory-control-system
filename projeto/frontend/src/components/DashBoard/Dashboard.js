
import React from 'react';
import "./styles.css"
import { AiOutlineDashboard } from 'react-icons/ai'
import { BsPeople } from 'react-icons/bs'
import { GoBook } from 'react-icons/go'
import { MdPointOfSale } from 'react-icons/md'
import { AiOutlineShoppingCart } from 'react-icons/ai'


const Dashboard = () => {

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
                        <span className="number">10</span>
                    </div>
                    <div className="box box2">
                        <i><GoBook /></i>
                        <span className="text">Livros</span>
                        <span className="number">20</span>
                    </div>
                    <div className="box box3">
                        <i><MdPointOfSale /></i>
                        <span className="text">Total Vendas</span>
                        <span className="number">60,120</span>
                    </div>
                    <div className="box box4">
                        <i><AiOutlineShoppingCart /></i>
                        <span className="text">Total Compra</span>
                        <span className="number">20,120</span>
                    </div>
                </div>

                <div class="activity">
                    <div class="title">
                        <i><AiOutlineShoppingCart /></i>
                        <span class="text">Compras Recentes</span>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    </p>
                    <p>Praesent eu nibh vel nunc sollicitudin rhoncus.
                    </p>
                    <p>Nulla id tortor id augue tempor vehicula vitae hendrerit lorem.
                    </p>
                    <p>Aliquam a lectus vitae ante consequat iaculis.
                    </p>


                    <div class="title">
                        <i><MdPointOfSale /></i>
                        <span class="text">Vendas Recentes</span>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    </p>
                    <p>Praesent eu nibh vel nunc sollicitudin rhoncus.
                    </p>
                    <p>Nulla id tortor id augue tempor vehicula vitae hendrerit lorem.
                    </p>
                    <p>Aliquam a lectus vitae ante consequat iaculis.
                    </p>

                </div>
            </div>
        </div>
    )
}

export default Dashboard;