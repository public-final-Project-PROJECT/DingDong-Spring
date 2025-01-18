package com.dingdong.lastdance_s.entity;

import com.dingdong.lastdance_s.entity.voting.Voting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private int alertId;

    @Column(name = "alert_category")
    @Enumerated(EnumType.STRING)
    private AlertCategory alertCategory;


    @Column(name = "voting_id")
    private int votingId;

    @Column(name = "notice_id")
    private Integer noticeId;


    @Column(name = "class_id")
    private int classId;


    @Column(name ="student_id")
    private Integer studentId;

    @Column(name = "is_read")
    private Boolean isRead = Boolean.FALSE;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "alert_at")
    private Timestamp alertAt;

    public Alert() {
    }

    public Alert(int alertId, AlertCategory alertCategory, int votingId, int noticeId, int classId, int studentId, boolean isRead, Timestamp alertAt) {
        this.alertId = alertId;
        this.alertCategory = alertCategory;
        this.votingId = votingId;
        this.noticeId = noticeId;
        this.classId = classId;
        this.studentId = studentId;
        this.isRead = isRead;
        this.alertAt = alertAt;
    }

    public enum AlertCategory {
        공지사항("공지사항"),
        투표("투표"),
        타이머("타이머");

        private String value;

        AlertCategory(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        // 문자열을 AlertCategory로 변환하는 메서드
        public static AlertCategory fromString(String value) {
            for (AlertCategory category : AlertCategory.values()) {
                if (category.getValue().equals(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown category: " + value);
        }
    }
    }
