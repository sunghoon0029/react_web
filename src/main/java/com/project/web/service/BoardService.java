package com.project.web.service;

import com.project.web.dto.request.BoardRequest;
import com.project.web.dto.response.BoardResponse;
import com.project.web.entity.Board;
import com.project.web.entity.Member;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.MemberRepository;
import com.project.web.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

//    public boolean save(BoardRequest request) throws Exception {
//
//        Member member = memberRepository.findById(request.getMemberId())
//                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
//
//        Board board = request.toEntity(member);
//
//        boardRepository.save(board);
//
//        return true;
//    }

    public BoardResponse write(BoardRequest request, Member member) {

        Board board = BoardRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("사용자 정보를 찾을 수 없습니다."));

        board.setMapping(writer);
        Board saveBoard = boardRepository.save(board);

        return BoardResponse.toDTO(saveBoard, writer.getName());
    }

//    public List<BoardResponse> findAll() {
//
//        List<Board> boardList = boardRepository.findAll();
//        List<BoardResponse> boardResponseList = new ArrayList<>();
//
//        for (Board board: boardList) {
//            boardResponseList.add(BoardResponse.toDTO(board));
//        }
//
//        return boardResponseList;
//    }
//
//    public BoardResponse findById(Long id) throws Exception {
//
//        Board board = boardRepository.findById(id)
//                .orElseThrow(() -> new  Exception("게시글 정보를 찾을 수 없습니다."));
//        boardRepository.updateHits(board.getId());
//        BoardResponse response = BoardResponse.toDTO(board);
//
//        return response;
//    }

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
