import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import { parse } from 'date-fns';
import ptBR from 'date-fns/locale/pt-BR';
import { AiOutlineFolderAdd } from 'react-icons/ai';
import { BiEdit } from 'react-icons/bi';
import BookService from '../services/BookService';
import formatDateToDdMmYyyy from '../utils/formatDateToDdMmYyyy';
import TitleWithIcon from './TitleWithIcon';
import { bookRegistrationSchema, validateForm, renderValidationMessage, clearError } from '../utils/validationSchemas';
import '../styles/form.css';
import '../styles/button.css';
import '../styles/validation-errors.css';
import 'react-datepicker/dist/react-datepicker.css';

const BookForm = ({ id, book }) => {

    const [bookForm, setBookForm] = useState({
        titulo: '',
        autor: '',
        editora: '',
        linkImg: '',
        anoPublicacao: '',
        estoque: ''
    });

    const [validationErrors, setValidationErrors] = useState({});

    useEffect(() => {
        if (book) {
            setBookForm(prevBookForm => ({
                ...prevBookForm,
                titulo: book.titulo,
                autor: book.autor,
                editora: book.editora,
                linkImg: book.linkImg,
                anoPublicacao: (parse(
                    book.anoPublicacao,
                    'dd/MM/yyyy',
                    new Date())),
                estoque: book.estoque || 0,
            }));
        }
    }, [book]);


    const handleDateChange = (date) => {
        setBookForm({
            ...bookForm,
            anoPublicacao: date,
        });

        clearError(validationErrors, setValidationErrors, 'anoPublicacao');
    };

    const handleChange = (e) => {
        const { name, value } = e.target;

        setBookForm({
            ...bookForm,
            [name]: value,
        });

        clearError(validationErrors, setValidationErrors, [name]);
    };

    const saveOrUpdateBook = async (e) => {
        e.preventDefault();

        const errors = await
            validateForm(bookForm, bookRegistrationSchema);

        setValidationErrors(errors);

        const saleData = {
            titulo: bookForm.titulo,
            autor: bookForm.autor,
            editora: bookForm.editora,
            linkImg: bookForm.linkImg,
            anoPublicacao: formatDateToDdMmYyyy(bookForm.anoPublicacao),
            estoque: bookForm.estoque,
        };

        if (Object.keys(errors).length === 0) {
            try {
                if (id) {
                    await BookService.updateBook(id, saleData);
                    window.location.href = '/livros';
                } else {
                    await BookService.insertBook(saleData);
                    window.location.href = '/livros';
                }
            } catch (error) {
                console.error(error);
            }
        };
    }

    return (
        <div className="form-container">

            <div className="form-title">
                <TitleWithIcon
                    icon={id ? <BiEdit /> : <AiOutlineFolderAdd />}
                    title={id ? "Atualizar Livro" : "Adicionar Livro"}
                />
            </div>

            <form className='form'>
                <div className="form-input">
                    <label>Titulo</label>
                    <input
                        type="text"
                        placeholder="Digite o titulo"
                        name="titulo"
                        value={bookForm.titulo}
                        onChange={handleChange}
                    >
                    </input>
                    {renderValidationMessage(validationErrors, 'titulo')}
                </div>

                <div className="form-input">
                    <label>Autor</label>
                    <input
                        type="text"
                        placeholder="Digite o autor"
                        name="autor"
                        value={bookForm.autor}
                        onChange={handleChange}
                    >
                    </input>
                    {renderValidationMessage(validationErrors, 'autor')}
                </div>

                <div className="form-input">
                    <label>Editora</label>
                    <input
                        type="text"
                        placeholder="Digite a editora"
                        name="editora"
                        value={bookForm.editora}
                        onChange={handleChange}
                    >
                    </input>
                    {renderValidationMessage(validationErrors, 'editora')}
                </div>

                <div className="form-input">
                    <label>Imagem</label>
                    <input
                        type="text"
                        placeholder="Digite o link da imagem"
                        name="linkImg"
                        value={bookForm.linkImg}
                        onChange={handleChange}
                    >
                    </input>
                    {renderValidationMessage(validationErrors, 'linkImg')}
                </div>

                <div className="form-in-column">
                    <div className="form-input">
                        <label>Data Edição</label>
                        <DatePicker
                            selected={bookForm.anoPublicacao}
                            onChange={handleDateChange}
                            placeholderText="Selecione a data."
                            dateFormat="dd/MM/yyyy"
                            locale={ptBR}
                            name="anoPublicacao"
                            id="anoPublicacao"
                        />
                        {renderValidationMessage(validationErrors, 'anoPublicacao')}
                    </div>

                    <div className="form-input">
                        <label>Estoque</label>
                        <input
                            type="text"
                            placeholder="Digite o estoque"
                            name="estoque"
                            value={bookForm.estoque}
                            onChange={handleChange}
                        >
                        </input>
                        {renderValidationMessage(validationErrors, 'estoque')}
                    </div>
                </div>

                <div className='button-container'>
                    <button
                        className="button primary-button"
                        onClick={(e) => saveOrUpdateBook(e)} >
                        Salvar
                    </button>

                    <Link
                        to="/livros"
                        className="button secondary-button ">
                        Cancelar
                    </Link>
                </div>

            </form>
        </div>
    );

};

export default BookForm;