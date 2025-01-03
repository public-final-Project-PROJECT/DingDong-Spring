package com.dingdong.lastdance_s.service;

import jakarta.transaction.Transactional;
import com.dingdong.lastdance_s.entity.voting.Voting;
import com.dingdong.lastdance_s.entity.voting.VotingContents;
import com.dingdong.lastdance_s.entity.voting.VotingRecord;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.StudentsRepository;
import com.dingdong.lastdance_s.repository.voting.VotingContentsRepository;
import com.dingdong.lastdance_s.repository.voting.VotingRecordRepository;
import com.dingdong.lastdance_s.repository.voting.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class  VotingService {

    @Autowired
    private VotingRepository votingRepository;
    @Autowired
    private VotingContentsRepository votingContentsRepository;

    @Autowired
    private VotingRecordRepository votingRecordRepository;
    @Autowired
    private StudentsRepository studentsRepository;


    // 투표 insert
    public Voting saveVoting(Map<String, Object> voteData) {

        Voting voting = new Voting();
        voting.setClassId((Integer) voteData.get("classId")); // 학급 id
        voting.setVotingName(voteData.get("votingName").toString()); // 제목
        voting.setVotingDetail(voteData.get("detail").toString()); // 설명
        // String votingEndObj = (String)voteData.get("votingEnd");

        LocalDateTime date = null;

        if (voteData.get("votingEnd") != null) {
          // voting.setVotingEnd((LocalDateTime) votingEndObj);
            String votingEnd = (String)voteData.get("votingEnd");

                date = LocalDate.parse(votingEnd).atStartOfDay();
                voting.setVotingEnd(date);

        }

        voting.setCreatedAt(LocalDateTime.now()); // 생성일
        voting.setVote(true); // 투표 진행 여부
        voting.setAnonymousVote((Boolean) voteData.get("anonymousVote")); // 비밀 투표 여부
        voting.setDoubleVote((Boolean) voteData.get("doubleVote")); // 중복 투표 가능 여부
        Voting result = votingRepository.save(voting);
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

    public List<Voting> findByClassId(int classId) {

        List<Voting> result = votingRepository.findByClassId(classId);
        System.out.println("트표 조회 중 서비스");

        if (result.size() > 0) {
            return result;
        }
        return Collections.emptyList();
    }

    public List<VotingContents> findByVotingId(int votingId) {

        List<VotingContents> result = votingContentsRepository.findByVotingId(votingId);
        if (result.size() > 0) {
            return result;
        }
        return Collections.emptyList();
    }

    public boolean saveVotingRecord(Map<String, Object> voteData) {

        int votingId = (int)voteData.get("votingId");
        int studentId = (int)voteData.get("studentId");
        int contentsId = (int)voteData.get("contentsId");
        System.out.println(" 잘넘어오나아아아: " + studentId+votingId+contentsId );

        VotingRecord vr = new VotingRecord();
        vr.setVotingId(votingId);
        vr.setStudentId(studentId);
        vr.setContentsId(contentsId);

        VotingRecord result = votingRecordRepository.save(vr);
        System.out.println("저장 결과 ! : " + result);
       if(result != null) {
           return true;
       }
       return false;
    }

    public boolean updateIsVote(int votingId) {

        Voting voting = (Voting) votingRepository.findByVotingId(votingId);
        voting.setVote(false); // 투표 상태 (진행종료로) 바꿔주기
        Voting result2 = votingRepository.save(voting);
        if(result2 != null){
            return true;
        }
        return false;
    }
//
//    public List<VotingRecord> findByStudentId(int votingId, int studentId) {
//
//        List<VotingRecord> result = votingRecordRepository.findByStudentId(studentId);
//        if (result.size() > 0) {
//            return result;
//        }
//        return Collections.emptyList();
//    }

    public List<Students> findByStudentsName(int classId) {

        List<Students> result = studentsRepository.findByClassId(classId);
        if (result.size() > 0) {
            return result;
        }
        return Collections.emptyList();
    }

//    @Transactional
//    public void updateExpiredVotings() {
//        // 현재 날짜/시간 가져오기
//        LocalDateTime now = LocalDateTime.now();
//
//        // 만료된 투표 조회
//        List<Voting> expiredVotings = votingRepository.findExpiredVoting(now);
//
//        if (!expiredVotings.isEmpty()) {
//            // 만료된 투표 ID 목록 추출
//            List<Integer> expiredIds = expiredVotings.stream().map(Voting::getId).toList();
//
//            // is_vote를 false로 업데이트
//            votingRepository.updateIsVoteToFalse(expiredIds);
//        }
//    }
}
