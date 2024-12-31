package com.dingdong.lastdance_s.repository;


import com.dingdong.lastdance_s.dto.StudentsDTO;
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

    @Query("SELECT new com.dingdong.lastdance_s.dto.StudentsDTO(s.studentId, s.studentName, s.studentImg, " +
            "s.studentPhone, s.studentGender, s.studentEtc, s.studentBirth, s.studentAddress, " +
            "s.parentsName, s.parentsPhone, s.memo, c.classId, c.schoolName, c.grade, c.classNo) " +
            "FROM Students s JOIN Class c ON s.classId = c.classId " +
            "WHERE c.classId = :classId")
    List<StudentsDTO> findClassByClassId(@Param("classId") int classId);

    @Query("SELECT new com.dingdong.lastdance_s.dto.StudentsDTO(s.studentId, s.studentName, s.studentImg, " +
            "s.studentPhone, s.studentGender, s.studentEtc, s.studentBirth, s.studentAddress, " +
            "s.parentsName, s.parentsPhone, s.memo, c.classId, c.schoolName, c.grade, c.classNo) " +
            "FROM Students s JOIN Class c ON s.classId = c.classId " +
            "WHERE s.studentId = :studentId")
    StudentsDTO findStudentsAndClassByStudentId(@Param("studentId") int studentId);
}

