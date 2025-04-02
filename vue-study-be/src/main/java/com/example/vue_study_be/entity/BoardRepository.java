package com.example.vue_study_be.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
    Page<BoardEntity> findAllByOrderByIdxDesc(Pageable pageable);
    // 순서대로 검색쿼리가 존재할때 호출할 JPA-_-라고 상정해본다 >>> 이유는 모르지만 이게 된다?!
    Page<BoardEntity> findByAuthorOrderByIdxDesc(Pageable pageable, String sv);
    Page<BoardEntity> findByTitleOrderByIdxDesc(Pageable pageable, String sv);
    Page<BoardEntity> findByContentsContainingOrderByIdxDesc(Pageable pageable, String sv);
}