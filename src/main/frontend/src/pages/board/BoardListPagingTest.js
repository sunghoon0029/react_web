import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Pagination from '../../components/Pagination';

const BoardListPagingTest = () => {
    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);

    useEffect(() => {
        fetchData();
    }, [currentPage]);

    const fetchData = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/board/page?page=${currentPage}&size=${pageSize}`);
            console.log(response.data.content);

            setData(response.data.content);
        } catch (error) {
            console.error(error);
        }
    };

    const onPageChange = (newPage) => {
        setCurrentPage(newPage);
    };

  return (
    <div>
        {data.map((item) => (
            <div key={item.id}>{item.name}</div>
        ))}

        <Pagination
            currentPage={currentPage}
            pageSize={pageSize}
            totalItems={100}
            onPageChange={onPageChange}
        />
    </div>
  );
};

export default BoardListPagingTest;