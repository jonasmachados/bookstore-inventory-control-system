import React from 'react'
import error404 from "../../assets/img/404.svg";
import "./styles.css";

function Error404() {

    return (
        <div className="container-error">
            <img src={error404} alt="error-4040 Img" />
            <p className="p-error">Page not found</p>
        </div>
    )
}


export default Error404;

