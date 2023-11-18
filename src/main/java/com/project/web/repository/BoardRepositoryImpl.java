package com.project.web.repository;

import com.project.web.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    @Override
    public void updateHits(Long id) {

        QBoard board = QBoard.board;

        jpaQueryFactory.update(board)
                .where(board.id.eq(id))
                .set(board.hits, board.hits.add(1))
                .execute();
    }
}
