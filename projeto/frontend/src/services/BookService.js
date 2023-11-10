import axios from 'axios';

const BOOK_API_BASE_URL = process.env.REACT_APP_BOOK_API_BASE_URL ??
    "http://localhost:8080/livro";

class BookService {

    findAllBooks() {
        return axios.get(BOOK_API_BASE_URL);
    }

    findBookById(bookId) {
        return axios.get(
            BOOK_API_BASE_URL
            + '/'
            + bookId);
    }

    insertBook(book) {
        return axios.post(
            BOOK_API_BASE_URL,
            book);
    }

    updateBook(bookId, book) {
        return axios.put(
            BOOK_API_BASE_URL
            + '/'
            + bookId,
            book);
    }

    deletebook(bookId) {
        return axios.delete(
            BOOK_API_BASE_URL
            + '/'
            + bookId);
    }

}

export default new BookService()
