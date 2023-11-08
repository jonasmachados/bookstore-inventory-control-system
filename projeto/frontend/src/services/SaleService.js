import axios from 'axios';

const SALE_API_BASE_URL = process.env.REACT_APP_SALE_API_BASE_URL
    ?? "http://localhost:8080/vendas";

class SaleService {

    findAllSales() {
        return axios.get(SALE_API_BASE_URL);
    }

    findSaleById(saleId) {
        return axios.get(
            SALE_API_BASE_URL
            + '/'
            + saleId);
    }

    insertSale(sale) {
        return axios.post(
            SALE_API_BASE_URL,
            sale);
    }

    updateSale(saleId, sale) {
        return axios.put(
            SALE_API_BASE_URL
            + '/'
            + saleId,
            sale);
    }

    deleteSale(saleId) {
        return axios.delete(
            SALE_API_BASE_URL
            + '/'
            + saleId);
    }

}

export default new SaleService()
