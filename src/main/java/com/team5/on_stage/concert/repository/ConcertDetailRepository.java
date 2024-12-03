package com.team5.on_stage.concert.repository;

import com.team5.on_stage.concert.entity.ConcertDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertDetailRepository extends JpaRepository<ConcertDetail,Long> {
}
