import React, { useState, useEffect} from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import VendaService from '../../services/VendaService';
import 'bootstrap/dist/css/bootstrap.min.css';
import ClienteService from '../../services/ClienteService';
import LivroService from '../../services/LivroService';

const AddUpdateVenda = () => {

    const [client, setClient] = useState('')
    const [livro, setLivro] = useState('')
    const [qtdItens, setQtdItens] = useState('')
    const [precoVenda, setPrecoVenda] = useState('')
    const history = useNavigate();
    const { id } = useParams();

    const [listaCliente, setlistaCliente] = useState([]);
    const [listaLivros, setlistaLivros] = useState([])

    useEffect(() => {
        getAllClientes();
    }, []);

    useEffect(() => {
        getAllLivros();
    }, []);

    const getAllClientes = () => {
        ClienteService.getAllClientes()
            .then((response) => {
                setlistaCliente(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getAllLivros = () => {
        LivroService.getAllLivros()
            .then((response) => {
                setlistaLivros(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const saveOrUpdateVenda = (e) => {
        e.preventDefault();

        const venda = {
            client: {
                id: client
            },
            livro: {
                id: livro
            },
            qtdItens,
            precoVenda
        }

        if (id) {
            VendaService.updateVenda(id, venda).then((response) => {
                window.location.href = "/vendas";
            }).catch(error => {
                console.log(error)
            })

        } else {
            VendaService.createVenda(venda).then((response) => {

                console.log(response.data)

                window.location.href = "/vendas";

            }).catch(error => {
                console.log(error)
            })
        }

    }

    useEffect(() => {
        VendaService.getVendaById(id).then((response) => {
            setClient(response.data.client)
            setLivro(response.data.livro)
            setQtdItens(response.data.qtdItens)
            setPrecoVenda(response.data.precoVenda)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {

        if (id) {
            return <h1 className="text-center">Atualizar Venda</h1>
        } else {
            return <h1 className="text-center">Adicionar Venda</h1>
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
                                    <label className="mb-2">Cliente</label>
                                    <select name="client" className="form-control" onChange={(e) => setClient(e.target.value)}>
                                        <option>--Selecione o Cliente--</option>
                                        {
                                            listaCliente.map((cliente) => (
                                                <option key={cliente.id} value={cliente.id}> {cliente.name}</option>
                                            ))
                                        }
                                    </select>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="mb-2">Livro</label>
                                    <select name="livro" className="form-control" onChange={(e) => setLivro(e.target.value)}>
                                        <option>--Selecione o Livro--</option>
                                        {
                                            listaLivros.map((livro) => (
                                                <option key={livro.id} value={livro.id}> {livro.titulo} </option>
                                            ))
                                        }
                                    </select>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="number"
                                        placeholder="qtdItens"
                                        name="qtdItens"
                                        className="form-control"
                                        value={qtdItens}
                                        onChange={(e) => setQtdItens(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="number"
                                        step="0.1"
                                        min='0'
                                        max='20'
                                        placeholder="precoVenda"
                                        name="precoVenda"
                                        className="form-control"
                                        value={precoVenda}
                                        onChange={(e) => setPrecoVenda(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <button className="btn" onClick={(e) => saveOrUpdateVenda(e)} >Salvar </button>
                                <Link to="/vendas" className="btn"> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    )
}

export default AddUpdateVenda;