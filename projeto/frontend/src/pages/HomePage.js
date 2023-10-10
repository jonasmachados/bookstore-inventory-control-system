import React, { useState, useEffect } from "react";
import Dashboard from "../components/Dashboard";
import RecentPurchases from "../components/RecentPurchases";
import RecentSales from "../components/RecentSales";
import LivroService from "../services/LivroService"
import ClienteService from "../services/ClienteService"
import CompraService from "../services/CompraService";
import VendaService from "../services/VendaService";

import "../styles/home-page.css"

const HomePage = () => {

    const [bookList, setBookList] = useState([]);
    const [customerList, setCustomerList] = useState([]);
    const [purchaseList, setPurchaseList] = useState([]);
    const [saleList, setSaleList] = useState([]);

    useEffect(() => {
        getAllLivros();
        getAllClientes();
        getAllCompras();
        getAllVendas();
    }, []);

    const getAllLivros = () => {
        LivroService.getAllLivros()
            .then((response) => {
                setBookList(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getAllClientes = () => {
        ClienteService.getAllClientes()
            .then((response) => {
                setCustomerList(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getAllCompras = () => {
        CompraService.getAllCompras()
            .then((response) => {
                setPurchaseList(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getAllVendas = () => {
        VendaService.getAllVendas()
            .then((response) => {
                setSaleList(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="home-page-container">

            <Dashboard
                customerList={customerList}
                bookList={bookList}
                purchaseList={purchaseList}
                saleList={saleList}
            />

            <RecentPurchases purchaseList={purchaseList} />

            <RecentSales saleList={saleList} />

        </div>
    )
}

export default HomePage;