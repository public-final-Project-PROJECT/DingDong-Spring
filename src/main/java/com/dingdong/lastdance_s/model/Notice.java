package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private int noticeId;

    @Column(name = "notice_category")
    @Enumerated(EnumType.STRING)
    private NoticeCategory noticeCategory;

    @Column(name = "notice_title ")
    private String noticeTitle;

    @Column(name = "notice_content ")
    private String noticeContent;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at ")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at ")
    private Timestamp updatedAt;

    @Column(name = "notice_img ")
    private String noticeImg;

    @Column(name = "notice_file ")
    private String noticeFile;

    public Notice() {
    }

    public Notice(int noticeId, NoticeCategory noticeCategory, String noticeTitle, String noticeContent, Timestamp createdAt, Timestamp updatedAt, String noticeImg, String noticeFile) {
        this.noticeId = noticeId;
        this.noticeCategory = noticeCategory;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.noticeImg = noticeImg;
        this.noticeFile = noticeFile;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public NoticeCategory getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(NoticeCategory noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNoticeImg() {
        return noticeImg;
    }

    public void setNoticeImg(String noticeImg) {
        this.noticeImg = noticeImg;
    }

    public String getNoticeFile() {
        return noticeFile;
    }

    public void setNoticeFile(String noticeFile) {
        this.noticeFile = noticeFile;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeCategory=" + noticeCategory +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", noticeImg='" + noticeImg + '\'' +
                ", noticeFile='" + noticeFile + '\'' +
                '}';
    }

    public enum NoticeCategory {
        가정통신문,
        알림장,
        학교생활
    }
}


