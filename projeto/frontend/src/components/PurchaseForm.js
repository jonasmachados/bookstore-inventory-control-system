import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import '../styles/form.css';
import '../styles/button.css';
import { AiOutlineFolderAdd } from 'react-icons/ai';
import { BiEdit } from 'react-icons/bi';
import PurchaseService from '../services/PurchaseService';
import TitleWithIcon from './TitleWithIcon';
import { purchaseRegistrationSchema, validateForm, renderValidationMessage, clearError } from '../utils/validationSchemas';

const PurchaseForm = ({ id, purchase, bookList }) => {

    const [purchaseForm, setPurchaseForm] = useState({
        bookId: '',
        qtdItens: '',
        precoVenda: '',
    });

    const [validationErrors, setValidationErrors] = useState({});

    useEffect(() => {
        if (purchase && purchase.livro) {
            setPurchaseForm(prevPurchaseForm => ({
                ...prevPurchaseForm,
                bookId: purchase.livro.id,
                qtdItens: purchase.qtdItens || '',
                precoVenda: purchase.precoVenda || '',
            }));
        }
    }, [purchase]);

    const handleChange = (e) => {
        const { name, value } = e.target;

        setPurchaseForm({
            ...purchaseForm,
            [name]: value,
        });

        clearError(validationErrors, setValidationErrors, [name]);
    };

    const saveOrUpdateBook = async (e) => {
        e.preventDefault();

        const errors = await validateForm(purchaseForm, purchaseRegistrationSchema);

        setValidationErrors(errors);

        const purchaseData = {
            livro: {
                id: purchaseForm.bookId
            },
            qtdItens: purchaseForm.qtdItens,
            precoVenda: purchaseForm.precoVenda
        };

        if (Object.keys(errors).length === 0) {
            try {
                if (id) {
                    await PurchaseService.updatePurchase(id, purchaseData);
                    window.location.href = '/compras';
                } else {
                    await PurchaseService.insertPurchase(purchaseData);
                    window.location.href = '/compras';
                }
            } catch (error) {
                console.error(error);
            }
        };
    }

    const bookOptions = bookList.map((bookItem) => ({
        value: bookItem.id,
        label: bookItem.titulo,
    }));

    return (
        <div className="form-container">

            <div className="form-title">
                <TitleWithIcon
                    icon={id ? <BiEdit /> : <AiOutlineFolderAdd />}
                    title={id ? "Atualizar Compra" : "Adicionar Compra"}
                />
            </div>

            <form className='form'>
                <div className="form-input">
                    <label>Livro</label>
                    <Select
                        name="bookId"
                        className="react-select"
                        classNamePrefix="react-select"
                        placeholder="Selecione um livro"
                        isSearchable={false}
                        value={bookOptions.find(
                            option => option.value === purchaseForm.bookId
                        )}
                        onChange={(e) => {
                            setPurchaseForm({
                                ...purchaseForm,
                                bookId: e.value,
                            })
                            clearError(validationErrors,
                                setValidationErrors,
                                'bookId');
                        }}
                        options={bookOptions}
                    />
                    {renderValidationMessage(validationErrors, 'bookId')}

                </div>

                <div className="form-in-column">
                    <div className="form-input">
                        <label>Quantidade</label>
                        <input
                            type="number"
                            placeholder="Digite a quantidade"
                            name="qtdItens"
                            value={purchaseForm.qtdItens}
                            onChange={handleChange}
                        >
                        </input>
                        {renderValidationMessage(validationErrors, 'qtdItens')}
                    </div>

                    <div className="form-input">
                        <label>Preco de venda</label>
                        <input
                            type="number"
                            step="0.1"
                            min='0'
                            max='20'
                            placeholder="Digite o preço"
                            name="precoVenda"
                            value={purchaseForm.precoVenda}
                            onChange={handleChange}
                        >
                        </input>
                        {renderValidationMessage(validationErrors, 'precoVenda')}
                    </div>
                </div>

                <div className='button-container'>
                    <button
                        type="submit"
                        className="button primary-button"
                        onClick={(e) => saveOrUpdateBook(e)}
                    >
                        Salvar
                    </button>

                    <Link
                        to="/clientes"
                        className="button secondary-button ">
                        Cancelar
                    </Link>
                </div>
            </form >

        </div >
    );

};

export default PurchaseForm;