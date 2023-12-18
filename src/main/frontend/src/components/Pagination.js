import React from 'react'

const Pagination = ({ currentPage, pageSize, totalItems, onPageChange }) => {
    const totalPages = Math.ceil(totalItems / pageSize);
    const pages = Array.from({ length: totalPages }, (_, index) => index + 1);

  return (
    <div>
        {pages.map((page) => (
            <button key={page} onClick={() => onPageChange(page)}>{page}</button>
        ))}
    </div>
  );
};

export default Pagination;