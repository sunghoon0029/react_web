import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom'
import { getBoard, deleteBoard } from '../../modules/board';

import Layout from '../../components/layout/Layout';
import { Button } from 'react-bootstrap';

const BoardDetail = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { id } = useParams();

    const boardData = useSelector(state => state.board);

    const backToBoardList = () => {
        navigate('/board')
    };

    const moveToUpdate = () => {
        navigate(`/board/update/${id}`);
    };

    const onDeleteBoard = () => {
        try {
            dispatch(deleteBoard(id));

            alert('게시글 삭제 완료');
            navigate('/board');
        } catch (error) {
            console.error(error);

            alert('게시글 삭제 실패');
        };
    };

    useEffect(() => {
        dispatch(getBoard(id));
    }, [dispatch, id]);

  return (
    <Layout>
        <div>
            {boardData.board ? (
                <>
                    <h2>{boardData.board.title}</h2>
                    <p>{boardData.board.member}</p>
                    <p>{boardData.board.contents}</p>
                    <p>작성일자: {new Date(boardData.board.createdAt).toLocaleString()}</p>
                    <p>조회수: {boardData.board.hits}</p>
                </>
            ) : (
                <div>Loading...</div>
            )}
        </div>
        <Button variant="primary" onClick={ backToBoardList }>게시글 목록</Button>
        <Button variant="primary" onClick={ moveToUpdate }>게시글 수정</Button>
        <Button variant="danger" onClick={ onDeleteBoard }>게시글 삭제</Button>
    </Layout>
  );
};

export default BoardDetail;