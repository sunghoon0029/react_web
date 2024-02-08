import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

import Layout from '../../components/layout/Layout'
import { Button } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { deleteUser, getUser } from '../../modules/user';

const Profile = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const userData = useSelector(state => state.user);

    const backToHome = () => {
      navigate('/');
    };

    const moveToProfileEdit = () => {
        navigate('/mypage/profile-edit');
    };

    const onDeleteUser = () => {
      try {
        dispatch(deleteUser());

        alert('회원탈퇴 완료');
        navigate('/');
      } catch (error) {
        console.error(error);

        alert('회원탈퇴 싪패');
      };
    };

    useEffect(() => {
      dispatch(getUser());
    }, [dispatch]);

  return (
    <Layout>
      <div>
        <>
          <p>이메일 : {userData.user.email}</p>
          <p>이름 : {userData.user.name}</p>
          <p>생년월일 : {userData.user.birth}</p>
          <p>성별 : {userData.user.gender === 'Male' ? '남성' : '여성'}</p>
          <p>휴대전화번호 : {userData.user.phoneNumber}</p>
        </>
      </div>

      <Button variant="primary" onClick={ backToHome }>마이페이지</Button>
      <Button variant="primary" onClick={ moveToProfileEdit }>프로필 수정</Button>
      <Button variant="danger" onClick={ onDeleteUser }>회원탈퇴</Button>
    </Layout>
  );
};

export default Profile;