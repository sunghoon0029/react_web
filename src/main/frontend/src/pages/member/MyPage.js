import React from 'react'
import { useNavigate } from 'react-router-dom';

import Layout from '../../components/layout/Layout'
import { Button } from 'react-bootstrap';

const MyPage = () => {

    const navigate = useNavigate();

    const backToHome = () => {
      navigate('/');
    };

    const moveToProfile = () => {
        navigate('/mypage/profile');
    };

  return (
    <Layout>
      <div>MyPage</div>

      <Button variant="primary" onClick={ moveToProfile }>프로필</Button>
      <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
    </Layout>
  );
};

export default MyPage;