import { Container, Row, Col, Button } from "react-bootstrap";

import logo from "../../assets/img/logo.png";
import "./Footer.css";

import {FaLinkedinIn, FaFacebookF } from 'react-icons/fa';
import {AiFillInstagram } from 'react-icons/ai';

const Footer = () => {
  return (
    <footer className="footer">
      <Container>
        <Row className="align-items-center">

          <Col size={12} sm={6}>
            <div className="img-logo">
              <img src={logo} alt="Logo" />
            </div>
          </Col>
          <Col size={12} sm={6} className="text-center text-sm-end">
            <div className="social-icon">
              <a href="https://www.facebook.com/jonas.machado.90/">
                <i><FaFacebookF /></i>
              </a>
              <a href="https://www.instagram.com/jonasmachado01/">
                <i> <AiFillInstagram /></i>
              </a>
              <a href="https://www.linkedin.com/in/jonas-machados/">
                <i><FaLinkedinIn /></i>
              </a>
            </div>
            <p>Copyright 2022. All Rights Reserved</p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
