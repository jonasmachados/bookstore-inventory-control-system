import axios from 'axios';

const COMPRA_API_BASE_URL = process.env.REACT_COMPRA_API_BASE_URL ?? "http://localhost:8070/compra";

class CompraService {

    getAllCompras() {
        return axios.get(COMPRA_API_BASE_URL);
    }


    createCompra(compra) {
        return axios.post(COMPRA_API_BASE_URL, compra);
    }

    getCompraById(compraId) {
        return axios.get(COMPRA_API_BASE_URL + '/' + compraId);
    }

    updateCompra(compraId, compra) {
        return axios.put(COMPRA_API_BASE_URL + '/' + compraId, compra);
    }

    deleteCompra(compraId) {
        return axios.delete(COMPRA_API_BASE_URL + '/' + compraId);
    }

}

export default new CompraService()
