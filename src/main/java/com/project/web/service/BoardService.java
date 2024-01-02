package com.project.web.service;

import com.project.web.dto.request.board.BoardRequest;
import com.project.web.dto.response.board.BoardDetailResponse;
import com.project.web.dto.response.board.BoardListResponse;
import com.project.web.dto.response.board.BoardUpdateResponse;
import com.project.web.dto.response.board.BoardWriteResponse;
import com.project.web.entity.Board;
import com.project.web.entity.File;
import com.project.web.entity.Member;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.MemberRepository;
import com.project.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    public BoardWriteResponse save(BoardRequest request, CustomUserDetails member) throws Exception {

        Board board = BoardRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        board.setMember(writer);
        Board saveBoard = boardRepository.save(board);

        return BoardWriteResponse.toDTO(saveBoard);
    }

    public BoardWriteResponse saveWithFile(BoardRequest request, List<MultipartFile> files, CustomUserDetails member) throws Exception {

        Board board = BoardRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        board.setMember(writer);
        Board saveBoard = boardRepository.save(board);

        if (!files.isEmpty()) {

            List<File> uploadFiles = fileService.uploadFiles(files);
            saveBoard.setFiles(uploadFiles);
        }

        boardRepository.save(saveBoard);

        return BoardWriteResponse.toDTO(saveBoard);
    }

    public List<BoardListResponse> findAll() {

        List<Board> boardList = boardRepository.findAll();
        List<BoardListResponse> boardListResponseList = new ArrayList<>();

        for (Board board: boardList) {
            boardListResponseList.add(BoardListResponse.toDTO(board));
        }

        return boardListResponseList;
    }

    public Page<BoardListResponse> page(Pageable pageable) {

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(BoardListResponse::toDTO);
    }

    public BoardDetailResponse findById(Long id) throws Exception {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new  Exception("게시글 정보를 찾을 수 없습니다."));
        boardRepository.updateHits(board.getId());
        BoardDetailResponse response = BoardDetailResponse.toDTO(board);

        return response;
    }

    public BoardUpdateResponse updateById(Long id, BoardRequest request, CustomUserDetails member) throws Exception {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));

        Member loggedInMember = memberRepository.findByEmail(member.getUsername())
                        .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        if (!board.getMember().equals(loggedInMember)) {
            throw new AccessDeniedException("사용자가 일치 하지 않습니다.");
        }

        board.update(request.getTitle(), request.getContents());
        Board saveBoard = boardRepository.save(board);

        return BoardUpdateResponse.toDTO(saveBoard);
    }

    public boolean deleteById(Long id) {

        boardRepository.deleteById(id);

        return true;
    }
}
