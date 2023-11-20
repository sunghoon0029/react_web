package com.project.web.dto.request;

import com.project.web.entity.Board;
import lombok.Getter;

@Getter
public class BoardRequest {

    private Long id;

    private String title;

    private String contents;

    public Board toEntity(BoardRequest request) {
        return Board.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .build();
    }
}
