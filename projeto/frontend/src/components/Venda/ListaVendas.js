import React, { useState, useEffect } from "react";
import VendaService from "../../services/VendaService";
import "./Venda.css";
import vendaImg from "../../assets/img/venda.svg";
import { Link } from "react-router-dom";
import VendaToPDF from "../../report/cliente/VendaToPDF";

const ListaVendas = () => {
    const [vendas, setVendas] = useState([]);

    function formatNumber(number) {
        return new Intl.NumberFormat('pt-BR',
            { style: 'currency', currency: 'BRL' }).format(number)
    }

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

    const deleteVenda = (vendaId) => {
        VendaService.deleteVenda(vendaId).then((response) => {
            getAllVendas();
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div className="container-venda">
            <h1> Vendas </h1>
            <Link to="/add-venda" className="btn mb-4 btn-lg">
                {" "}
                Nova Venda{" "}
            </Link>
            <button onClick={(e) => VendaToPDF(vendas)} className="btn mb-4 btn-lg" >Print PDF</button>

            <table className="table">
                <thead>
                    <tr>
                        <th> <p>Id</p> </th>
                        <th> <p>Cliente </p> </th>
                        <th> <p>Livro </p> </th>
                        <th> <p>Quantidade</p> </th>
                        <th> <p>Preço </p> </th>
                        <th> <p>Total </p> </th>
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
                            <td> <p className="p_td">{formatNumber(venda.precoVenda)} </p> </td>
                            <td> <p className="p_td">{formatNumber(venda.total)} </p> </td>
                            <td>
                                <div className="div-acoes">
                                    <Link className="btn" to={`/edit-venda/${venda.id}`} >Atualizar</Link>
                                    <button className="btn" onClick={() => deleteVenda(venda.id)}
                                    >Deletar</button>
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
