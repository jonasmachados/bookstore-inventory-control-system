import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import LivroService from "../../services/LivroService"
import "./ListaLivro.css";
import livroImg from "../../assets/img/livro.svg";
import livroToPDF from "../../report/cliente/LivroToPDF";

const ListaLivro = () => {
    const [livros, setlivros] = useState([]);

    useEffect(() => {
        getAllLivros();
    }, []);

    const getAllLivros = () => {
        LivroService.getAllLivros()
            .then((response) => {
                setlivros(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const deleteLivro = (livroId) => {
        LivroService.deleteLivro(livroId).then((response) => {
            getAllLivros();
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div className="container-livro">
            <h1> Livros </h1>
            <Link to="/add-livro" className="btn mb-4 btn-lg">
                {" "}
                Novo Livro{" "}
            </Link>
            <button onClick={(e) => livroToPDF(livros)} className="btn mb-4 btn-lg" >Print PDF</button>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> <p>Id</p> </th>
                        <th> <p>Titulo</p> </th>
                        <th> <p>Autor</p> </th>
                        <th> <p>Editora</p> </th>
                        <th> <p>Capa livro</p> </th>
                        <th> <p>Ano Publicacao</p> </th>
                        <th> <p>Estoque</p> </th>
                        <th> <p>Ações</p> </th>
                    </tr>
                </thead>
                <tbody>
                    {livros.map((livro) => (
                        <tr key={livro.id}>
                            <td> <p className="p_td">{livro.id}</p></td>
                            <td> <p className="p_td">{livro.titulo}</p> </td>
                            <td> <p className="p_td">{livro.autor}</p> </td>
                            <td> <p className="p_td">{livro.editora} </p> </td>
                            <td> <p className="p_td"><img className="img-capa-livro" src={livro.linkImg} alt="CapaLivro" /> </p> </td>
                            <td> <p className="p_td">{livro.anoPublicacao} </p> </td>
                            <td> <p className="p_td">{livro.estoque} </p> </td>
                            <td>
                                <div className="div-acoes">
                                    <Link className="btn" to={`/edit-livro/${livro.id}`} >Atualizar</Link>
                                    <button className="btn" onClick={() => deleteLivro(livro.id)}
                                    >Deletar</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <img src={livroImg} alt="livroImg Img" />
        </div>
    );
};

export default ListaLivro;
