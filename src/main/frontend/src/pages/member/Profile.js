import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { getUser } from '../../modules/user';

import Layout from '../../components/layout/Layout'
import { Button } from 'react-bootstrap';

const Profile = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { id } = useParams();

    const userData = useSelector((state) => state.user);

    const moveToProfileEdit = () => {
        navigate('/mypage/profile-edit');
    };

    useEffect(() => {
      dispatch(getUser(id));
    }, [dispatch]);

  return (
    <Layout>
      <div>
        {userData.user ? (
          <>
            <p>{userData.user.email}</p>
            <p>{userData.user.password}</p>
            <p>{userData.user.name}</p>
            <p>{userData.user.birth}</p>
            <p>{userData.user.gender}</p>
            <p>{userData.user.phoneNumber}</p>
            <p>{userData.user.createdTime}</p>
            <p>{userData.user.updatedTime}</p>
          </>
        ) : (
          <div>Loading...</div>
        )}
      </div>
      <Button variant="primary" onClick={ moveToProfileEdit }>프로필 수정</Button>
    </Layout>
  );
};

export default Profile;