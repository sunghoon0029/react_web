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
import com.project.web.repository.FileRepository;
import com.project.web.repository.MemberRepository;
import com.project.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Value("${file.dir}")
    private String fileDir;

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;
    private final FileHandler fileHandler;

    public BoardWriteResponse save(BoardRequest request, CustomUserDetails member) throws Exception {

        Board board = BoardRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        board.setMember(writer);
        Board saveBoard = boardRepository.save(board);

        return BoardWriteResponse.toDTO(saveBoard);
    }

//    @Transactional
//    public BoardWriteResponse saveWithFile(BoardRequest request, List<MultipartFile> files, CustomUserDetails member) throws Exception {
//
//        Board board = BoardRequest.toEntity(request);
//
//        Member writer = memberRepository.findByEmail(member.getUsername())
//                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
//
//        board.setMember(writer);
//        Board saveBoard = boardRepository.save(board);
//
//        if (files != null && !files.isEmpty()) {
//
//            List<File> uploadFiles = fileService.uploadFiles(files, saveBoard.getId());
//
//            for (File file : uploadFiles) {
//                saveBoard.addFile(file);
//            }
//        }
//        return BoardWriteResponse.toDTO(saveBoard);
//    }

//    @Transactional
//    public BoardWriteResponse saveWithFile(BoardRequest request, List<MultipartFile> files, CustomUserDetails member) throws Exception {
//
//        Board board = BoardRequest.toEntity(request);
//
//        Member writer = memberRepository.findByEmail(member.getUsername())
//                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
//
//        board.setMember(writer);
//
//        List<File> fileList = fileHandler.parseFileInfo(files);
//
//        if (!fileList.isEmpty()) {
//            for (File file : fileList) {
//                board.addFile(fileRepository.save(file));
//            }
//        }
//
//        Board saveBoard = boardRepository.save(board);
//
//        return BoardWriteResponse.toDTO(saveBoard);
//    }

//    @Transactional
//    public Long test(BoardRequest request, List<MultipartFile> multipartFiles, CustomUserDetails member) throws Exception {
//
//        Board board = BoardRequest.toEntity(request);
//
//        Member writer = memberRepository.findByEmail(member.getUsername())
//                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));
//
//        board.setMember(writer);
//
//        Long id = boardRepository.save(board).getId();
//
//        if (!CollectionUtils.isEmpty(multipartFiles)) {
//
//            for (MultipartFile multipartFile : multipartFiles) {
//
//                String originalFileName = multipartFile.getOriginalFilename();
//                String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
//                String filePath = fileDir + originalFileName;
//                long fileSize = multipartFile.getSize();
//                String extension = multipartFile.getContentType();
//
//                File file = new File(originalFileName, storedFileName, filePath, fileSize, extension);
//                file.setBoard(board);
//                fileRepository.save(file);
//
//                multipartFile.transferTo(new java.io.File(filePath));
//            }
//        }
//
//        return id;
//    }

    @Transactional
    public Long saveWithFile(BoardRequest request, List<MultipartFile> multipartFiles, CustomUserDetails member) throws Exception {

        Board board = BoardRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        board.setMember(writer);

        Long id = boardRepository.save(board).getId();

        fileService.uploadFiles(board, multipartFiles);

        return id;
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
