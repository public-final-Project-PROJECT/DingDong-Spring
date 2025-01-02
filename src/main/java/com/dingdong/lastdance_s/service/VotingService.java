package com.dingdong.lastdance_s.service;


import com.dingdong.lastdance_s.entity.voting.Voting;
import com.dingdong.lastdance_s.entity.voting.VotingContents;
import com.dingdong.lastdance_s.entity.voting.VotingRecord;
import com.dingdong.lastdance_s.repository.voting.VotingContentsRepository;
import com.dingdong.lastdance_s.repository.voting.VotingRecordRepository;
import com.dingdong.lastdance_s.repository.voting.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    // 투표 insert
    public Voting saveVoting(Map<String, Object> voteData) {

        Voting voting = new Voting();
        voting.setClassId((Integer) voteData.get("classId")); // 학급 id
        voting.setVotingName(voteData.get("votingName").toString()); // 제목
        voting.setVotingDetail(voteData.get("detail").toString()); // 설명

        Object votingEndObj = voteData.get("votingEnd");
        if (votingEndObj != null) {
            if (!(votingEndObj instanceof LocalDateTime)) {
                throw new IllegalArgumentException("... " + votingEndObj);
            }
            voting.setVotingEnd((LocalDateTime) votingEndObj);
        } else {
            voting.setVotingEnd(null);
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

        VotingRecord vr = new VotingRecord();
        vr.setVotingId(votingId);
        vr.setStudentId(studentId);
        vr.setContentsId(contentsId);

        List<VotingRecord> result = Collections.singletonList(votingRecordRepository.save(vr));
        System.out.println("저장 결과 ! : " + result);
        if (result.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateIsVote(int votingId) {

        Voting result = (Voting) votingRepository.findById(votingId);
        result.setVote(false); // 투표 진행 상태 바꿔주기
        votingRepository.save(result);
        return true;
    }

    public List<VotingRecord> findByStudentId(int votingId, int studentId) {

        List<VotingRecord> result = votingRecordRepository.findByStudentId(studentId);
        if (result.size() > 0) {
            return result;
        }
        return Collections.emptyList();
    }
}
