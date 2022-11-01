import axios from 'axios';

const CLIENTE_API_BASE_URL = "https://backend-livraria-jonas.herokuapp.com/clientes";

class ClienteService{

    getAllClientes(){
        return axios.get(CLIENTE_API_BASE_URL);
    }

    
    createClientePf(cliente){
        return axios.post(CLIENTE_API_BASE_URL+ '/pf', cliente);
    }

    createClientePj(cliente){
        return axios.post(CLIENTE_API_BASE_URL+ '/pj', cliente);
    }

    getClienteById(clienteId){
        return axios.get(CLIENTE_API_BASE_URL + '/' + clienteId);
    }

    updateCliente(clienteId, cliente){ 
        return axios.put(CLIENTE_API_BASE_URL + '/' +clienteId, cliente);
    }

    deleteCliente(clienteId){
        return axios.delete(CLIENTE_API_BASE_URL + '/' + clienteId);
    }
    
}

export default new ClienteService()
