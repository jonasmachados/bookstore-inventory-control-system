import axios from 'axios';

const VENDA_API_BASE_URL = "https://backend-livraria-jonas.herokuapp.com/vendas";

class VendaService {

    getAllVendas() {
        return axios.get(VENDA_API_BASE_URL);
    }

    createVenda(venda) {
        return axios.post(VENDA_API_BASE_URL + '/add', venda);
    }

    getVendaById(vendaId) {
        return axios.get(VENDA_API_BASE_URL + '/' + vendaId);
    }

    getVendaByClient(idCliente) {
        return axios.get(VENDA_API_BASE_URL + '/cliente/' + idCliente);
    }

    updateVenda(vendaId, venda) {
        return axios.put(VENDA_API_BASE_URL + '/' + vendaId, venda);
    }

    deleteVenda(vendaId) {
        return axios.delete(VENDA_API_BASE_URL + '/' + vendaId);
    }

}

export default new VendaService()
