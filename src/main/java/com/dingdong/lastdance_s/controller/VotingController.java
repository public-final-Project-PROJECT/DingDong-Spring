package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.model.voting.Voting;
import com.dingdong.lastdance_s.model.voting.VotingContents;
import com.dingdong.lastdance_s.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;

@RestController
@RequestMapping("api/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;


    // 투표 생성 메소드
    @PostMapping("newvoting")
    public ResponseEntity<Object> votingInsert(
            @RequestBody Map<String, Object> voteData){

        System.out.println("voteData :: " + voteData);
        // 1. voting 테이블 저장
        List<Voting> voting = votingService.saveVoting(voteData);
        if(voting != null){
            String listId =  voting.stream()

                    .map(Voting::getId)
                    .collect(Collectors.toSet())
                    .toString();
            listId = listId.replaceAll("[\\[\\]]", "");

            // 2. voting_contents 테이블 저장
            boolean votingContents = votingService.saveVotingContents(voteData, valueOf(listId));
        }

        if(voting != null){
            return ResponseEntity.ok(voting);
        }else{
            return ResponseEntity.status(500).body(null);
        }
    }
}
