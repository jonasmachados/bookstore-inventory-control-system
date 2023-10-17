import React, { useState, useEffect } from "react";
import BookList from "../components/BookList";
import LivroService from "../services/LivroService";
import "../styles/book-list-page.css"

const BookListPage = () => {

    const [bookList, setBookList] = useState([]);

    useEffect(() => {
        getAllLivros();
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

    const handleDeleteBook = (bookId) => {
        LivroService.deleteLivro(bookId)
            .then(() => {
                getAllLivros();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="container-book-list">

            <BookList
                bookList={bookList}
                onDeleteBook={handleDeleteBook}
            />

        </div>
    );
};

export default BookListPage; 