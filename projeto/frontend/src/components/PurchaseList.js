import { Link } from "react-router-dom";
import { AiOutlinePrinter, AiOutlineFolderAdd, AiOutlineDelete } from 'react-icons/ai';
import { BiEdit } from 'react-icons/bi';
import { BsFillCartCheckFill } from 'react-icons/bs';
import TitleWithIcon from "./TitleWithIcon";
import livroToPDF from "../report/cliente/LivroToPDF";
import PurchaseImg from "../assets/img/compra.svg";
import "../styles/button.css";
import "../styles/table.css";
import { convertNumberToCurrency } from "../utils/convertNumberToCurrency";

const PurchaseList = ({ purchaseList, onDeletePurchase }) => {

    return (
        <>
            <div className="table-title">
                <TitleWithIcon
                    icon={<BsFillCartCheckFill />}
                    title="Compras"
                />
            </div>

            <div className="button-container">
                <Link
                    to="/add-compra"
                    className="button primary-button"
                >
                    <AiOutlineFolderAdd className="button-icon" />
                    Novo
                </Link>

                <button
                    onClick={(e) => livroToPDF()}
                    className="button secondary-button"
                >
                    <AiOutlinePrinter className="button-icon" />
                    Print
                </button>
            </div>

            <table className="table">
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
                    {purchaseList.map((purchase) => (
                        <tr key={purchase.id}>
                            <td> <p>{purchase.id}</p></td>
                            <td> <p>{purchase.livro.titulo}</p> </td>
                            <td> <p>{purchase.qtdItens}</p> </td>
                            <td> <p>{convertNumberToCurrency(purchase.precoVenda)} </p> </td>
                            <td> <p>{convertNumberToCurrency(purchase.total)} </p> </td>
                            <td>
                                <div className="table-action">
                                    <Link
                                        className="table-action-icon edit"
                                        to={`/edit-compra/${purchase.id}`} >
                                        <BiEdit />
                                    </Link>

                                    <button
                                        className="table-action-icon"
                                        onClick={() => onDeletePurchase(purchase.id)}>
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
                    src={PurchaseImg} alt="Purchase Img" />
            </div>
        </>
    );
};

export default PurchaseList;