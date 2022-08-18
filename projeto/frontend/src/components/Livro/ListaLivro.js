import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import LivroService from "../../services/LivroService"
import "./Livro.css";

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

    return (
        <div className="tables">
            <h1> Livros </h1>
            <Link to="/add-employee" className="btn btn-primary mb-2">
                {" "}
                Novo Livro{" "}
            </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> <p>Id</p> </th>
                        <th> <p>Titulo</p> </th>
                        <th> <p>Autor</p> </th>
                        <th> <p>Editora</p> </th>
                        <th> <p>Ano Publicacao</p> </th>
                        <th> <p>Estoque</p> </th>
                    </tr>
                </thead>
                <tbody>
                    {livros.map((livro) => (
                        <tr key={livro.id}>
                            <td> <p className="p_td">{livro.id}</p></td>
                            <td> <p className="p_td">{livro.titulo}</p> </td>
                            <td> <p className="p_td">{livro.autor}</p> </td>
                            <td> <p className="p_td">{livro.editora} </p> </td>
                            <td> <p className="p_td">{livro.anoPublicacao} </p> </td>
                            <td> <p className="p_td">{livro.estoque} </p> </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListaLivro;
