package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {

    @Query("SELECT s FROM Alert s WHERE s.classId = :classId AND s.isRead = false")
    List<Alert> findByClassId(int classId);

    @Query("SELECT s FROM Alert s WHERE (s.classId = :classId OR s.studentId = :studentId) AND s.isRead = false")
    List<Alert> findByClassIdOrStudentId(int classId, Integer studentId);
}