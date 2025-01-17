package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.Alert;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

//    @Autowired
//    private AlertRe alertRepository;
//

    // 투표 insert
    public Voting saveVoting(Map<String, Object> voteData) {
        Voting voting = new Voting();
        voting.setClassId((Integer) voteData.get("classId")); // 학급 id
        voting.setVotingName(voteData.get("votingName").toString()); // 제목
        voting.setVotingDetail(voteData.get("detail").toString()); // 설명


        String votingEndStr = (String) voteData.get("votingEnd");
        LocalDateTime date = null;

        if (votingEndStr != null && !votingEndStr.equals("no")) {
            try {

                String cleanedDate = votingEndStr.replace(".000", "");


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


                date = LocalDateTime.parse(cleanedDate, formatter);


                voting.setVotingEnd(date);

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format: " + votingEndStr);

            }
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

        VotingRecord vr = new VotingRecord();
        vr.setVotingId(votingId);
        vr.setStudentId(studentId);
        vr.setContentsId(contentsId);

        VotingRecord result = votingRecordRepository.save(vr);

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
        System.out.println("학생 name 조회 결과 :: " + result);
        if (result.size() > 0) {
            return result;
        }
        return Collections.emptyList();
    }

    public boolean deleteVoting(int votingId) {
        try {
            // int id = votingId;
            votingRecordRepository.deleteByVotingId(votingId);
            votingContentsRepository.deleteByVotingId(votingId);
            votingRepository.deleteById(votingId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("해당 투표 id 를 찾지 못함 : " + votingId);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//
//    public Alert nonVotingAlertSave(int classId, int studentId, int votingId) {
//        return
//    }
//

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
