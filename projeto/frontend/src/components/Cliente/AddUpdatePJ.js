import React, { useState, useEffect } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import ClienteService from '../../services/ClienteService.js';

const AddUpdatePJ = () => {

    const [name, setName] = useState('')
    const [rua, setRua] = useState('')
    const [numero, setNumero] = useState('')
    const [bairro, setBairro] = useState('')
    const [cidade, setCidade] = useState('')
    const [estado, setEstado] = useState('')
    const [cep, setCep] = useState('')
    const [cnpj, setCnpj] = useState('')
    const history = useNavigate();
    const { id } = useParams();

    const saveOrUpdateCliente = (e) => {
        e.preventDefault();

        const cliente = { name, rua, numero, bairro, cidade, estado, cep, cnpj }

        if (id) {
            ClienteService.updateCliente(id, cliente).then((response) => {
                window.location.href = "/clientes";
            }).catch(error => {
                console.log(error)
            })

        } else {
            ClienteService.createClientePf(cliente).then((response) => {

                console.log(response.data)

                window.location.href = "/clientes";

            }).catch(error => {
                console.log(error)
            })
        }

    }

    useEffect(() => {

        ClienteService.getClienteById(id).then((response) => {
            setName(response.data.name)
            setRua(response.data.rua)
            setNumero(response.data.numero)
            setBairro(response.data.bairro)
            setCidade(response.data.cidade)
            setEstado(response.data.estado)
            setCep(response.data.cep)
            setCnpj(response.data.cnpj)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {

        if (id) {
            return <h1 className="text-center">Atualizar Empresa</h1>
        } else {
            return <h1 className="text-center">Adicionar Empresa</h1>
        }
    }

    return (
        <div>
            <br /><br />
            <div className="container-add">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        {
                            title()
                        }
                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="Razão Social"
                                        name="name"
                                        className="form-control"
                                        value={name}
                                        onChange={(e) => setName(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="rua"
                                        name="rua"
                                        className="form-control"
                                        value={rua}
                                        onChange={(e) => setRua(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="number"
                                        placeholder="numero"
                                        name="numero"
                                        className="form-control"
                                        value={numero}
                                        onChange={(e) => setNumero(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="bairro"
                                        name="bairro"
                                        className="form-control"
                                        value={bairro}
                                        onChange={(e) => setBairro(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="cidade"
                                        name="cidade"
                                        className="form-control"
                                        value={cidade}
                                        onChange={(e) => setCidade(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="estado"
                                        name="estado"
                                        className="form-control"
                                        value={estado}
                                        onChange={(e) => setEstado(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="Cep"
                                        name="cep"
                                        className="form-control"
                                        value={cep}
                                        onChange={(e) => setCep(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="text"
                                        placeholder="CNPJ"
                                        name="cnpj"
                                        className="form-control"
                                        value={cnpj}
                                        onChange={(e) => setCnpj(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <button className="btn" onClick={(e) => saveOrUpdateCliente(e)} >Salvar </button>
                                <Link to="/clientes" className="btn"> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    )
}

export default AddUpdatePJ;