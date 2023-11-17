import { Link } from "react-router-dom";
import livroImg from "../assets/img/livro.svg";
import BookToPDF from "../report/BookToPDF";
import "../styles/table.css"
import "../styles/button.css"
import { AiOutlinePrinter, AiOutlineFolderAdd, AiOutlineDelete } from 'react-icons/ai'
import { BiEdit } from 'react-icons/bi'
import { GiBookmarklet } from 'react-icons/gi'
import TitleWithIcon from "./TitleWithIcon";
import formatDateToDdMonthYyyy from "../utils/formatDateToDdMonthYyyy";
import NoDataMessage from "./NoDataMessage";

const BookList = ({ bookList, onDeleteBook }) => {

  const isEmpty = bookList.length === 0;

  return (
    <>

      <div className="table-title">
        <TitleWithIcon
          icon={<GiBookmarklet />}
          title="Livros"
        />
      </div>

      <div className="button-container">
        <Link to="/add-livro" className="button primary-button">
          <AiOutlineFolderAdd className="button-icon" />
          Novo
        </Link>

        <button
          onClick={(e) => BookToPDF(bookList)}
          className="button secondary-button" >
          <AiOutlinePrinter className="button-icon" />
          Print
        </button>
      </div>

      {isEmpty ? (
        <NoDataMessage
          header={"Oops, nenhum livro disponível."}
          bodyText={"Adicione um livro"}
          iconButton={< AiOutlineFolderAdd className="button-icon" />}
          urlButton={"/add-livro"}
        />
      ) : (
        <table className="table">
          <thead>
            <tr>
              <th> <p>Id</p> </th>
              <th> <p>Titulo</p> </th>
              <th> <p>Autor</p> </th>
              <th> <p>Editora</p> </th>
              <th> <p>Capa</p> </th>
              <th> <p>Publicação</p> </th>
              <th> <p>Estoque</p> </th>
              <th> <p>Ações</p> </th>
            </tr>
          </thead>

          <tbody>
            {bookList.map((book) => (
              <tr key={book.id}>
                <td> <p>{book.id}</p></td>
                <td> <p>{book.titulo}</p> </td>
                <td> <p>{book.autor}</p> </td>
                <td> <p>{book.editora} </p> </td>
                <td> <p><img className="book-cover-image" src={book.linkImg} alt="CapaLivro" /> </p> </td>
                <td> <p>{formatDateToDdMonthYyyy(book.anoPublicacao)} </p> </td>
                <td> <p>{book.estoque} </p> </td>
                <td>
                  <div className="table-action">
                    <Link
                      className="table-action-icon edit"
                      to={`/edit-livro/${book.id}`} >
                      <BiEdit />
                    </Link>

                    <button
                      className="table-action-icon"
                      onClick={() => onDeleteBook(book.id)}>
                      <AiOutlineDelete />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )
      }

      <div className="table-img">
        <img
          src={livroImg} alt="Book Img" />
      </div>
    </>
  );
};

export default BookList;