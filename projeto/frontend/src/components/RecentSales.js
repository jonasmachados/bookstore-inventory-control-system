import { convertNumberToCurrency } from "../utils/convertNumberToCurrency";
import TitleWithIcon from "./TitleWithIcon";
import { MdPointOfSale } from 'react-icons/md'
import { AiOutlineFolderAdd } from 'react-icons/ai'
import "../styles/table.css"
import "../styles/component.css"
import NoDataMessage from "./NoDataMessage";

const RecentSales = ({ saleList }) => {

  const isEmpty = saleList.length === 0;

  return (
    <div className="container-component">

      <TitleWithIcon
        icon={<MdPointOfSale />}
        title="Vendas Recentes"
      />

      {isEmpty ? (
        <NoDataMessage
          header={"Oops, nenhuma venda disponível."}
          bodyText={"Adicione uma venda"}
          iconButton={< AiOutlineFolderAdd className="button-icon" />}
          urlButton={"/add-venda"}
        />
      ) : (
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
      )
      }

    </div>
  );

};

export default RecentSales;