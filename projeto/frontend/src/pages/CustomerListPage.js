import React, { useState, useEffect } from "react";
import CustomerService from "../services/CustomerService";
import CustomerList from "../components/CustomerList";
import "../styles/customer-list-page.css"

const CustomerListPage = () => {

    const [customerList, setCustomerList] = useState([]);

    useEffect(() => {
        findAllCustomers();
    }, []);

    const findAllCustomers = () => {
        CustomerService.findAllCustomers()
            .then((response) => {
                setCustomerList(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const handleDeleteCustomer = (customerId) => {
        CustomerService.deleteCustomer(customerId)
            .then(() => {
                findAllCustomers();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="container-customer-list">

            <CustomerList
                customerList={customerList}
                onDeleteCustomer={handleDeleteCustomer}
            />

        </div>
    );
};

export default CustomerListPage;