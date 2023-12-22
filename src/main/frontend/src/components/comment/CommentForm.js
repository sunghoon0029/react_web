import React, { useState } from 'react'
import { useDispatch } from 'react-redux';
import { commnetList, createComment } from '../../modules/comment';
import { Button, Form } from 'react-bootstrap';

const CommentForm = ({ id }) => {

    const dispatch = useDispatch();

    const [contents, setContents] = useState('');

    const onSubmit = async (e) => {
        e.preventDefault();

        await dispatch(createComment(id, { contents }));
        setContents('');

        dispatch(commnetList(id));
    };

  return (
    <Form onSubmit={onSubmit}>
        <Form.Group className="mb-3">
            <Form.Label>Comment</Form.Label>
            <Form.Control
                as="textarea"
                rows={3}
                value={ contents }
                onChange={(e) => setContents(e.target.value)}
            />
        </Form.Group>
        
        <Button variant="primary" type="submit">댓글 작성</Button>
    </Form>
  );
};

export default CommentForm;