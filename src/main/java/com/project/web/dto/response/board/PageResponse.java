package com.project.web.dto.response.board;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageResponse {

    private List<BoardListResponse> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
