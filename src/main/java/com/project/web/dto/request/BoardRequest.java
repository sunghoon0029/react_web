package com.project.web.dto.request;

import com.project.web.entity.Board;
import lombok.Getter;

@Getter
public class BoardRequest {

    private String title;
    private String contents;

    public static Board toEntity(BoardRequest request) {
        return Board.builder()
                .title(request.title)
                .contents(request.contents)
                .build();
    }
}
