import React, { useState } from 'react'
import Layout from '../components/layout/Layout';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const Login = () => {

  const navigate = useNavigate();

  const [member, setMember] = useState({
    email: '',
    password: '',
  });

  const { email, password } = member;

  const onChange = (e) => {
    const { value, name } = e.target;
    setMember({
      ...member,
      [name]: value
    });
  };

  const loginMember = async () => {
    try {
      const res = await axios.post('http://localhost:8080/login', member);
      const { accessToken, refreshToken } = res.data;

      console.log(res.data);

      navigate('/');
    } catch (err) {
      console.log('error')
    }
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
            <Form.Control
              type="email"
              placeholder="email"
              name="email"
              value={ email }
              onChange={ onChange }
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              name="password"
              value={ password }
              onChange={ onChange }
            />
          </Form.Group>

          <Button variant="primary" onClick={ loginMember }>로그인</Button>
          <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
        </Form>
      </Container>
    </Layout>
  );
};

export default Login;