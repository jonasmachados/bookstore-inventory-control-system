import { convertNumberToCurrency } from "../utils/convertNumberToCurrency";
import TitleWithIcon from "./TitleWithIcon";
import { MdPointOfSale } from 'react-icons/md'
import "../styles/table.css"

const RecentSales = ({ saleList }) => {
  return (
    <>
      <TitleWithIcon
        icon={<MdPointOfSale />}
        title="Vendas Recentes"
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
          {saleList.slice(-4).map((compra) => (
            <tr key={compra.id}>
              <td> <p>{compra.livro.titulo} </p> </td>
              <td> <p>{compra.qtdItens} </p> </td>
              <td> <p>{convertNumberToCurrency(compra.precoVenda)} </p> </td>
              <td> <p>{convertNumberToCurrency(compra.total)} </p> </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default RecentSales;