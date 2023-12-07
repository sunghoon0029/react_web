import React from 'react'
import { Col, Container, Row } from 'react-bootstrap';

const Footer = () => {

  const thisYear = () => {
    const year = new Date().getFullYear();
    return year
  };

  return (
    <footer className="bg-light text-dark fixed-bottom">
      <Container>
        <Row className="py-3">
          <Col md={6}>
            <p>&copy; {thisYear()} Web</p>
          </Col>
          <Col md={6} className="text-end">
            <span className="mr-2">이용약관</span> / <span>개인정보 처리방침</span>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;