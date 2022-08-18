import axios from 'axios';

const LIVRO_API_BASE_URL = "http://localhost:8070/livro";

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

    deleteLivro(livroId){
        return axios.delete(LIVRO_API_BASE_URL + '/' + livroId);
    }
    
}

export default new LivroService()
