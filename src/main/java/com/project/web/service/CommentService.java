package com.project.web.service;

import com.project.web.dto.request.comment.CommentRequest;
import com.project.web.dto.response.comment.CommentListResponse;
import com.project.web.dto.response.comment.CommentUpdateResponse;
import com.project.web.dto.response.comment.CommentWriteResponse;
import com.project.web.entity.Board;
import com.project.web.entity.Comment;
import com.project.web.entity.Member;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.CommentRepository;
import com.project.web.repository.MemberRepository;
import com.project.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public CommentWriteResponse save(Long id, CommentRequest request, CustomUserDetails member) throws Exception {

        Comment comment = CommentRequest.toEntity(request);

        Member writer = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));

        comment.setMember(writer);
        comment.setBoard(board);

        Comment saveComment = commentRepository.save(comment);

        return CommentWriteResponse.toDTO(saveComment);
    }

    public List<CommentListResponse> findAll() {

        List<Comment> commentList = commentRepository.findAll();
        List<CommentListResponse> commentListResponseList = new ArrayList<>();

        for (Comment comment: commentList) {
            commentListResponseList.add(CommentListResponse.toDTO(comment));
        }

        return commentListResponseList;
    }

    public List<CommentListResponse> findByBoard(Long id) throws Exception {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글 정보를 찾을 수 없습니다."));

        List<Comment> commentList = commentRepository.findByBoard(board);
        List<CommentListResponse> commentListResponseList = new ArrayList<>();

        for (Comment comment: commentList) {
            commentListResponseList.add(CommentListResponse.toDTO(comment));
        }

        return commentListResponseList;
    }

    public CommentUpdateResponse updateById(Long id, CommentRequest request, CustomUserDetails member) throws Exception {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("댓글 정보를 찾을 수 없습니다."));

        Member loggedInMember = memberRepository.findByEmail(member.getUsername())
                .orElseThrow(() -> new Exception("사용자 정보를 찾을 수 없습니다."));

        if (!comment.getMember().equals(loggedInMember)) {
            throw new AccessDeniedException("사용자가 일치 하지 않습니다.");
        }

        comment.update(request.getContents());
        Comment updateComment = commentRepository.save(comment);

        return CommentUpdateResponse.toDTO(updateComment);
    }

    public boolean deleteById(Long id) {

        commentRepository.deleteById(id);

        return true;
    }
}
