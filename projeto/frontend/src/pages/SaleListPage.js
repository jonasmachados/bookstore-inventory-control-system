import React, { useState, useEffect } from "react";
import SaleService from "../services/SaleService";
import SaleList from "../components/SaleList";
import "../styles/page.css";

const SaleListPage = () => {

    const [saleList, setSaleList] = useState([]);

    useEffect(() => {
        fetchSales();
    }, []);

    const fetchSales = async () => {
        try {
            const response = await SaleService.findAllSales();
            setSaleList(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleDeleteSale = async (saleId) => {
        try {
            await SaleService.deleteSale(saleId);
            fetchSales();
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container-page">

            <SaleList
                saleList={saleList}
                onDeleteSale={handleDeleteSale}
            />

        </div>
    );

};

export default SaleListPage;