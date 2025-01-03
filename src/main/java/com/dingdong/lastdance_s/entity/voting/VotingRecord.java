package com.dingdong.lastdance_s.entity.voting;


import jakarta.persistence.*;

@Entity
@Table(name = "voting_record")
public class VotingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int VotingRecordId;  // 학생 투표 정보 고유 id

    @Column(name = "voting_id")
    private int votingId;  // 투표 고유 id

    @Column(name = "contents_id")
    private int contentsId;  // 투표 항목 고유 id

    @Column(name = "student_id")
    private int studentId;  // 학생 고유 id

    public VotingRecord() {
    }

    public VotingRecord(int VotingRecordId, int votingId, int contentsId, int studentId) {
        VotingRecordId = VotingRecordId;
        this.votingId = votingId;
        this.contentsId = contentsId;
        this.studentId = studentId;
    }

    public int getRecordId() {
        return VotingRecordId;
    }

    public void setRecordId(int recordId) {
        VotingRecordId = recordId;
    }

    public int getVotingId() {
        return votingId;
    }

    public void setVotingId(int votingId) {
        this.votingId = votingId;
    }

    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "votingRecord{" +
                "VotingRecordId=" + VotingRecordId +
                ", votingId=" + votingId +
                ", contentsId=" + contentsId +
                ", studentId=" + studentId +
                '}';
    }
}
