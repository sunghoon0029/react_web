import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { boardPage } from '../../modules/board';

import Layout from '../../components/layout/Layout';
import { Button, Card } from 'react-bootstrap';
import Pagination from '../../components/Paging';

const BoardList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const boardData = useSelector(state => state.board);

  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCnt, setTotalCnt] = useState(0);

  const moveToWrite = () => {
    navigate('/board/write');
  };

  const backToHome = () => {
    navigate('/');
  };

  const handlePageChange = (newPage) => {
    setPage(newPage);
  }

  useEffect(() => {
    dispatch(boardPage(page));
  }, [dispatch, page]);

  useEffect(() => {
    if (boardData.boardPage) {
      setTotalPages(boardData.boardPage.totalPages);
      setTotalCnt(boardData.boardPage.totalElements);
    }
  }, [boardData.boardPage]);

  return (
    <Layout>
      <div>
        {boardData.boardPage && boardData.boardPage.content.map((board) => (
          <Card key={board.id}>
            <Card.Body>
              <Link to={`/board/${board.id}`}>
                <Card.Title>{board.title}</Card.Title>
              </Link>
              <Card.Text>{board.member}</Card.Text>
              <p className="text-muted">작성일자: {new Date(board.createdAt).toLocaleString()}</p>
              <p className="text-muted">조회수: {board.hits}</p>
            </Card.Body>
          </Card>
        ))}
      </div>

      <Pagination
        activePage={page}
        itemsCountPerPage={pageSize}
        totalItemsCount={totalCnt}
        pageRangeDisplayed={5}
        prevPageText={"<"}
        nextPageText={">"}
        onChange={handlePageChange}
      />

      <Button variant="primary" onClick={ moveToWrite }>게시글 작성</Button>
      <Button variant="primary" onClick={ backToHome }>뒤로가기</Button>
    </Layout>
  );
};

export default BoardList;