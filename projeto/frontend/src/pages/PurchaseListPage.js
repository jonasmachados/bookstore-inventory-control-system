import React, { useState, useEffect } from "react";
import PurchaseList from "../components/PurchaseList";
import PurchaseService from "../services/PurchaseService";
import "../styles/page.css";

const PurchaseListPage = () => {

    const [purchaseList, setPurchaseList] = useState([]);

    useEffect(() => {
        findAllPurchase();
    }, []);

    const findAllPurchase = () => {
        PurchaseService.findAllPurchase()
            .then((response) => {
                setPurchaseList(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const handleDeletePurchase = (purchaseId) => {
        PurchaseService.deletePurchase(purchaseId)
            .then(() => {
                findAllPurchase();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="container-page">

            <PurchaseList
                purchaseList={purchaseList}
                onDeletePurchase={handleDeletePurchase}
            />

        </div>
    );
};

export default PurchaseListPage;