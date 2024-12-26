package com.dingdong.lastdance_s.repository.voting;


import com.dingdong.lastdance_s.entity.voting.VotingContents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingContentsRepository extends JpaRepository<VotingContents, Integer> {
    List<VotingContents> findByVotingId(int votingId);
}
