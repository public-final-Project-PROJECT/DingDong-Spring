package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);

    @Query("SELECT u.schoolName FROM User u WHERE u.email = :email")
    Optional<String> findSchoolNameByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Optional<Integer> findUserIdByEmail(@Param("email") String email);

    @Query("SELECT u.latestClassId FROM User u WHERE u.email = :email")
    Optional<Integer> findLatestClassIdByEmail(String email);
}