package com.dingdong.lastdance_s.repository.voting;


import com.dingdong.lastdance_s.entity.voting.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Integer> {
}
