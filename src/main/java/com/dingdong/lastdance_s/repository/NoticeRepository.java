package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    @Query("SELECT n FROM Notice n WHERE n.classId = :classId")
    List<Notice> findAllByClassId(@Param("classId") Integer classId);

    @Query("SELECT n FROM  Notice n WHERE n.noticeId = :noticeId")
    List<Notice> findAllByNoticeId(@Param("noticeId") Integer noticeId);
}
