package com.dingdong.lastdance_s.repository.voting;


import com.dingdong.lastdance_s.entity.voting.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Integer> {

    List<Voting> findByClassId(int id);

    List<Voting> findById(int id);

    Object findByVotingId(int votingId);



//    void updateIsVoteToFalse(List<Integer> expiredIds);
//
//    List<Voting> findExpiredVoting(LocalDateTime now);
}
