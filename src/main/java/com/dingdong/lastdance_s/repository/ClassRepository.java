package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    List<Class> findBySchoolName(String schoolName);

    List<Class> findByGradeAndClassNo(int grade, int classNo);

    @Query("SELECT c FROM Class c WHERE c.id.id = :teacherId")
    List<Class> findByUserId(@Param("teacherId") int teacherId);

    @Query("SELECT count(*) FROM Class c WHERE c.id.id = :teacherId")
    int countByUserId(@Param("teacherId") int teacherId);

    @Modifying
    @Query("DELETE FROM Class c WHERE c.id.id = :teacherId")
    void deleteByUserId(@Param("teacherId") int teacherId);

    @Modifying
    @Query("DELETE FROM Class c WHERE c.id.id = :teacherId AND c.classId = :classId")
    void deleteClassByUserIdAndId(@Param("teacherId") int teacherId, @Param("classId") int classId);

    @Query("SELECT c FROM Class c WHERE c.classId = :classId AND c.id.id = :teacherId")
    Class findByIdAndUserId(int classId, int teacherId);
}
