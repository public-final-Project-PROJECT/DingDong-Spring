package com.dingdong.lastdance_s.entity.voting;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "voting")
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // 투표 고유 id

    @Column(name = "class_id")
    private int classId;  // 학급 고유 id

    @Column(name = "voting_name")
    private String votingName;  // 투표 제목

    @Column(name = "voting_detail")
    private String votingDetail;  // 투표 설명

    @Column(name = "voting_end")
    private LocalDateTime votingEnd;  // 투표 마감일

    @Column(name = "created_at")
    private LocalDateTime createdAt;  // 투표 생성일

    @Column(name = "is_vote")
    private boolean isVote;  // 투표 진행 여부

    @Column(name = "anonymous_vote")
    private boolean anonymousVote;  // 비밀 투표 여부

    @Column(name = "double_voting")
    private boolean doubleVote;  // 중복 투표 가능 여부

    public Voting() {
    }

    public Voting(int id, int classId, String votingName, String votingDetail, LocalDateTime votingEnd, LocalDateTime createdAt, boolean isVote, boolean anonymousVote, boolean doubleVote) {
        this.id = id;
        this.classId = classId;
        this.votingName = votingName;
        this.votingDetail = votingDetail;
        this.votingEnd = votingEnd;
        this.createdAt = createdAt;
        this.isVote = isVote;
        this.anonymousVote = anonymousVote;
        this.doubleVote = doubleVote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getVotingName() {
        return votingName;
    }

    public void setVotingName(String votingName) {
        this.votingName = votingName;
    }

    public String getVotingDetail() {
        return votingDetail;
    }

    public void setVotingDetail(String votingDetail) {
        this.votingDetail = votingDetail;
    }

    public LocalDateTime getVotingEnd() {
        return votingEnd;
    }

    public void setVotingEnd(LocalDateTime votingEnd) {
        this.votingEnd = votingEnd;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isVote(boolean b) {
        return isVote;
    }

    public void setVote(boolean vote) {
        isVote = vote;
    }

    public boolean isAnonymousVote() {
        return anonymousVote;
    }

    public void setAnonymousVote(boolean anonymousVote) {
        this.anonymousVote = anonymousVote;
    }

    public boolean isDoubleVote() {
        return doubleVote;
    }

    public void setDoubleVote(boolean doubleVote) {
        this.doubleVote = doubleVote;
    }

    @Override
    public String toString() {
        return "Voting{" +
                "id=" + id +
                ", classId=" + classId +
                ", votingName='" + votingName + '\'' +
                ", votingDetail='" + votingDetail + '\'' +
                ", votingEnd=" + votingEnd +
                ", createdAt=" + createdAt +
                ", isVote=" + isVote +
                ", anonymousVote=" + anonymousVote +
                ", doubleVote=" + doubleVote +
                '}';
    }
}
