package com.dingdong.lastdance_s.repository.voting;


import com.dingdong.lastdance_s.entity.voting.VotingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingRecordRepository extends JpaRepository<VotingRecord, Integer> {
    List<VotingRecord> findByStudentId(int studentId);

    List<VotingRecord> findByVotingId(int votingId);
}
