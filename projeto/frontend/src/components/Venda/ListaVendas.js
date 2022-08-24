import React, { useState, useEffect } from "react";
import VendaService from "../../services/VendaService";
import "./Venda.css";
import vendaImg from "../../assets/img/venda.svg";


const ListaVendas = () => {
    const [vendas, setVendas] = useState([]);

    useEffect(() => {
        getAllVendas();
    }, []);

    const getAllVendas = () => {
        VendaService.getAllVendas()
            .then((response) => {
                setVendas(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="container-venda">
            <h1> Vendas </h1>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> <p>Id</p> </th>
                        <th> <p>Cliente </p> </th>
                        <th> <p>Livro </p> </th>
                        <th> <p>Quantidade</p> </th>
                        <th> <p>Preço </p> </th>
                        <th> <p>Ações</p> </th>
                    </tr>
                </thead>
                <tbody>
                    {vendas.map((venda) => (
                        <tr key={venda.id}>
                            <td> <p className="p_td">{venda.id}</p></td>
                            <td> <p className="p_td">{venda.client.name} </p> </td>
                            <td> <p className="p_td">{venda.livro.titulo} </p> </td>
                            <td> <p className="p_td">{venda.qtdItens} </p> </td>
                            <td> <p className="p_td">{venda.precoVenda} </p> </td>
                            <td>
                                <div>

                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <img src={vendaImg} alt="compraImg Img" />
        </div>
    );
};

export default ListaVendas;
