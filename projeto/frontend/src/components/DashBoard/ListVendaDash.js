import React, { useState, useEffect } from "react";
import VendaService from "../../services/VendaService";
import "./styles.css"

function ListVendaDash() {
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

    function formatNumber(number) {
        return new Intl.NumberFormat('pt-BR',
            { style: 'currency', currency: 'BRL' }).format(number)
    }

    var list = vendas.slice(Math.max(vendas.length - 3, 1))

    return (
        <div className="table-vendas">
            <table className="table table-dark table-borderless">
                <thead className="table-light">
                    <tr>
                        <th> <p>Cliente </p> </th>
                        <th> <p>Livro </p> </th>
                        <th> <p>Quantidade</p> </th>
                        <th> <p>Preço </p> </th>
                        <th> <p>Total </p> </th>
                    </tr>
                </thead>
                <tbody>
                    {list.map((venda) => (
                        <tr key={venda.id}>
                            <td> <p className="p_td">{venda.client.name} </p> </td>
                            <td> <p className="p_td">{venda.livro.titulo} </p> </td>
                            <td> <p className="p_td">{venda.qtdItens} </p> </td>
                            <td> <p className="p_td">{formatNumber(venda.precoVenda)} </p> </td>
                            <td> <p className="p_td">{formatNumber(venda.total)} </p> </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}


export default ListVendaDash;