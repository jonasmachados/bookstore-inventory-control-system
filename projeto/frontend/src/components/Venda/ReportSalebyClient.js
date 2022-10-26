/*eslint-disable */
import React, { useState, useEffect } from 'react'
import ClienteService from '../../services/ClienteService';
import VendaService from "../../services/VendaService";
import VendaByCliente from '../../report/cliente/VendaByCliente';
import "./Venda.css";
import reportImg from "../../assets/img/report.svg";

const ReportSalebyClient = () => {

    const [listaCliente, setlistaCliente] = useState([]);
    const [Listavendas, setListavendas] = useState([]);
    const [client, setClient] = useState('')

    useEffect(() => {
        getAllClientes();
        getVendaByClient();
    }, []);


    const getAllClientes = () => {
        ClienteService.getAllClientes()
            .then((response) => {
                setlistaCliente(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getVendaByClient = () => {
        VendaService.getVendaByClient(client)
            .then((response) => {
                setListavendas(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div>
            <br /><br />
            <div className="container-add">
                <h1> Relatorio de vendas por cliente. </h1>
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <label className="mb-2">Selecione o cliente:</label>
                                    <select name="client" className="form-control" onChange={(e) => setClient(e.target.value)}>
                                        <option>--Selecione o Cliente--</option>
                                        {
                                            listaCliente.map((client) => (
                                                <option key={client.id} value={client.id}> {client.name}</option>
                                            ))
                                        }
                                    </select>
                                </div>
                            </form>
                            <button onClick={(e) => VendaByCliente(Listavendas)} className="btn mb-4 btn-lg" >Print PDF</button>
                        </div>
                    </div>
                </div>
                <img src={reportImg} alt="reportImg Img" />
            </div>
        </div>
    )
}


export default ReportSalebyClient;