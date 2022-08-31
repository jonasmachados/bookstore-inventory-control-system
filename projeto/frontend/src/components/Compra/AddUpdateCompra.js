import React, { useState, useEffect} from 'react'
import { Link, useParams } from 'react-router-dom';
import CompraService from '../../services/CompraService';
import 'bootstrap/dist/css/bootstrap.min.css';
import LivroService from '../../services/LivroService';

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

    const saveOrUpdateCompra = (e) => {
        e.preventDefault();

        if (id) {
            CompraService.updateCompra(id, compra).then((response) => {
                window.location.href = "/compras";
            }).catch(error => {
                console.log(error)
            })

        } else {
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