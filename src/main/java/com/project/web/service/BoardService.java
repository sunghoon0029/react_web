package com.project.web.service;

import com.project.web.dto.request.board.BoardRequest;
import com.project.web.dto.response.board.BoardDetailResponse;
import com.project.web.dto.response.board.BoardListResponse;
import com.project.web.dto.response.board.BoardWriteResponse;
import com.project.web.entity.Board;
import com.project.web.entity.File;
import com.project.web.entity.Member;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.MemberRepository;
import com.project.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
    private final FileTestService fileService;

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

        List<String> fileNames = fileService.uploadFiles(files);

        for (String fileName: fileNames) {
            File file = File.builder()
                    .originalFileName(fileName)
                    .filePath(fileService.getUploadDir() + java.io.File.separator + fileName)
                    .board(saveBoard)
                    .build();

            saveBoard.getFiles().add(file);
        }

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

    public boolean updateById(Long id, BoardRequest request, CustomUserDetails member) throws Exception {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));

        Member loggedInMember = memberRepository.findByEmail(member.getUsername())
                        .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        if (!board.getMember().equals(loggedInMember)) {
            throw new AccessDeniedException("사용자가 일치 하지 않습니다.");
        }

        board.update(request.getTitle(), request.getContents());
        boardRepository.save(board);

        return true;
    }

    public boolean deleteById(Long id) {

        boardRepository.deleteById(id);

        return true;
    }
}
