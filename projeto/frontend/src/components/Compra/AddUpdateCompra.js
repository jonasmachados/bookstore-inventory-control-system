import React, { useState, useEffect } from 'react'
import { Link, useParams } from 'react-router-dom';
import CompraService from '../../services/CompraService';
import 'bootstrap/dist/css/bootstrap.min.css';
import LivroService from '../../services/LivroService';
import * as yup from 'yup';

const AddUpdateCompra = () => {

    const [livro, setLivro] = useState('')
    const [qtdItens, setQtdItens] = useState('')
    const [precoVenda, setPrecoVenda] = useState('')
    const { id } = useParams();

    const [listaLivros, setlistaLivros] = useState([])

    const compra = {
        livro: {
            id: livro
        },
        qtdItens,
        precoVenda
    }

    useEffect(() => {
        getAllLivros();
    }, []);

    useEffect(() => {
        CompraService.getCompraById(id).then((response) => {
            setLivro(response.data.livro.id)
            setQtdItens(response.data.qtdItens)
            setPrecoVenda(response.data.precoVenda)
        }).catch(error => {
            console.log(error)
        })
    }, [])

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
        livro: yup.object().required().typeError(),
        qtdItens: yup.number().required().integer(),
        precoVenda: yup.number().required(),
    });

    async function validate() {
        let schema = yup.object().shape({
            livro: yup.object().shape({
                id: yup.number()
                .typeError('Erro: Necessario selecionar o livro!')
                .required("Erro: O campo é obrigatório2.")
            }),
            precoVenda: yup.number()
                .typeError('Erro: Necessario preencher o preço de venda!')
                .required("Erro: O campo é obrigatório.")
                .positive("Erro: O Preço deve ser maior que 0"),
            qtdItens: yup.number()
                .typeError('Erro: Necessario preencher a quantidade a comprar!')
                .required("Erro: O campo é obrigatório.")
                .positive("Erro: Quantidade deve ser maior que 0")
                .integer("Erro: Estoque deve possuir um número inteiro.")
        });

        try {
            await schema.validate(compra)
            return true;
        } catch (err) {
            setStatus({
                type: 'error',
                mensagem: err.errors
            });
            return false;
        }
    }

    const saveOrUpdateCompra = async (e) => {
        e.preventDefault();

        if (!(await validate())) return;

        const livro = { qtdItens };

        if (id) {
            LivroService.atualizarLivroCompra(compra.livro.id, id, livro).then((response) => {
                console.log(response.data);
            }).catch(error => {
                console.log(error)
            })
            CompraService.updateCompra(id, compra).then((response) => {
                window.location.href = "/compras";
            }).catch(error => {
                console.log(error)
            })

        } else {
            LivroService.addEstoque(compra.livro.id, livro).then((response) => {

                window.location.href = "/compras";
            }).catch(error => {
                console.log(error)
            })
            CompraService.createCompra(compra).then((response) => {

                console.log(response.data)

                window.location.href = "/compras";

            }).catch(error => {
                console.log(error)
            })
        }

    }

    const title = () => {

        if (id) {
            return <h1 className="text-center">Atualizar Compra</h1>
        } else {
            return <h1 className="text-center">Adicionar Compra</h1>
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

                        {status.type === 'error' ? <p style={{ color: "#ff0000", textAlign: 'center', paddingTop: '5%' }}> {status.mensagem}</p> : ""}

                        <div className="card-body">
                            <form>

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
                                </div>

                                <div className="form-group mb-2">
                                    <input
                                        type="number"
                                        step="0.1"
                                        min='0'
                                        max='20'
                                        placeholder="Preço"
                                        name="precoVenda"
                                        className="form-control"
                                        value={precoVenda}
                                        onChange={(e) => setPrecoVenda(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <button className="btn" onClick={(e) => saveOrUpdateCompra(e)} >Salvar </button>
                                <Link to="/compras" className="btn"> Cancel </Link>
                            </form>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    )
}

export default AddUpdateCompra;