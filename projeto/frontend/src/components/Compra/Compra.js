import compraImg from "../../assets/img/compra.svg";
import "./Compra.css";

const Compra = () => {

    return (
        <div className="container-compra">
            <div>
                <h1>Compras</h1>
            </div>
            <img src={compraImg} alt="compraImg Img" />
        </div>
    )
}

export default Compra;