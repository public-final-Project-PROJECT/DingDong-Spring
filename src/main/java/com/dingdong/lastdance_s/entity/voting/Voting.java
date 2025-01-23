package com.dingdong.lastdance_s.entity.voting;


import aj.org.objectweb.asm.commons.Remapper;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "voting")
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int votingId;

    @Column(name = "class_id")
    private int classId;

    @Column(name = "voting_name")
    private String votingName;

    @Column(name = "voting_detail")
    private String votingDetail;

    @Column(name = "voting_end")
    private LocalDateTime votingEnd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_vote")
    private boolean isVote;

    @Column(name = "anonymous_vote")
    private boolean anonymousVote;

    @Column(name = "double_voting")
    private boolean doubleVote;

    public Voting() {
    }

    public Voting(int votingId, int classId, String votingName, String votingDetail, LocalDateTime votingEnd, LocalDateTime createdAt, boolean isVote, boolean anonymousVote, boolean doubleVote) {
        this.votingId = votingId;
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
        return votingId;
    }

    public void setId(int id) {
        this.votingId = id;
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

    public boolean isVote() {
        return isVote;
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
                "votingId=" + votingId +
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
