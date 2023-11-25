import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom';
import { joinUser } from '../../modules/user';

import Layout from '../../components/layout/Layout';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button';

const ReduxJoin = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const onChangeName = (e) => {
        setName(e.target.value);
    };
    const onChangeEmail = (e) => {
        setEmail(e.target.value);
    };
    const onChangePassword = (e) => {
        setPassword(e.target.value);
    };

    const onSubmit = async (e) => {
        e.preventDefault();

        let body = {
            name: name,
            email: email,
            password: password,
        };

        try {
            const res = await dispatch(joinUser(body));

            if (res.payload.success) {
                alert('회원가입 완료');
                navigate('/');
            } else {
                alert('회원가입 실패');
            }
        } catch (err) {
            console.error(err);
            alert('error');
        }
    };

    const backToHome = () => {
        navigate('/');
    };

  return (
    <Layout>
        <Container>
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder="email"
                        value={ email }
                        onChange={ onChangeEmail }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        value={ password }
                        onChange={ onChangePassword }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>name</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="name"
                        value={ name }
                        onChange={ onChangeName }
                    />
                </Form.Group>

                <Button variant="primary" onClick={ onSubmit }>회원가입</Button>
                <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
            </Form>
        </Container>
    </Layout>
  );
};

export default ReduxJoin;