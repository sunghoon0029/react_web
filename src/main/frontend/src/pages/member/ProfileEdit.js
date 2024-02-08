import React, { useEffect, useState } from 'react'

import Layout from '../../components/layout/Layout'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { updateUser } from '../../modules/user';
import { Button, Container, Form } from 'react-bootstrap';

const ProfileEdit = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const userData = useSelector(state => state.user);

  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [birth, setBirth] = useState('');
  const [gender, setGender] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const onChangePassword = (e) => {
    setPassword(e.target.value);
  };
  const onChangeName = (e) => {
    setName(e.target.value);
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
      password: password,
      name: name,
      birth: birth,
      gender: gender,
      phoneNumber: phoneNumber,
    };

    try {
      dispatch(updateUser(body));

      alert('프로필 수정 완료');
      navigate('/mypage/profile');
    } catch (error) {
      console.error(error);

      alert('프로필 수정 실패');
    }
  };

  const backToProfile = () => {
    navigate('/mypage/profile');
  };

  useEffect(() => {
    if (userData.user) {
      setPassword(userData.user.password);
      setName(userData.user.name);
      setBirth(userData.user.birth);
      setGender(userData.user.gender);
      setPhoneNumber(userData.user.phoneNumber);
    }
  }, [dispatch, userData.user]);

  return (
    <Layout>
      <Container>
        <Form>
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
                <option value="Male">남성</option>
                <option value="FeMale">여성</option>
            </Form.Control>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>PhoneNumber</Form.Label>
            <Form.Control
                type="phoneNumber"
                placeholder="phoneNumber"
                value={ phoneNumber }
                onChange={ onChangePhoneNumber }
            />
          </Form.Group>

          <Button variant="primary" onClick={ onSubmit }>프로필 수정</Button>
          <Button variant="primary" onClick={ backToProfile }>뒤로가기</Button>
        </Form>
      </Container>
    </Layout>
  );
};

export default ProfileEdit;