package com.dingdong.lastdance_s.dto;



public class AlertDTO {

    private String alertCategory;
    private int noticeId;
    private int classId;

    public AlertDTO() {
    }


    public AlertDTO(String alertCategory, int noticeId, int classId) {
        this.alertCategory = alertCategory;
        this.noticeId = noticeId;
        this.classId = classId;
    }

    public String getAlertCategory() {
        return alertCategory;
    }

    public void setAlertCategory(String alertCategory) {
        this.alertCategory = alertCategory;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "AlertDTO{" +
                "alertCategory='" + alertCategory + '\'' +
                ", noticeId=" + noticeId +
                ", classId=" + classId +
                '}';
    }
}
