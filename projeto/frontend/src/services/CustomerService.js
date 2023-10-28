import axios from 'axios';

const CUSTOMER_API_BASE_URL = process.env.REACT_APP_CLIENTE_API_BASE_URL ?? "http://localhost:8080/clientes";

class CustomerService {

    findAllCustomers() {
        return axios.get(CUSTOMER_API_BASE_URL);
    }

    findCustomerById(customerId) {
        return axios.get(
            CUSTOMER_API_BASE_URL
            + '/'
            + customerId
        );
    }

    insertCustomer(customerRequest) {
        return axios.post(
            CUSTOMER_API_BASE_URL,
            customerRequest
        );
    }


    updateCustomer(customerId, customerRequest) {
        return axios.put(
            CUSTOMER_API_BASE_URL
            + '/'
            + customerId,
            customerRequest
        );
    }

    deleteCustomer(customerId) {
        return axios.delete(
            CUSTOMER_API_BASE_URL
            + '/'
            + customerId
        );
    }

}

export default new CustomerService()
