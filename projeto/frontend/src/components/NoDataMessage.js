import { Link } from "react-router-dom";
import noDataMessage from "../assets/img/no-data.svg";
import "../styles/component.css"

const NoDataMessage = ({ header, bodyText, iconButton, urlButton }) => {

  return (
    <div className="container-no-data-message">

      <img
        src={noDataMessage}
        alt="No data message img"
        className="img-no-data-message"
      />

      <h1 className="h1-no-data-message">{header}</h1>

      <p className="p-no-data-message">{bodyText}</p>

      <Link to={urlButton} className="button primary-button">
        {iconButton}
        Novo
      </Link>

    </div>
  );

};

export default NoDataMessage;