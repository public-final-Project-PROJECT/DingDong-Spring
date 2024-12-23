package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;


    public List<Notice> getNoticesByClassId(int id) {
        return noticeRepository.findAllById(Collections.singleton(id));
    }
}
