import axios from 'axios';

const LIVRO_API_BASE_URL = "https://backend-livraria-jonas.herokuapp.com/livro";

class LivroService{

    getAllLivros(){
        return axios.get(LIVRO_API_BASE_URL);
    }

    createLivro(livro){
        return axios.post(LIVRO_API_BASE_URL, livro);
    }

    getLivroById(livroId){
        return axios.get(LIVRO_API_BASE_URL + '/' + livroId);
    }

    updateLivro(livroId, livro){ 
        return axios.put(LIVRO_API_BASE_URL + '/' +livroId, livro);
    }

    addEstoque(livroId, livro){ 
        return axios.patch(LIVRO_API_BASE_URL + '/' + livroId + '/addEstoque', livro);
    }

    removerEstoque(livroId, livro){ 
        return axios.patch(LIVRO_API_BASE_URL + '/' + livroId + '/removerEstoque', livro);
    }

    deleteLivro(livroId){
        return axios.delete(LIVRO_API_BASE_URL + '/' + livroId);
    }

    atualizarLivroCompra(livroId, compraId, livro){
        return axios.patch(LIVRO_API_BASE_URL + '/' + livroId + '/atualizarEstoque/' + compraId, livro);
    }

    atualizarLivroVenda(livroId, vendaId, livro){
        return axios.patch(LIVRO_API_BASE_URL + '/' + livroId + '/atualizarVenda/' + vendaId, livro);
    }
    
}

export default new LivroService()
