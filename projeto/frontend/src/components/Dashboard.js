import { BsPeople } from 'react-icons/bs'
import { GoBook } from 'react-icons/go'
import { MdPointOfSale } from 'react-icons/md'
import { AiOutlineShoppingCart, AiOutlineDashboard } from 'react-icons/ai'
import { convertNumberToCurrency } from '../utils/convertNumberToCurrency';
import "../styles/dashboard.css"
import TitleWithIcon from './TitleWithIcon';

const Dashboard = ({ customerList,
    bookList,
    purchaseList,
    saleList }) => {

    var totalPurchase = 0;
    for (var i = 0; i < purchaseList.length; i++) {
        totalPurchase = totalPurchase + purchaseList[i].total;
    }

    var totalSales = 0;
    for (i = 0; i < saleList.length; i++) {
        totalSales = totalSales + saleList[i].total;
    }

    return (
        <div className="dashboard-container">

            <TitleWithIcon
                icon={<AiOutlineDashboard />}
                title="Dashboard"
            />

            <div className="dashboard-boxes">
                <div className="dashboard-box box1">
                    <i><BsPeople /></i>
                    <span className="dashboard-text">Clientes</span>
                    <span className="dashboard-number">{customerList.length}</span>
                </div>

                <div className="dashboard-box box2">
                    <i><GoBook /></i>
                    <span className="dashboard-text">Livros</span>
                    <span className="dashboard-number">{bookList.length}</span>
                </div>

                <div className="dashboard-box sale box3">
                    <i><MdPointOfSale /></i>
                    <span className="dashboard-text sale">Total Vendas</span>
                    <span className="dashboard-number sale">{convertNumberToCurrency(totalSales)}</span>
                </div>

                <div className="dashboard-box box4">
                    <i><AiOutlineShoppingCart /></i>
                    <span className="dashboard-text">Total Compra</span>
                    <span className="dashboard-number">{convertNumberToCurrency(totalPurchase)}</span>
                </div>
            </div>

        </div>
    );
};

export default Dashboard;