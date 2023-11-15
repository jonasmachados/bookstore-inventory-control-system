import { AiOutlineShoppingCart } from 'react-icons/ai'
import "../styles/table.css"
import "../styles/component.css"
import { convertNumberToCurrency } from "../utils/convertNumberToCurrency";
import TitleWithIcon from './TitleWithIcon';

const RecentPurchases = ({ purchaseList }) => {

    return (
        <div className="container-component">

            <TitleWithIcon
                icon={<AiOutlineShoppingCart />}
                title="Compras Recentes"
            />

            <table className="table">
                <thead>
                    <tr>
                        <th> <p>Livro </p> </th>
                        <th> <p>Quantidade</p> </th>
                        <th> <p>Preço </p> </th>
                        <th> <p>Total </p> </th>
                    </tr>
                </thead>

                <tbody>
                    {purchaseList.slice(-4).map((compra) => (
                        <tr key={compra.id}>
                            <td> <p>{compra.livro.titulo} </p> </td>
                            <td> <p>{compra.qtdItens} </p> </td>
                            <td> <p>{convertNumberToCurrency(compra.precoVenda)} </p> </td>
                            <td> <p>{convertNumberToCurrency(compra.total)} </p> </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </div>
    );
};

export default RecentPurchases;