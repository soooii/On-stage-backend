package com.team5.on_stage.concert.repository;

import com.team5.on_stage.concert.entity.ConcertInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertInfoRepository extends JpaRepository<ConcertInfo,Long> {
    boolean existsByMt20id(String mt20id); // 유니크 키 확인 메서드
}
