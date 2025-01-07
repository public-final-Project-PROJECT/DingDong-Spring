package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "calendar")
public class Calender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calendarId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_datetime", nullable = false, length = 50)
    private String start;

    @Column(name = "end_datetime", length = 50)
    private String end;

    @Column(name = "all_day", nullable = false)
    private Boolean allDay = false;

    @Override
    public String toString() {
        return "Calender{" +
                "calendarId=" + calendarId +
                ", title='" + title + '\'' +
                ", isAdmin=" + isAdmin +
                ", description='" + description + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", allDay=" + allDay +
                ", color='" + color + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    /* @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "class_id", referencedColumnName = "class_id")
        private Class classId;  // `Class` 엔티티와 매핑
    */
    @Column(name = "color", length = 32)
    private String color;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());




    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        calendarId = calendarId;
    }
}
