package com.sparta.BoardAPI2.service;

import com.sparta.BoardAPI2.dto.BoardListResponseDto;
import com.sparta.BoardAPI2.dto.BoardRequestDto;
import com.sparta.BoardAPI2.dto.BoardResponseDto;
import com.sparta.BoardAPI2.entity.Board;
import com.sparta.BoardAPI2.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 글 생성
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 모든 글 가져오기
    public List<BoardListResponseDto> findAllBoard() {
        List<Board> boardList = boardRepository.findAll();
        if(boardList.isEmpty()){
            throw new IllegalStateException("게시글이 없습니다.");
        }
        List<BoardListResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardListResponseDto(board));
        }
        return responseDtoList;
    }

    // 글 하나 가져오기
    public BoardResponseDto findOneBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return new BoardResponseDto(board);
    }

    // 글 수정
    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board.update(requestDto);
        return board.getId();
    }

    // 글 삭제
    @Transactional
    public Long deleteBoard(Long id) {
        if(!boardRepository.existsById(id)){
            throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.");
        }
        boardRepository.deleteById(id);
        return id;
    }

    // 비밀번호 확인
    public boolean checkPassword(Long id, String inputPassword){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return board.getPassword().equals(inputPassword);
    }
}
