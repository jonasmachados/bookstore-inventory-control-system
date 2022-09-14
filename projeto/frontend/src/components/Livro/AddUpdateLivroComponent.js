import React, { useState, useEffect } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import LivroService from '../../services/LivroService.js';
import "./AddUpdateLivroComponent.css";
import * as yup from 'yup';
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

const AddUpdateLivroComponent = () => {

    const [titulo, setTitulo] = useState('')
    const [autor, setAutor] = useState('')
    const [editora, setEditora] = useState('')
    const [linkImg, setLinkImg] = useState('')
    const [anoPublicacao, setAnoPublicacao] = useState('')
    const [estoque, setEstoque] = useState('')
    const history = useNavigate();
    const { id } = useParams();

    const livro = { titulo, autor, editora, linkImg, anoPublicacao, estoque }

    const [status, setStatus] = useState({
        type: '',
        mensagem: ''
    })

    let schema = yup.object().shape({
        titulo: yup.string().required(),
        autor: yup.string().required(),
        editora: yup.string().required(),
        linkImg: yup.string().required(),
        estoque: yup.number().required().integer()
    });

    async function validate() {
        let schema = yup.object().shape({
            estoque: yup.number()
                .typeError('Erro: Necessario preencher a quantidade do estoque!')
                .required("Erro: O campo é obrigatório.")
                .integer("Erro: Estoque deve possuir um número inteiro."),
            linkImg: yup.string("Erro: Necessario preencher o campo imagem!")
                .required("Erro: Necessario preencher o campo imagem!"),
            editora: yup.string("Erro: Necessario preencher o campo editora!")
                .required("Erro: Necessario preencher o campo editora!")
                .min(3, "Erro: Editora deve possuir mais que 3 caracteres ")
                .max(50, "Erro: Editora passou de 50 caracteres!"),
            autor: yup.string("Erro: Necessario preencher o campo autor!")
                .required("Erro: Necessario preencher o campo autor!")
                .min(3, "Erro: Autor deve possuir mais que 3 caracteres ")
                .max(50, "Erro: Autor passou de 50 caracteres!"),
            titulo: yup.string("Erro: Necessario preencher o campo titulo!")
                .required("Erro: Necessario preencher o campo titulo!")
                .min(3, "Erro: Titulo deve possuir mais que 3 caracteres ")
                .max(50, "Erro: Titulo passou de 50 caracteres!")
        });

        try {
            await schema.validate(livro)
            return true;
        } catch (err) {
            setStatus({
                type: 'error',
                mensagem: err.errors
            });
            return false;
        }
    }

    const dateFormatAux = (date) => {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [day, month, year].join('/');
    }

    const dateFormat = (date) => {

        let formatYearMonthDay = dateFormatAux(date);

        return formatYearMonthDay;
    }

    const saveOrUpdateLivro = async (e) => {
        e.preventDefault();

        livro.anoPublicacao = dateFormat(anoPublicacao)

        if (!(await validate())) return;

        if (id) {
            LivroService.updateLivro(id, livro).then((response) => {
                window.location.href = "/";
            }).catch(error => {
                console.log(error)
            })

        } else {
            LivroService.createLivro(livro).then((response) => {

                console.log(response.data)

                window.location.href = "/";

            }).catch(error => {
                console.log(error)
            })
        }

    }

    useEffect(() => {

        LivroService.getLivroById(id).then((response) => {
            setTitulo(response.data.titulo)
            setAutor(response.data.autor)
            setEditora(response.data.editora)
            setLinkImg(response.data.linkImg)
            setAnoPublicacao(response.data.anoPublicacao)
            setEstoque(response.data.estoque)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {

        if (id) {
            return <h1 className="text-center">Atualizar Livro</h1>
        } else {
            return <h1 className="text-center">Adicionar Livro</h1>
        }
    }

    return (
        <div>
            <br /><br />
            <div className="container-add">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        {
                            title()
                        }

                        {status.type === 'error' ? <p style={{ color: "#ff0000", textAlign: 'center' }}> {status.mensagem}</p> : ""}

                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="Titulo"
                                        name="titulo"
                                        className="form-control"
                                        value={titulo}
                                        onChange={(e) => setTitulo(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="Autor"
                                        name="autor"
                                        className="form-control"
                                        value={autor}
                                        onChange={(e) => setAutor(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="editora"
                                        placeholder="Editora"
                                        name="editora"
                                        className="form-control"
                                        value={editora}
                                        onChange={(e) => setEditora(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="linkImg"
                                        placeholder="LinkImg"
                                        name="linkImg"
                                        className="form-control"
                                        value={linkImg}
                                        onChange={(e) => setLinkImg(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <DatePicker
                                        selected={anoPublicacao}
                                        onChange={date => setAnoPublicacao(date)}
                                        dateFormat="dd/MM/yyyy"
                                        placeholderText="Ano de Publicacao"
                                        name="anoPublicacao"
                                        id="anoPublicacao"
                                        className="form-control"
                                    />
                                </div>


                                <div className="form-group mb-2">
                                    <input
                                        type="estoque"
                                        placeholder="Estoque"
                                        name="estoque"
                                        className="form-control"
                                        value={estoque}
                                        onChange={(e) => setEstoque(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <button className="btn" onClick={(e) => saveOrUpdateLivro(e)} >Salvar </button>
                                <Link to="/" className="btn"> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    )
}

export default AddUpdateLivroComponent;