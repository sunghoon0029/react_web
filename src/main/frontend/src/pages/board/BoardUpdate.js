import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { getBoard, updateBoard } from '../../modules/board';
import Layout from '../../components/layout/Layout';
import { Button, Container, Form } from 'react-bootstrap';

const BoardUpdate = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { id } = useParams();

    const boardData = useSelector(state => state.board);

    const [title, setTitle] = useState('');
    const [contents, setContents] = useState('');

    const onChangeTitle = (e) => {
        setTitle(e.target.value);
    };
    const onChangeContents = (e) => {
        setContents(e.target.value);
    };

    const onSubmit = async (e) => {
        e.preventDefault();

        let body = {
            title: title,
            contents: contents,
        };

        try {
            dispatch(updateBoard(id, body));

            alert('게시글 수정 완료');
            navigate(`/board/${id}`);
        } catch (error) {
            console.error(error);

            alert('게시글 작성 실패');
        }
    };

    const backToBoardDetail = () => {
        navigate(`/board/${id}`);
    };

    useEffect(() => {
        setTitle(boardData.board.title);
        setContents(boardData.board.contents);
    }, [dispatch, id]);

  return (
    <Layout>
        <Container>
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>Title</Form.Label>
                    <Form.Control
                        type="title"
                        placeholder="title"
                        value={ title }
                        onChange={ onChangeTitle }
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Contents</Form.Label>
                    <Form.Control
                        as="textarea"
                        rows={5}
                        placeholder="contents"
                        value={ contents }
                        onChange={ onChangeContents }
                    />
                </Form.Group>

                <Button variant="primary" onClick={ onSubmit }>게시글 수정</Button>
                <Button variant="primary" onClick={ backToBoardDetail }>뒤로가기</Button>
            </Form>
        </Container>
    </Layout>
  );
};

export default BoardUpdate;