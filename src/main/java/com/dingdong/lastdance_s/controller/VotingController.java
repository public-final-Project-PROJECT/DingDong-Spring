package com.dingdong.lastdance_s.controller;

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


    // 투표 생성 메소드
    @PostMapping("newvoting")
    public ResponseEntity<Object> votingInsert(
            @RequestBody Map<String, Object> voteData){

        // 1. voting 테이블 저장
        Voting voting = votingService.saveVoting(voteData);
        if(voting != null){
            String listId = String.valueOf(voting.getId());


            // 2. voting_contents 테이블 저장
            boolean votingContents = votingService.saveVotingContents(voteData, valueOf(listId));

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

        try {
            int classId = (int) voteData.get("classId");

            List<Voting> result = votingService.findByClassId(classId);
            if (result != null && !result.isEmpty()) {
                return ResponseEntity.ok(result); // 성공 응답
            } else {
                return ResponseEntity.status(404).body("No voting data found");
            }
        } catch (Exception e) {
            System.out.println("에러 발생: " + e.getMessage());
            return ResponseEntity.status(500).body("Error occurred");
        }
    }

    // 투표들의 항목 조회 요청
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

   // 사용자 투표 항목 저장 요청
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

    // 해당 학생이 투표 한 항목 정보 조회
    @PostMapping("findUserVoting")
    public ResponseEntity<Object> findUserVoting(
            @RequestBody Map<String, Object> voteData
    ){

        int votingId = (int) voteData.get("votingId");
        int studentId = (int) voteData.get("studentId");

        // 해당 voting 에 있는 투표 기록 가져온다.
        List<VotingRecord> result = votingRecordRepository.findByVotingId(votingId);

        if(result == null){
            return ResponseEntity.status(500).body(null);
        }else{

            result.getClass();
            result.toString();

            List<Integer> userVote = new ArrayList<>();
            for(VotingRecord user : result){
                if(user.getStudentId() == studentId){
                    userVote.add(user.getVotingId()); // 투표 id
                    userVote.add(user.getContentsId()); // 유저가 투표한 항목
                }
            }
            return ResponseEntity.ok(userVote);
        }
    }

    // (투표 후) 각 항목들에 대한 유저의 투표 정보들
    @PostMapping("VoteOptionUsers")
    public ResponseEntity<Object> VoteOptionUsers(
            @RequestBody Map<String, Object> voteData
    ){
        // 투표를 한 유저의 그 투표의 id 를 보낸거니까 해당 투표의 투표 유저들만 보내주면 됌

         int votingId = (int) voteData.get("votingId");

        // 1. 해당 투표의 모든 유저의 투표 정보를 가져온다.
        List<VotingRecord> result = votingRecordRepository.findByVotingId(votingId);

        // List<Integer> userVoteData = new ArrayList<>();

//        for(VotingRecord user : result){
//            userVoteData.add(user.getStudentId());
//        }
        if(result == null){
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.ok(result);

    }


    // 해당 학급에 속한 학생들의 이름, img 가지고오기
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

    // 투표 종료
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

    // 투표 삭제
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



}
