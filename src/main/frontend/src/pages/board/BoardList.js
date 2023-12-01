import React, { useEffect, useState } from 'react'
import Layout from '../../components/layout/Layout';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { createBoard } from '../../modules/board';

const BoardList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [boardList, setBoardList] = useState([]);

  const getBoardList = (e) => {
    e.preventDefault();

    try {
      dispatch(createBoard());
      setBoardList();
    } catch (error) {
      console.error(error);
    }
  }

  // useEffect(() => {
  //   getBoardList();
  // }, []);

  const moveToSave = () => {
    navigate('/board/save');
  }

  return (
    <Layout>
      {/* <div>
        <ul>
          {boardList.map((board) => (
            <li key={board.id}>{board.title}</li>
          ))}
        </ul>
      </div> */}
      <Button variant="primary" onClick={ moveToSave }>게시글 작성</Button>
    </Layout>
  );
};

export default BoardList;