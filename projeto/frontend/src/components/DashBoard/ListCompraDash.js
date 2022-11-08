import React, { useState, useEffect } from "react";
import CompraService from "../../services/CompraService";
import "./styles.css"

function ListCompraDash() {
    const [compras, setCompras] = useState([]);

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

    function formatNumber(number) {
        return new Intl.NumberFormat('pt-BR',
            { style: 'currency', currency: 'BRL' }).format(number)
    }

    var list = compras.slice(Math.max(compras.length - 3, 1))

    return (
        <div className="table-dash">
            <table className="table table-dark table-borderless">
                <thead className="table-light">
                    <tr>
                        <th> <p>Livro </p> </th>
                        <th> <p>Quantidade</p> </th>
                        <th> <p>Preço </p> </th>
                        <th> <p>Total </p> </th>
                    </tr>
                </thead>
                <tbody>
                    {list.map((compra) => (
                        <tr key={compra.id}>
                            <td> <p className="p_td">{compra.livro.titulo} </p> </td>
                            <td> <p className="p_td">{compra.qtdItens} </p> </td>
                            <td> <p className="p_td">{formatNumber(compra.precoVenda)} </p> </td>
                            <td> <p className="p_td">{formatNumber(compra.total)} </p> </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}


export default ListCompraDash;