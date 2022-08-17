import { Container, Row, Col } from "react-bootstrap";

import "./Livro.css";

const Livro = () => {
  return (
    <section className="container-livro" >
      <Container>
        <Row  >
          <Col>
            <h1>LIVROS</h1>

            <p>
              Here
              Lorem ipsum vivamus semper quis nisi senectus laoreet cras dui
              ante ligula quam lorem, mattis est maecenas hac sociosqu congue
              platea aptent feugiat odio cursus. vestibulum tincidunt libero
              fringilla id nostra convallis consequat magna dapibus, fringilla
              vehicula magna quisque quis allivroiquam rhoncus condimentum.
              phasellus praesent massa justo per dictum consequat senectus orci,
              convallis enim quis augue risus platea torquent risus, enim nunc
              cras sit vestibulum laoreet pulvinar. non habitant rutrum aliquam
              mollis nisi fringilla tincidunt auctor habitant augue ipsum
              habitant, fusce proin euismod vehicula vel tortor gravida leo
              volutpat sociosqu nisl.
            </p>
            <br></br>
            <p>
              Lorem ipsum vivamus semper quis nisi senectus laoreet cras dui
              ante ligula quam lorem, mattis est maecenas hac sociosqu congue
              platea aptent feugiat odio cursus. vestibulum tincidunt libero
              fringilla id nostra convallis consequat magna dapibus, fringilla
              vehicula magna quisque quis aliquam rhoncus condimentum. phasellus
              praesent massa justo per dictum consequat senectus orci, convallis
              enim quis augue risus platea torquent risus, enim nunc cras sit
              vestibulum laoreet pulvinar. non habitant rutrum aliquam mollis
              nisi fringilla tincidunt auctor habitant augue ipsum habitant,
              fusce proin euismod vehicula vel tortor gravida leo volutpat
              sociosqu nisl.
            </p>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default Livro;
