import React, { useEffect } from 'react'
import Layout from '../../components/layout/Layout';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button, Card } from 'react-bootstrap';
import { boardList } from '../../modules/board';

const BoardList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const boardData = useSelector((state) => state.board);

  const moveToWrite = () => {
    navigate('/board/write');
  };

  const backToHome = () => {
    navigate('/');
  };

  useEffect(() => {
    dispatch(boardList());
  }, []);

  return (
    <Layout>
      <div className="mt-4">
        {boardData && boardData.length > 0 ? (
          boardData.map((board) => (
            <Card key={board.id} className="mb-3">
              <Card.Body>
                <Card.Title>{board.title}</Card.Title>
                <Card.Text>{board.contents}</Card.Text>
              </Card.Body>
            </Card>
          ))
        ) : (
          <p>작성된 게시글이 없습니다.</p>
        )}
      </div>

      <Button variant="primary" onClick={ moveToWrite }>게시글 작성</Button>
      <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
    </Layout>
  );
};

export default BoardList;