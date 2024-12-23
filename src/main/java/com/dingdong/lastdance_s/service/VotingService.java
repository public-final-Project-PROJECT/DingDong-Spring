package com.dingdong.lastdance_s.service;


import com.dingdong.lastdance_s.entity.voting.Voting;
import com.dingdong.lastdance_s.entity.voting.VotingContents;
import com.dingdong.lastdance_s.repository.voting.VotingContentsRepository;
import com.dingdong.lastdance_s.repository.voting.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class VotingService {

    @Autowired
    VotingRepository votingRepository;
    @Autowired
    private VotingContentsRepository votingContentsRepository;


    // 투표 insert
    public List<Voting> saveVoting(Map<String, Object> voteData) {

        Voting voting = new Voting();
        voting.setClassId((Integer) voteData.get("classId")); // 학급 id
        voting.setVotingName(voteData.get("votingName").toString()); // 제목
        voting.setVotingDetail(voteData.get("votingDetail").toString()); // 설명
        voting.setVotingEnd((LocalDateTime) voteData.get("votingEnd")); // 마감일자
        voting.setCreatedAt(LocalDateTime.now()); // 생성일
        voting.isVote(true);
        voting.setAnonymousVote((Boolean) voteData.get("anonymousVote")); // 비밀 투표 여부
        voting.setDoubleVote((Boolean) voteData.get("doubleVote")); // 중복 투표 가능 여부
        List<Voting> result = Collections.singletonList(votingRepository.save(voting));
        return result;
    }

    // 투표 항목 insert
    public Boolean  saveVotingContents(Map<String, Object> voteData, Integer integer) {

        List<String> contents = (List<String>) voteData.get("contents"); // 항목

        for (String content : contents) {
            System.out.println("Service 의 contents :: " + content);
            // 매변 새로운 객체를 생성
            VotingContents votingContents = new VotingContents();
            votingContents.setVotingId(integer);
            votingContents.setVotingContents(content);

            votingContentsRepository.save(votingContents);
        }
       return true;
    }

}
