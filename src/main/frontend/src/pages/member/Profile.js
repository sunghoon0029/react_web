import React from 'react'
import Layout from '../../components/layout/Layout'

import { useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';

const Profile = () => {

    const navigate = useNavigate();

    const moveToProfileEdit = () => {
        navigate('/mypage/profile-edit');
    };

  return (
    <Layout>
        <div>Profile</div>
        <Button variant="primary" onClick={ moveToProfileEdit }>프로필수정</Button>
    </Layout>
  );
};

export default Profile;