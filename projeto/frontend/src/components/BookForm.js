import React, { useState, useEffect } from 'react'
import LivroService from '../services/LivroService';
import { Link } from 'react-router-dom';
import DatePicker from "react-datepicker";
import formatDateToDdMmYyyy from '../utils/formatDateToDdMmYyyy';
import "../styles/form.css"
import "../styles/button.css"
import { AiOutlineFolderAdd } from 'react-icons/ai'
import { BiEdit } from 'react-icons/bi'
import TitleWithIcon from './TitleWithIcon';
import { parse } from 'date-fns';
import { bookRegistrationSchema, validateForm, renderValidationMessage, clearError } from '../utils/validationSchemas';
import "../styles/validation-errors.css"
import 'react-datepicker/dist/react-datepicker.css';
import ptBR from 'date-fns/locale/pt-BR';

const BookForm = ({ id, book }) => {

    const [titulo, setTitulo] = useState(book?.titulo || '');
    const [autor, setAutor] = useState(book?.autor || '');
    const [editora, setEditora] = useState(book?.editora || '');
    const [linkImg, setLinkImg] = useState(book?.linkImg || '');
    const [anoPublicacao, setAnoPublicacao] = useState('')
    const [estoque, setEstoque] = useState(book?.estoque || '');
    const [validationErrors, setValidationErrors] = useState({});

    useEffect(() => {
        if (book) {
            setTitulo(book.titulo);
            setAutor(book.autor);
            setEditora(book.editora);
            setLinkImg(book.linkImg);
            setAnoPublicacao(parse(
                book.anoPublicacao,
                'dd/MM/yyyy',
                new Date()));
            setEstoque(book.estoque);
        }
    }, [book]);

    const livro = {
        titulo,
        autor,
        editora,
        linkImg,
        anoPublicacao,
        estoque
    }

    const saveOrUpdateBook = async (e) => {

        e.preventDefault();

        const errors = await validateForm(livro, bookRegistrationSchema);

        setValidationErrors(errors);

        livro.anoPublicacao = formatDateToDdMmYyyy(anoPublicacao)

        if (id) {
            LivroService.updateLivro(id, livro).then((response) => {
                window.location.href = "/livros";
            }).catch(error => {
                console.log(error)
            })

        } else {
            LivroService.createLivro(livro).then((response) => {
                window.location.href = "/livros";
            }).catch(error => {
                console.log(error)
            })
        }

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
                        value={titulo}
                        onChange={(e) => {
                            setTitulo(e.target.value);
                            clearError(validationErrors, setValidationErrors, 'titulo');
                        }}
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
                        value={autor}
                        onChange={(e) => {
                            setAutor(e.target.value)
                            clearError(validationErrors, setValidationErrors, 'autor');
                        }}
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
                        value={editora}
                        onChange={(e) => {
                            setEditora(e.target.value)
                            clearError(validationErrors, setValidationErrors, 'editora');
                        }}
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
                        value={linkImg}
                        onChange={(e) => {
                            setLinkImg(e.target.value)
                            clearError(validationErrors, setValidationErrors, 'linkImg');
                        }}
                    >
                    </input>
                    {renderValidationMessage(validationErrors, 'linkImg')}
                </div>

                <div className="form-in-column">
                    <div className="form-input">
                        <label>Data Edição</label>
                        <DatePicker
                            controls={['anoPublicacao']}
                            selected={anoPublicacao}
                            dateFormat="dd/MM/yyyy"
                            locale={ptBR}
                            placeholderText="Selecione a data."
                            name="anoPublicacao"
                            id="anoPublicacao"
                            value={anoPublicacao}
                            onChange={date => {
                                setAnoPublicacao(date)
                                clearError(validationErrors, setValidationErrors, 'anoPublicacao');
                            }}
                        />
                        {renderValidationMessage(validationErrors, 'anoPublicacao')}
                    </div>

                    <div className="form-input">
                        <label>Estoque</label>
                        <input
                            type="text"
                            placeholder="Digite o estoque"
                            name="estoque"
                            value={estoque}
                            onChange={(e) => {
                                setEstoque(e.target.value)
                                clearError(validationErrors, setValidationErrors, 'estoque');
                            }}
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