import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom';
import { createBoard } from '../../modules/board';

import Layout from '../../components/layout/Layout';
import { Button, Container, Form } from 'react-bootstrap';

const BoardWrite = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [title, setTitle] = useState('');
  const [contents, setContents] = useState('');
  const [image, setImage] = useState([]);

  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };
  const onChangeContents = (e) => {
    setContents(e.target.value);
  };
  const onChangeImage = (e) => {
    const file = e.target.files[0];
    setImage(file);
  };

  // const onChangeImages = (e) => {
  //   const selectedImages = Array.from(e.target.files);
  //   setImages(selectedImages);
  // }

  const onSubmit = async (e) => {
    e.preventDefault();

    // let body = {
    //   title: title,
    //   contents: contents,
    // };

    const formData = new FormData();

    const json = JSON.stringify({ title, contents });
    const blob = new Blob([json], { type: "application/json" });

    formData.append('request', blob);
    formData.append('files', image);

    // for (let i = 0; i < FileElement.files.length; i++) {
    //   formData.append("files", FileElement.files[i]);
    // };

    // images.forEach((image, index) => {
    //   formData.append(`image-${index}`, image);
    // });

    try { 
      dispatch(createBoard(formData));

      alert('게시글 작성 완료');
      navigate('/board');
    } catch (error) {
      console.error(error);

      alert('게시글 작성 실패');
    }
  };

  const backToBoardList = () => {
    navigate('/board');
  };

  return (
    <Layout>
      <Container>
        <Form>
          <Form.Group className="mb-3">
            <Form.Label>Title</Form.Label>
            <Form.Control
                type="title"
                placeholder="title"
                value={title}
                onChange={onChangeTitle}
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Contents</Form.Label>
            <Form.Control
                as="textarea"
                rows={5}
                placeholder="contents"
                value={contents}
                onChange={onChangeContents}
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Image</Form.Label>
            <Form.Control
              type="file"
              multiple
              onChange={onChangeImage}
            />
          </Form.Group>

          <Button variant="primary" onClick={ onSubmit }>게시글 작성</Button>
          <Button variant="primary" onClick={ backToBoardList }>게시글 목록</Button>
        </Form>
      </Container>
    </Layout>
  );
};

export default BoardWrite;