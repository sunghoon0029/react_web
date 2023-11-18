import React from 'react'
import Layout from '../components/layout/Layout';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Button from 'react-bootstrap/Button';

const Login = () => {

  const navigate = useNavigate();

  const loginMember = async () => {
    await axios.post('http://localhost:8080/member/login')
  };

  const backToHome = () => {
    navigate('/');
  };

  return (
    <Layout>
      <Container>
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" placeholder="email" />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
          </Form.Group>

          <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
        </Form>
      </Container>
    </Layout>
  );
};

export default Login;