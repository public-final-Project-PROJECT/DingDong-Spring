package com.dingdong.lastdance_s.repository.voting;


import com.dingdong.lastdance_s.model.voting.VotingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRecordRepository extends JpaRepository<VotingRecord, Integer> {
}
