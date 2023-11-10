import React, { useState, useEffect } from "react";
import BookList from "../components/BookList";
import BookService from "../services/BookService";
import "../styles/page.css"

const BookListPage = () => {

    const [bookList, setBookList] = useState([]);

    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await BookService.findAllBooks();
            setBookList(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleDeleteBook = async (bookId) => {
        try {
            await BookService.deletebook(bookId);
            fetchBooks();
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container-page">

            <BookList
                bookList={bookList}
                onDeleteBook={handleDeleteBook}
            />

        </div>
    );
};

export default BookListPage; 