package com.project.web.service;

import com.project.web.dto.request.board.BoardRequest;
import com.project.web.dto.response.board.BoardDetailResponse;
import com.project.web.dto.response.board.BoardListResponse;
import com.project.web.dto.response.board.BoardWriteResponse;
import com.project.web.entity.Board;
import com.project.web.entity.Member;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.MemberRepository;
import com.project.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardWriteResponse save(BoardRequest request, CustomUserDetails member) throws Exception {

        Board board = BoardRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        board.setMapping(writer);
        Board saveBoard = boardRepository.save(board);

        return BoardWriteResponse.toDTO(saveBoard, writer.getId());
    }

    public List<BoardListResponse> findAll() {

        List<Board> boardList = boardRepository.findAll();
        List<BoardListResponse> boardListResponseList = new ArrayList<>();

        for (Board board: boardList) {
            boardListResponseList.add(BoardListResponse.toDTO(board, board.getMember().getName()));
        }

        return boardListResponseList;
    }

    public BoardDetailResponse findById(Long id) throws Exception {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new  Exception("게시글 정보를 찾을 수 없습니다."));
        boardRepository.updateHits(board.getId());
        BoardDetailResponse response = BoardDetailResponse.toDTO(board, board.getMember().getName());

        return response;
    }

    public boolean updateById(Long id, BoardRequest request) throws Exception {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));
        board.updateBoard(request.getTitle(), request.getContents());
        boardRepository.save(board);

        return true;
    }

    public boolean deleteById(Long id) {

        boardRepository.deleteById(id);

        return true;
    }
}
