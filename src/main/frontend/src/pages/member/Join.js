import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom';
import { registerUser } from '../../modules/user';

import Layout from '../../components/layout/Layout';
import { Button, Container, Form } from 'react-bootstrap';

const Join = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [birth, setBirth] = useState('');
    const [gender, setGender] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const onChangeName = (e) => {
        setName(e.target.value);
    };
    const onChangeEmail = (e) => {
        setEmail(e.target.value);
    };
    const onChangePassword = (e) => {
        setPassword(e.target.value);
    };
    const onChangeBirth = (e) => {
        setBirth(e.target.value);
    };
    const onChangeGender = (e) => {
        setGender(e.target.value);
    };
    const onChangePhoneNumber = (e) => {
        setPhoneNumber(e.target.value);
    };

    const onSubmit = (e) => {
        e.preventDefault();

        let body = {
            name: name,
            email: email,
            password: password,
            birth: birth,
            gender: gender,
            phoneNumber: phoneNumber,
        };

        try {
            dispatch(registerUser(body));

            alert('회원가입 완료');
            navigate('/login');
        } catch (error) {
            console.error(error);

            alert('회원가입 실패');
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
                    <Form.Label>이메일</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder="Email"
                        value={ email }
                        onChange={ onChangeEmail }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        value={ password }
                        onChange={ onChangePassword }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>이름</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="Name"
                        value={ name }
                        onChange={ onChangeName }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>생년월일</Form.Label>
                    <Form.Control
                        type="date"
                        placeholder="Birth Date"
                        value={ birth }
                        onChange={ onChangeBirth }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>성별</Form.Label>
                    <Form.Control
                        as="select"
                        value={ gender }
                        onChange={ onChangeGender }
                    >
                        <option value="">선택</option>
                        <option value="Male">남자</option>
                        <option value="Female">여자</option>
                    </Form.Control>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>휴대전화번호</Form.Label>
                    <Form.Control
                        type="tel"
                        placeholder="Phone Number"
                        value={ phoneNumber }
                        onChange={ onChangePhoneNumber }
                    />
                </Form.Group>

                <Button variant="primary" onClick={ onSubmit }>회원가입</Button>
                <Button variant="primary" onClick={ backToHome }>회원가입 취소</Button>
            </Form>
        </Container>
    </Layout>
  );
};

export default Join;