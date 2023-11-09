import React from "react";
import { Link } from "react-router-dom"
import { AiOutlinePrinter, AiOutlineFolderAdd, AiOutlineDelete } from 'react-icons/ai';
import { BiEdit } from 'react-icons/bi';
import { IoIosPeople } from 'react-icons/io';
import TitleWithIcon from "./TitleWithIcon";
import CustomerImg from "../assets/img/client.svg";
import CustomertoPdf from "../report/CustomerToPdf";
import "../styles/button.css";
import "../styles/table.css";

const CustomerList = ({ customerList, onDeleteCustomer }) => {

    return (
        <>
            <div className="table-title">
                <TitleWithIcon
                    icon={<IoIosPeople />}
                    title="Clientes"
                />
            </div>

            <div className="button-container">
                <Link to="/add-cliente" className="button primary-button">
                    <AiOutlineFolderAdd className="button-icon" />
                    Novo
                </Link>

                <button
                    onClick={(e) => CustomertoPdf(customerList)}
                    className="button secondary-button" >
                    <AiOutlinePrinter className="button-icon" />
                    Print
                </button>
            </div>

            <table className="table">
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
                    {customerList.map((customer) => (
                        <tr key={customer.id}>
                            <td> <p className="p_td">{customer.id}</p></td>
                            <td> <p className="p_td">{customer.nome}</p> </td>
                            <td> <p className="p_td">{customer.rua}</p> </td>
                            <td> <p className="p_td">{customer.numero} </p> </td>
                            <td> <p className="p_td">{customer.bairro} </p> </td>
                            <td> <p className="p_td">{customer.cidade} </p> </td>
                            <td> <p className="p_td">{customer.estado} </p> </td>
                            <td> <p className="p_td">{customer.cep} </p> </td>
                            <td>
                                <div className="table-action">

                                    <Link
                                        className="table-action-icon edit"
                                        to={`/edit-cliente/${customer.id}`} >
                                        <BiEdit />
                                    </Link>

                                    <button
                                        className="table-action-icon"
                                        onClick={() => onDeleteCustomer(customer.id)}>
                                        <AiOutlineDelete />
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="table-img">
                <img
                    src={CustomerImg}
                    alt="Customer Img"
                />
            </div>
        </>
    );
};

export default CustomerList;