import React, { useEffect, useState } from 'react'
import Layout from '../../components/layout/Layout';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { boardList } from '../../modules/board';

const BoardList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const [board, setBoard] = useState();

  const moveToWrite = () => {
    navigate('/board/write');
  }

  useEffect(() => {
    dispatch(boardList());
  }, []);

  return (
    <Layout>
      <Button variant="primary" onClick={ moveToWrite }>게시글 작성</Button>
    </Layout>
  );
};

export default BoardList;