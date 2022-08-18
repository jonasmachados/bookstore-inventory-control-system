import React, { useState, useEffect } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import LivroService from '../../services/LivroService.js';
import "./AddUpdateLivroComponent.css";

const AddUpdateLivroComponent = () => {

    const [titulo, setTitulo] = useState('')
    const [autor, setAutor] = useState('')
    const [editora, setEditora] = useState('')
    const [linkImg, setLinkImg] = useState('')
    const [anoPublicacao, setAnoPublicacao] = useState('')
    const [estoque, setEstoque] = useState('')
    const history = useNavigate();
    const { id } = useParams();

    const saveOrUpdateLivro = (e) => {
        e.preventDefault();

        const livro = { titulo, autor, editora, linkImg, anoPublicacao, estoque}

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
                                    <input
                                        type="anoPublicacao"
                                        placeholder="Ano de Publicacao"
                                        name="anoPublicacao"
                                        className="form-control"
                                        value={anoPublicacao}
                                        onChange={(e) => setAnoPublicacao(e.target.value)}
                                    >
                                    </input>
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