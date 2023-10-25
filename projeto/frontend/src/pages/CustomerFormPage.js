import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import CustomerForm from '../components/CustomerForm';
import CustomerService from "../services/CustomerService";

const CustomerFormPage = () => {

    const { id } = useParams();

    const [customer, setCustomer] = useState('')

    useEffect(() => {
        const findCustomerById = async () => {
            try {
                const response = await CustomerService.findCustomerById(id);
                setCustomer(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (id) {
            findCustomerById();
        }
    }, [id]);

    return (
        <div>

            <CustomerForm
                customer={customer}
                id={id}
            />

        </div>
    );
};

export default CustomerFormPage;