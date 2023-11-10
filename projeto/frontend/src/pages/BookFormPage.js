import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom';
import BookForm from "../components/BookForm";
import BookService from '../services/BookService';

const BookFormPage = () => {

    const { id } = useParams();

    const [book, setBook] = useState('')

    useEffect(() => {
        const fetchBook = async () => {
            try {
                const response = await BookService.findBookById(id);
                setBook(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (id) {
            fetchBook();
        }
    }, [id]);

    return (
        <>

            <BookForm
                book={book}
                id={id}
            />

        </>
    );

};

export default BookFormPage;