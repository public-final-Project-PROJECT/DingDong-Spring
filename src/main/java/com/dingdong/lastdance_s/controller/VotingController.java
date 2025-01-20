package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.entity.voting.Voting;
import com.dingdong.lastdance_s.entity.voting.VotingContents;
import com.dingdong.lastdance_s.entity.voting.VotingRecord;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.voting.VotingRecordRepository;
import com.dingdong.lastdance_s.service.VotingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;

@RestController
@RequestMapping("api/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @Autowired
    private VotingRecordRepository votingRecordRepository;


    @PostMapping("newvoting")
    public ResponseEntity<Object> votingInsert(
            @RequestBody Map<String, Object> voteData){


        Voting voting = votingService.saveVoting(voteData);
        if(voting != null){
            String listId = String.valueOf(voting.getId());

            boolean votingContents = votingService.saveVotingContents(voteData, valueOf(listId));

            if(votingContents && voting != null){
                return ResponseEntity.ok("등록 성공");
            }
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.status(500).body(null);
    }


    @PostMapping("findVoting")
    public ResponseEntity<Object> findVoting(
            @RequestBody Map<String, Object> voteData){

        try {
            int classId = (int) voteData.get("classId");

            List<Voting> result = votingService.findByClassId(classId);
            if (result != null && !result.isEmpty()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(404).body("No voting data found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred");
        }
    }


    @PostMapping("findContents")
    public ResponseEntity<Object> findContents(
            @RequestBody Map<String, Object> voteData
    ){
        int votingId = (int) voteData.get("votingId");

        List<VotingContents> result = votingService.findByVotingId(votingId);
        if(result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(500).body(null);
    }


    @PostMapping("uservoteinsert")
    public ResponseEntity<Object> userVoteInsert(
            @RequestBody Map<String, Object> voteData
    ){

        boolean result = votingService.saveVotingRecord(voteData);
        if(result){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(500).body(null);

    }

    @PostMapping("findUserVoting")
    public ResponseEntity<Object> findUserVoting(
            @RequestBody Map<String, Object> voteData
    ){

        int votingId = (int) voteData.get("votingId");
        int studentId = (int) voteData.get("studentId");

        List<VotingRecord> result = votingRecordRepository.findByVotingId(votingId);

        if(result == null){
            return ResponseEntity.status(500).body(null);
        }else{
            result.getClass();
            result.toString();
            List<Integer> userVote = new ArrayList<>();
            for(VotingRecord user : result){
                if(user.getStudentId() == studentId){
                    userVote.add(user.getVotingId());
                    userVote.add(user.getContentsId());
                }
            }
            return ResponseEntity.ok(userVote);
        }
    }


    @PostMapping("VoteOptionUsers")
    public ResponseEntity<Object> VoteOptionUsers(
            @RequestBody Map<String, Object> voteData
    ){

        int votingId = (int) voteData.get("votingId");

        List<VotingRecord> result = votingRecordRepository.findByVotingId(votingId);

        if(result == null){
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.ok(result);

    }


    @PostMapping("findStudentsName")
    public ResponseEntity<Object> findStudentsName (
            @RequestBody Map<String, Object> voteData
    ){

        int classId = (int) voteData.get("classId");
        List<Students> studentsList = votingService.findByStudentsName(classId);

        if(studentsList.isEmpty()){
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.ok(studentsList);

    }


    @PostMapping("isVoteUpdate")
    public ResponseEntity<Object> isVoteUpdate(
            @RequestBody Map<String, Object> voteData
    ){
        int votingId = (int) voteData.get("votingId");
        boolean result = votingService.updateIsVote(votingId);
        if(result){
            return ResponseEntity.ok("투표가 성공적으로 종료되었습니다.");
        }
        return ResponseEntity.status(500).body(null);
    }


    @Transactional
    @PostMapping("deleteVoting")
    public ResponseEntity<Object> deleteVoting(
            @RequestBody Map<String, Object> voteData
    ){

        int votingId = (int) voteData.get("votingId");
        boolean result = votingService.deleteVoting(votingId);
        if(result){
            return ResponseEntity.ok("투표 삭제 성공.");
        }
        return ResponseEntity.status(500).body(null);
    }

    @PostMapping("votingNameSearch")
    public ResponseEntity<Object> votingNameSearch(
            @RequestBody Map<String, Object> voteData
    ){
        System.out.println("투표 이름 검색");
        int votingId = (int) voteData.get("votingId");
        System.out.println(votingId);
        Voting voting = (Voting) votingService.findByClassIdAndVotingName(votingId);
        String votingName = voting.getVotingName();
        System.out.println("투표 이름 : " + votingName); ;
        if(voting == null){
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.ok(votingName);
    }


}
