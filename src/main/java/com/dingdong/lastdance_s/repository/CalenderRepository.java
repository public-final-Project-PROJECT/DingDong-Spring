package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.model.Calender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Integer> {


}
