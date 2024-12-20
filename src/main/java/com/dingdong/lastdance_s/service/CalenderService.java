package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.model.Calender;
import com.dingdong.lastdance_s.repository.CalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalenderService {

    @Autowired
    CalenderRepository calenderRepository;

    public Calender CalenderList(int calenderId) {

        return calenderRepository.findById(calenderId).orElse(null);
    }
}
