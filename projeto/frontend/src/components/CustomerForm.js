import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { AiOutlineFolderAdd } from 'react-icons/ai';
import { BiEdit } from 'react-icons/bi';
import TitleWithIcon from './TitleWithIcon';
import Select from 'react-select';
import InputMask from 'react-input-mask';
import CustomerService from '../services/CustomerService.js';

const CustomerForm = ({ id, customer }) => {

    const [nome, setNome] = useState('')
    const [rua, setRua] = useState('')
    const [numero, setNumero] = useState('')
    const [bairro, setBairro] = useState('')
    const [cidade, setCidade] = useState('')
    const [estado, setEstado] = useState('')
    const [cep, setCep] = useState('')
    const [personaType, setPersonaType] = useState({ value: 'PF', label: 'Pessoa Física' });
    const [rg, setRg] = useState('')
    const [cpf, setCpf] = useState('')
    const [cnpj, setCnpj] = useState('')

    useEffect(() => {
        if (customer) {
            setNome(customer.nome);
            setRua(customer.rua);
            setNumero(customer.numero)
            setBairro(customer.bairro)
            setCidade(customer.cidade)
            setEstado(customer.estado)
            setCep(customer.cep)

            if (customer.cpf) {
                setPersonaType({ value: 'PF', label: 'Pessoa Física' })
                setRg(customer.rg);
                setCpf(customer.cpf);
                setCnpj('')
            } else {
                setPersonaType({ value: 'PJ', label: 'Pessoa Jurídica' })
                setRg('');
                setCpf('');
                setCnpj(customer.cnpj)
            }
        }
    }, [customer]);

    const saveOrUpdateCustomer = async (e) => {
        e.preventDefault();

        const clienteType = personaType.value;
        const customerData = {
            nome,
            rua,
            numero,
            bairro,
            cidade,
            estado,
            cep,
            rg: personaType.value === 'PF' ? rg : undefined,
            cpf: personaType.value === 'PF' ? cpf : undefined,
            cnpj: personaType.value === 'PJ' ? cnpj : undefined
        };

        const customerRequest = {
            clienteType,
            [clienteType.toLowerCase()]: customerData
        };

        try {
            const response = id
                ? await CustomerService.updateCustomer(id, customerRequest)
                : await CustomerService.insertCustomer(customerRequest);

            console.log(response.data);
            window.location.href = '/clientes';
        } catch (error) {
            console.log(error);
        }
    };

    const handlePersonaTypeChange = (selectedOption) => {
        setPersonaType(selectedOption);
    };

    const personaOptions = [
        { value: 'PF', label: 'Pessoa Física' },
        { value: 'PJ', label: 'Pessoa Jurídica' },
    ];

    return (
        <div className="form-container">

            <div className="form-title">
                <TitleWithIcon
                    icon={id ? <BiEdit /> : <AiOutlineFolderAdd />}
                    title={id ? "Atualizar Cliente" : "Adicionar Cliente"}
                />
            </div>

            <form className='form'>
                <div className="form-input">
                    <label>Nome</label>
                    <input
                        type="text"
                        placeholder="Digite o nome"
                        name="nome"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    >
                    </input>
                </div>

                <div className="form-in-column">
                    <div className="form-input">
                        <label>Rua</label>
                        <input
                            type="text"
                            placeholder="Digite a rua"
                            name="rua"
                            value={rua}
                            onChange={(e) => setRua(e.target.value)}
                        >
                        </input>
                    </div>

                    <div className="form-input">
                        <label>Numero</label>
                        <input
                            type="number"
                            placeholder="Digite o número"
                            name="numero"
                            value={numero}
                            onChange={(e) => setNumero(e.target.value)}
                        >
                        </input>
                    </div>

                    <div className="form-input">
                        <label>Bairro</label>
                        <input
                            type="text"
                            placeholder="Digite o bairro"
                            name="bairro"
                            value={bairro}
                            onChange={(e) => setBairro(e.target.value)}
                        >
                        </input>
                    </div>
                </div>

                <div className="form-in-column">
                    <div className="form-input">
                        <label>Cidade</label>
                        <input
                            type="text"
                            placeholder="Digite a cidade"
                            name="cidade"
                            value={cidade}
                            onChange={(e) => setCidade(e.target.value)}
                        >
                        </input>
                    </div>

                    <div className="form-input">
                        <label>Estado</label>
                        <input
                            type="text"
                            placeholder="Digite o estado"
                            name="estado"
                            value={estado}
                            onChange={(e) => setEstado(e.target.value)}
                        >
                        </input>
                    </div>

                    <div className="form-input">
                        <label>CEP</label>
                        <InputMask
                            mask="99999-999"
                            maskChar="_"
                            value={cep}
                            onChange={e => setCep(e.target.value)}
                        >
                            {inputProps =>
                                <input
                                    type="text"
                                    placeholder="Digite o CEP"
                                    name="cep"
                                    {...inputProps}
                                />
                            }
                        </InputMask>
                    </div>
                </div>

                <div className="form-type">
                    <label>Tipo do cliente</label>
                </div>

                <div className='form-related'>
                    <div className="form-in-column">
                        <div className="form-input">
                            <Select
                                name="personaType"
                                className="react-select"
                                classNamePrefix="react-select"
                                isSearchable={false} value={personaType}
                                onChange={handlePersonaTypeChange}
                                options={personaOptions}
                            />
                        </div>
                    </div>

                    {personaType.value === 'PF' && (
                        <div className="form-in-column">
                            <div className="form-input">
                                <label>RG</label>
                                <InputMask
                                    mask="99.999.999-9"
                                    maskChar="_"
                                    value={rg}
                                    onChange={(e) => setRg(e.target.value)} >
                                    {inputProps =>
                                        <input
                                            type="text"
                                            placeholder="Digite o RG"
                                            name="rg"
                                            {...inputProps} />
                                    }
                                </InputMask>

                            </div>

                            <div className="form-input">
                                <label>CPF</label>
                                <InputMask
                                    mask="999.999.999-99"
                                    maskChar="_"
                                    value={cpf}
                                    onChange={(e) => setCpf(e.target.value)}
                                >
                                    {inputProps =>
                                        <input
                                            type="text"
                                            placeholder="Digite o CPF"
                                            name="cpf"
                                            {...inputProps} />
                                    }
                                </InputMask>
                            </div>
                        </div>
                    )}

                    {personaType.value === 'PJ' && (
                        <div className="form-in-column">
                            <div className="form-input">
                                <label>CNPJ</label>
                                <InputMask
                                    mask="99.999.999/9999-99"
                                    maskChar="_"
                                    value={cnpj}
                                    onChange={(e) => setCnpj(e.target.value)}
                                >
                                    {inputProps =>
                                        <input type="text"
                                            placeholder="Digite o CNPJ"
                                            name="cnpj"
                                            {...inputProps} />
                                    }
                                </InputMask>
                            </div>
                        </div>
                    )}
                </div>

                <div className='button-container'>
                    <button
                        className="button primary-button"
                        onClick={(e) => saveOrUpdateCustomer(e)} >
                        Salvar
                    </button>

                    <Link
                        to="/clientes"
                        className="button secondary-button ">
                        Cancelar
                    </Link>
                </div>
            </form>

        </div>
    );
};

export default CustomerForm;