import React from 'react'
import { Button } from 'react-bootstrap';

const CommentList = ({ comments }) => {

  return (
    <div>
        <h2>Comments</h2>
        {comments.map(comment => (
            <div key={comment.id}>
                <p>{comment.contents}</p>
                <p>작성자: {comment.member}</p>
                <p>작성일자: {new Date(comment.createdAt).toLocaleString()}</p>
            </div>
        ))}
    </div>
  );
};

export default CommentList;