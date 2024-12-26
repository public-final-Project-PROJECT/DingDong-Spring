package com.dingdong.lastdance_s.entity.voting;


import jakarta.persistence.*;

@Entity
@Table(name = "voting_contents")
public class VotingContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int votingContentsId;  // 투표 항목 고유 id

    @Column(name = "voting_id")
    private int votingId;  // 투표 고유 id

    @Column(name = "voting_contents")
    private String votingContents;  // 투표 항목 내용

    public VotingContents() {
    }

    public VotingContents(int votingContentsId, int votingId, String votingContents) {
        this.votingContentsId = votingContentsId;
        this.votingId = votingId;
        this.votingContents = votingContents;
    }

    public int getContentsId() {
        return votingContentsId;
    }

    public void setContentsId(int contentsId) {
        this.votingContentsId = contentsId;
    }

    public int getVotingId() {
        return votingId;
    }

    public void setVotingId(int votingId) {
        this.votingId = votingId;
    }

    public String getVotingContents() {
        return votingContents;
    }

    public void setVotingContents(String votingContents) {
        this.votingContents = votingContents;
    }

    @Override
    public String toString() {
        return "VotingContents{" +
                "votingContentsId=" + votingContentsId +
                ", votingId=" + votingId +
                ", votingContents='" + votingContents + '\'' +
                '}';
    }
}