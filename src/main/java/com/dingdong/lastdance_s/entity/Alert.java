package com.dingdong.lastdance_s.entity;

import com.dingdong.lastdance_s.model.Notice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private int alertId;

    @Column(name = "alert_category")
    @Enumerated(EnumType.STRING)
    private Notice.NoticeCategory noticeCategory;


    @Column(name = "voting_id")
    private int votingId;

    @Column(name = "notice_id")
    private int noticeId;

    public Alert() {
    }

    public Alert(int alertId, Notice.NoticeCategory noticeCategory, int votingId, int noticeId) {
        this.alertId = alertId;
        this.noticeCategory = noticeCategory;
        this.votingId = votingId;
        this.noticeId = noticeId;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId=" + alertId +
                ", noticeCategory=" + noticeCategory +
                ", votingId=" + votingId +
                ", noticeId=" + noticeId +
                '}';
    }
}
