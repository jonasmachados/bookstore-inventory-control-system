import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import BookService from '../services/BookService';
import SaleService from '../services/SaleService';
import SaleForm from '../components/SaleForm';
import CustomerService from '../services/CustomerService';

const SaleFormPage = () => {

    const { id } = useParams();
    const [sale, setSale] = useState([]);
    const [bookList, setBookList] = useState([]);
    const [customerList, setCustomerList] = useState([]);

    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await CustomerService.findAllCustomers();
                setCustomerList(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        const fetchBooks = async () => {
            try {
                const response = await BookService.findAllBooks();
                setBookList(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        const fetchSale = async () => {
            try {
                const response = await SaleService.findSaleById(id);
                setSale(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (id) {
            fetchSale();
        }
        fetchBooks();
        fetchCustomers();
    }, [id]);

    return (

        <SaleForm
            id={id}
            sale={sale}
            bookList={bookList}
            customerList={customerList}
        />

    );

};

export default SaleFormPage;