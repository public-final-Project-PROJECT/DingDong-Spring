package com.dingdong.lastdance_s.repository;


import com.dingdong.lastdance_s.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository  extends JpaRepository<Students, Integer> {

    List<Students> findAllByClassId(int classId);

    @Query("SELECT s FROM Students s WHERE s.studentId = :studentId")
    List<Students> findByStudentId(@Param("studentId") int studentId);

    List<Students> findByClassId(int classId);
}
