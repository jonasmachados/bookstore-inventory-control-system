
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ClienteService from "../../services/ClienteService"
import clienteImg from "../../assets/img/client.svg";

const ListaClientes = () => {
    const [cliente, setCliente] = useState([]);

    useEffect(() => {
        getAllClientes();
    }, []);

    const getAllClientes = () => {
        ClienteService.getAllClientes()
            .then((response) => {
                setCliente(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const deleteCliente = (clienteId) => {
        ClienteService.deleteCliente(clienteId).then((response) => {
            getAllClientes();
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div className="container-livro">
            <h1> Clientes </h1>
            <Link to="/add-cliente" className="btn mb-4 btn-lg">
                {" "}
                Novo Cliente{" "}
            </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> <p>Id</p> </th>
                        <th> <p>Nome</p> </th>
                        <th> <p>Rua</p> </th>
                        <th> <p>Numero</p> </th>
                        <th> <p>Bairro</p> </th>
                        <th> <p>Cidade</p> </th>
                        <th> <p>Estado</p> </th>
                        <th> <p>Cep</p> </th>
                        <th> <p>Ações</p> </th>
                    </tr>
                </thead>
                <tbody>
                    {cliente.map((cliente) => (
                        <tr key={cliente.id}>
                            <td> <p className="p_td">{cliente.id}</p></td>
                            <td> <p className="p_td">{cliente.name}</p> </td>
                            <td> <p className="p_td">{cliente.rua}</p> </td>
                            <td> <p className="p_td">{cliente.numero} </p> </td>
                            <td> <p className="p_td">{cliente.bairro} </p> </td>
                            <td> <p className="p_td">{cliente.cidade} </p> </td>
                            <td> <p className="p_td">{cliente.estado} </p> </td>
                            <td> <p className="p_td">{cliente.cep} </p> </td>
                            <td>
                                <div className="div-acoes">

                                    <Link className="btn" to={`/edit-cliente/pf/${cliente.id}`} >Atualizar</Link>

                                    <button className="btn" onClick={() => deleteCliente(cliente.id)}
                                    >Deletar</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <img src={clienteImg} alt="clienteImg Img" />
        </div>
    );
};

export default ListaClientes;

