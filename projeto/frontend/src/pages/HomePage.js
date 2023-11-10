import React, { useState, useEffect } from "react";
import Dashboard from "../components/Dashboard";
import RecentPurchases from "../components/RecentPurchases";
import RecentSales from "../components/RecentSales";
import BookService from "../services/BookService";
import CustomerService from "../services/CustomerService";
import SaleService from "../services/SaleService";
import PurchaseService from "../services/PurchaseService";
import "../styles/page.css";

const HomePage = () => {

    const [bookList, setBookList] = useState([]);
    const [customerList, setCustomerList] = useState([]);
    const [purchaseList, setPurchaseList] = useState([]);
    const [saleList, setSaleList] = useState([]);

    useEffect(() => {
        fetchBooks();
        fetchCustomers();
        fetchPurchases();
        fetchSales();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await BookService.findAllBooks();
            setBookList(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const fetchCustomers = async () => {
        try {
            const response = await CustomerService.findAllCustomers();
            setCustomerList(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const fetchPurchases = async () => {
        try {
            const response = await PurchaseService.findAllPurchase();
            setPurchaseList(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const fetchSales = async () => {
        try {
            const response = await SaleService.findAllSales();
            setSaleList(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container-page">

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