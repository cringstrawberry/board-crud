package com.sparta.BoardAPI2.repository;

import com.sparta.BoardAPI2.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
