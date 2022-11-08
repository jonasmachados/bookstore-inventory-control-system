import escolhaImg from "../../assets/img/escolha.svg";
import { Link } from "react-router-dom";
import "./Cliente.css";

const Cliente = () => {

    return (
        <div className="container-cliente">
            <div className="pf-pj">
                <Link to="/add-cliente/pf" className="btn mb-4 btn-lg">
                    {" "}
                    Pessoa Fisica{" "}
                </Link>
                <Link to="/add-cliente/pj" className="btn mb-4 btn-lg">
                    {" "}
                    Pessoa Juridica{" "}
                </Link>
            </div>
            <img src={escolhaImg} alt="Header Img" />
        </div>
    )
}

export default Cliente;