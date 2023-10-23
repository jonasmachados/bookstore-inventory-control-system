import Error404 from "../components/Error404";
import SVGError404 from "../assets/img/404.svg";
import "../styles/not-found-page.css"
import "../styles/svg.css"

const NotFoundPage = () => {
    return (
        <div className="not-found-container">

            <div>
                <Error404 />
            </div>

            <div class="svg-container">
                <img
                    src={SVGError404}
                    className="svg-error"
                    alt="Error404 Img"
                />
            </div>

        </div>
    );
};

export default NotFoundPage;