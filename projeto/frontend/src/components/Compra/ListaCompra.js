
import React, { useState, useEffect } from "react";
import compraImg from "../../assets/img/compra.svg";
import CompraService from "../../services/CompraService";
import { Link } from "react-router-dom";

import "./Compra.css";

const ListaCompra = () => {

    const [compras, setCompras] = useState([]);

    function formatNumber(number) {
        return new Intl.NumberFormat('pt-BR',
        { style: 'currency', currency: 'BRL' }).format(number)
    }

    useEffect(() => {
        getAllCompras();
    }, []);

    const getAllCompras = () => {
        CompraService.getAllCompras()
            .then((response) => {
                setCompras(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const deleteCompra = (compraId) => {
        CompraService.deleteCompra(compraId).then((response) => {
            getAllCompras();
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div className="container-compra">
            <h1> Compras </h1>
            <Link to="/add-compra" className="btn mb-4 btn-lg">
                {" "}
                Novo Compra{" "}
            </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> <p>Id</p> </th>
                        <th> <p>Livro </p> </th>
                        <th> <p>Quantidade</p> </th>
                        <th> <p>Preço </p> </th>
                        <th> <p>Total </p> </th>
                        <th> <p>Ações</p> </th>
                    </tr>
                </thead>
                <tbody>
                    {compras.map((compra) => (
                        <tr key={compra.id}>
                            <td> <p className="p_td">{compra.id}</p></td>
                            <td> <p className="p_td">{compra.livro.titulo} </p> </td>
                            <td> <p className="p_td">{compra.qtdItens} </p> </td>
                            <td> <p className="p_td">{formatNumber(compra.precoVenda)} </p> </td>
                            <td> <p className="p_td">{formatNumber(compra.total)} </p> </td>
                            <td>
                                <div className="div-acoes">
                                    <Link className="btn" to={`/edit-compra/${compra.id}`} >Atualizar</Link>
                                    <button className="btn" onClick={() => deleteCompra(compra.id)}
                                    >Deletar</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <img src={compraImg} alt="compraImg Img" />
        </div>
    )
}

export default ListaCompra;