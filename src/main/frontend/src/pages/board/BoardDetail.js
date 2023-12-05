import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom'
import { getBoard } from '../../modules/board';
import Layout from '../../components/layout/Layout';
import { Button } from 'react-bootstrap';

const BoardDetail = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { id } = useParams();

    const boardData = useSelector((state) => state.board);

    const backToBoardList = () => {
        navigate('/board')
    }

    useEffect(() => {
        dispatch(getBoard(id));
    }, [dispatch, id]);

  return (
    <Layout>
        <div>
            {boardData.board ? (
                <>
                    <h2>{boardData.board.title}</h2>
                    <p>{boardData.board.contents}</p>
                    <p>작성일자: {new Date(boardData.board.createdTime).toLocaleString()}</p>
                </>
            ) : (
                <div>Loading...</div>
            )}
        </div>
        <Button variant="primary" onClick={ backToBoardList }>게시글 목록</Button>
    </Layout>
  )
}

export default BoardDetail;