import React, { useEffect, useState } from 'react'
import Layout from '../../components/layout/Layout';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { boardList } from '../../modules/board';

const BoardList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const getBoardList = (e) => {
    e.preventDefault();

    try {
      dispatch(boardList());
    } catch (error) {
      console.error(error);
    }
  }

  const moveToSave = () => {
    navigate('/board/save');
  }

  return (
    <Layout>
      <Button variant="primary" onClick={ moveToSave }>게시글 작성</Button>
    </Layout>
  );
};

export default BoardList;