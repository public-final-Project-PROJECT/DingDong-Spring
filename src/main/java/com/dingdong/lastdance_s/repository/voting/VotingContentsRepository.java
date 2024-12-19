package com.dingdong.lastdance_s.repository.voting;


import com.dingdong.lastdance_s.model.voting.VotingContents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingContentsRepository extends JpaRepository<VotingContents, Integer> {
}
