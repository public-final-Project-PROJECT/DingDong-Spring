package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.voting.Voting;
import com.dingdong.lastdance_s.entity.voting.VotingContents;
import com.dingdong.lastdance_s.entity.voting.VotingRecord;
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
        Voting voting = votingService.saveVoting(voteData);
        if(voting != null){
            String listId = String.valueOf(voting.getId());
            System.out.println("listId :: " + listId);

            // 2. voting_contents 테이블 저장
            boolean votingContents = votingService.saveVotingContents(voteData, valueOf(listId));

           if(votingContents){
               System.out.println("votingContents :: " + voting);
           }
           if(votingContents && voting != null){
               return ResponseEntity.ok("등록 성공");
           }
           return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.status(500).body(null);
    }

    // 투표 list 조회 요청
    @PostMapping("findVoting")
    public ResponseEntity<Object> findVoting(
            @RequestBody Map<String, Object> voteData){
        int classId = (int) voteData.get("classId");
        System.out.println("투표 list 요청 넘어옴");

        List<Voting> result = votingService.findByClassId(classId);
        if(result != null){
            System.out.println("투표 정보 : " + result);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(500).body(null);
    }

    // 투표들의 항목 조회 요청
    @PostMapping("findContents")
    public ResponseEntity<Object> findContents(
            @RequestBody Map<String, Object> voteData
    ){
        System.out.println("투표 조회 넘어옴");
        int votingId = (int) voteData.get("votingId");

        List<VotingContents> result = votingService.findByVotingId(votingId);
        if(result != null){
            System.out.println(" 조회 한 항목 ::" + result);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(500).body(null);
    }

   // 사용자 투표 항목 저장 요청
    @PostMapping("uservoteinsert")
    public ResponseEntity<Object> userVoteInsert(
            @RequestBody Map<String, Object> voteData
    ){
        System.out.println("유저가 투표한거 넘엉옴");
        System.out.println("유저 투.정 저장 :: " + voteData);

        boolean result = votingService.saveVotingRecord(voteData);
        if(result){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(500).body(null);

    }

    // 해당 학생이 투표 한 항목 정보 조회
    @PostMapping("findUserVoting")
    public ResponseEntity<Object> findUserVoting(
            @RequestBody Map<String, Object> voteData
    ){
        System.out.println("학생 투표 정보 조회 넘어옴");
        int votingId = (int) voteData.get("votingId");
        int studentId = (int) voteData.get("studentId");

        List<VotingRecord> result = votingService.findByStudentId(votingId, studentId);
        if(result != null){
            System.out.println("학생 투표 값 :: " + result);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(500).body(null);
    }

//    // (투표 후) 각 항목들에 대한 유저의 투표 정보들
//    @PostMapping("VoteOptionUsers")
//    public ResponseEntity<Object> VoteOptionUsers(
//            @RequestBody Map<String, Object> voteData
//    )

    // 투표 종료 저장 요청(교사만 가능)
    @PostMapping("isVoteUpdate")
    public ResponseEntity<Object> isVoteUpdate(
            @RequestBody Map<String, Object> voteData
    ){
        int votingId = (int) voteData.get("votingId");
        boolean result = votingService.updateIsVote(votingId);
        if(result){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(500).body(null);
    }
}
