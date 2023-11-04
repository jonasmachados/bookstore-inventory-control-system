import axios from 'axios';

const PURCHASE_API_BASE_URL = process.env.REACT_APP_PURCHASE_API_BASE_URL
    ?? "http://localhost:8080/compra";

class PurchaseService {

    findAllPurchase() {
        return axios.get(PURCHASE_API_BASE_URL);
    }

    findPurchaseById(purchaseId) {
        return axios.get(
            PURCHASE_API_BASE_URL
            + '/'
            + purchaseId);
    }

    insertPurchase(purchase) {
        return axios.post(
            PURCHASE_API_BASE_URL,
            purchase);
    }

    updatePurchase(purchaseId, purchase) {
        return axios.put(
            PURCHASE_API_BASE_URL
            + '/'
            + purchaseId,
            purchase);
    }

    deletePurchase(purchaseId) {
        return axios.delete(
            PURCHASE_API_BASE_URL
            + '/'
            + purchaseId);
    }

}

export default new PurchaseService()
