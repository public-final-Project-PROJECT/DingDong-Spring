package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.dto.AlertDTO;
import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AlertService  {


    @Autowired
    private AlertRepository alertRepository;



    public void alertSave(AlertDTO alertDTO) {
        Alert alert = new Alert();
        Alert.AlertCategory alertCategory = Alert.AlertCategory.fromString(alertDTO.getAlertCategory().toString());
        alert.setAlertCategory(alertCategory);
        alert.setNoticeId(alertDTO.getNoticeId());
        alert.setClassId(alertDTO.getClassId());
        alertRepository.save(alert);

    }

    public List<Alert> findByClassId(int classId) {
        List<Alert> AlertList = alertRepository.findByClassId(classId);

        if (AlertList != null){
            return AlertList;
        }else {
            return null;
        }
    }

    public List<Alert> findByClassIOrStudentId(int classId, Integer studentId) {
        List<Alert> AlertList = alertRepository.findByClassIdOrStudentId(classId,studentId);

        if (AlertList != null){
            return AlertList;
        }else {
            return null;
        }
    }

    public Alert nonVotingAlertSave(int classId, int studentId, int votingId) {

        Alert alert = new Alert();
        alert.setAlertCategory(Alert.AlertCategory.valueOf("투표"));
        alert.setClassId(classId);
        alert.setStudentId(studentId);
        alert.setVotingId(votingId);

        Alert AlertList = alertRepository.save(alert);
        if(AlertList == null) {
            return null;
        }
        return AlertList;
    }
}