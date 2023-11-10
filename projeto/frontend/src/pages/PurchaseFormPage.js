import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import PurchaseForm from '../components/PurchaseForm';
import BookService from '../services/BookService';
import PurchaseService from '../services/PurchaseService';

const PurchaseFormPage = () => {

    const { id } = useParams();
    const [purchase, setPurchase] = useState([]);
    const [booklist, setBookList] = useState([]);

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await BookService.findAllBooks();
                setBookList(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        const fetchPurchase = async () => {
            try {
                const response = await PurchaseService.findPurchaseById(id);
                setPurchase(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (id) {
            fetchPurchase();
        }
        fetchBooks();
    }, [id]);

    return (
        <>

            <PurchaseForm
                id={id}
                purchase={purchase}
                bookList={booklist}
            />

        </>
    );

};

export default PurchaseFormPage;