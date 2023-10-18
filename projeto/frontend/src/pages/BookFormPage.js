import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom';
import BookForm from "../components/BookForm";
import LivroService from '../services/LivroService';

const BookFormPage = () => {

    const { id } = useParams();

    const [book, setBook] = useState('')

    useEffect(() => {
        const getBook = async () => {
            try {
                const response = await LivroService.getLivroById(id);
                setBook(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (id) {
            getBook();
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