import React, { useState, useEffect } from 'react'
import { Link, useParams } from 'react-router-dom';
import VendaService from '../../services/VendaService';
import 'bootstrap/dist/css/bootstrap.min.css';
import ClienteService from '../../services/ClienteService';
import LivroService from '../../services/LivroService';
import * as yup from 'yup';

const AddUpdateVenda = () => {

    const [client, setClient] = useState('')
    const [livro, setLivro] = useState('')
    const [qtdItens, setQtdItens] = useState('')
    const [precoVenda, setPrecoVenda] = useState('')
    const { id } = useParams();

    const [listaCliente, setlistaCliente] = useState([]);
    const [listaLivros, setlistaLivros] = useState([])

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

    useEffect(() => {
        getAllClientes();
    }, []);

    useEffect(() => {
        getAllLivros();
    }, []);

    useEffect(() => {
        VendaService.getVendaById(id).then((response) => {
            setClient(response.data.client.id)
            setLivro(response.data.livro.id)
            setQtdItens(response.data.qtdItens)
            setPrecoVenda(response.data.precoVenda)
        }).catch(error => {
            console.log(error)
        })
    }, [])

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

    const [status, setStatus] = useState({
        type: '',
        mensagem: ''
    })

    let schema = yup.object().shape({
        qtdItens: yup.number().required().integer(),
        precoVenda: yup.number().required(),
        livro: yup.object().required().typeError(),
        client: yup.object().required().typeError()
    });

    async function validate() {
        let schema = yup.object().shape({
            client: yup.object().shape({
                id: yup.number()
                    .typeError('Necessario selecionar !')
                    .required("O campo é obrigatório.")
            }),
            livro: yup.object().shape({
                id: yup.number()
                    .typeError('Necessario selecionar !')
                    .required("O campo é obrigatório.")
            }),
            precoVenda: yup.number()
                .typeError('Necessario preencher!')
                .required("O campo é obrigatório.")
                .positive("O Preço deve ser maior que 0"),
            qtdItens: yup.number()
                .typeError('Necessario preenche!')
                .required("O campo é obrigatório.")
                .positive("Quantidade deve ser maior que 0")
                .integer("Estoque deve possuir um número inteiro."),
        });

        try {
            await schema.validate(venda)
            return true;
        } catch (err) {
            setStatus({
                type: 'error',
                mensagem: err.errors
            });
            return false;
        }
    }

    const saveOrUpdateVenda = async (e) => {
        e.preventDefault();

        if (!(await validate())) return;

        const livro = { qtdItens }

        if (id) {
            LivroService.removerEstoque(venda.livro.id, livro).then((response) => {
                window.location.href = "/vendas";
            }).catch(error => {
                console.log(error)
            })
            VendaService.updateVenda(id, venda).then((response) => {
                window.location.href = "/vendas";
            }).catch(error => {
                console.log(error)
            })

        } else {
            LivroService.removerEstoque(venda.livro.id, livro).then((response) => {

                window.location.href = "/vendas";
            }).catch(error => {
                console.log(error)
            })
            VendaService.createVenda(venda).then((response) => {

                console.log(response.data)

                window.location.href = "/vendas";

            }).catch(error => {
                console.log(error)
            })
        }

    }

    const title = () => {

        if (id) {
            return <h1 className="text-center">Atualizar Venda</h1>
        } else {
            return <h1 className="text-center">Adicionar Venda</h1>
        }
    }

    const valid = (name) => {
        if (status.type === 'error' && name === '') {
            return status.type === 'error' ? <p style={{ color: "#ff0000", }}> {status.mensagem}</p> : "";
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
                                            listaCliente.map((client) => (
                                                <option key={client.id} value={client.id}> {client.name}</option>
                                            ))
                                        }
                                    </select>
                                    {valid(venda.client.id)}
                                </div>

                                <div className="form-group mb-2">
                                    <label className="mb-2">Livro</label>
                                    <select name="livro" className="form-control" onChange={(e) => setLivro(e.target.value)}>
                                        <option>--Selecione o Livro--</option>
                                        {
                                            listaLivros.map((livro) => (
                                                <option key={livro.id} value={livro.id} > {livro.titulo} </option>
                                            ))
                                        }
                                    </select>
                                    {valid(venda.livro.id)}
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="number"
                                        placeholder="Quantidade"
                                        name="qtdItens"
                                        className="form-control"
                                        value={qtdItens}
                                        onChange={(e) => setQtdItens(e.target.value)}
                                    >
                                    </input>
                                    {valid(venda.qtdItens)}
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="number"
                                        step="0.1"
                                        min='0'
                                        max='20'
                                        placeholder="Preco de venda"
                                        name="precoVenda" s
                                        className="form-control"
                                        value={precoVenda}
                                        onChange={(e) => setPrecoVenda(e.target.value)}
                                    >
                                    </input>
                                    {valid(precoVenda)}
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