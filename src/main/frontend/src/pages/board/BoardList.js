import React, { useEffect } from 'react'
import Layout from '../../components/layout/Layout';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
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
  }, [dispatch]);

  return (
    <Layout>
      <div>
        {boardData.boardList.map((board) => (
          <Card key={board.id}>
            <Card.Body>
              <Link to={`/board/${board.id}`}>
                <Card.Title>{board.title}</Card.Title>
              </Link>
              <Card.Text>{board.contents}</Card.Text>
              <p className="text-muted">작성일자: {new Date(board.createdTime).toLocaleString()}</p>
            </Card.Body>
          </Card>
        ))}
      </div>
      <Button variant="primary" onClick={ moveToWrite }>게시글 작성</Button>
      <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
    </Layout>
  );
};

export default BoardList;