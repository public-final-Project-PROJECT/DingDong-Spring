package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.dto.AlertDTO;
import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;


    public void alertSave(AlertDTO alertDTO) {
        Alert alert = new Alert();
        Alert.AlertCategory alertCategory = Alert.AlertCategory.fromString(alertDTO.getAlertCategory());
        alert.setAlertCategory(alertCategory);
        alert.setNoticeId(alertDTO.getNoticeId());
        alert.setClassId(alertDTO.getClassId());
        alertRepository.save(alert);

    }

    public List<Alert> findByClassId(int classId) {

        return alertRepository.findByClassId(classId);
    }

    public List<Alert> findByClassIOrStudentId(int classId, Integer studentId) {

        return alertRepository.findByClassIdOrStudentId(classId, studentId);
    }

    public void alertUpdate(int alertId) {
        Alert alert = alertRepository.findById(alertId).orElseThrow(() -> new IllegalArgumentException("No alert found with ID: " + alertId));
        alert.setIsRead(true);
        alertRepository.save(alert);
    }

    public Alert nonVotingAlertSave(int classId, int studentId, int votingId) {

        Alert alert = new Alert();
        alert.setAlertCategory(Alert.AlertCategory.valueOf("투표재촉"));
        alert.setClassId(classId);
        alert.setStudentId(studentId);
        alert.setVotingId(votingId);

        return alertRepository.save(alert);
    }

    public Alert votingResultAlert(int classId, int votingId) {

        Alert alert = new Alert();
        alert.setAlertCategory(Alert.AlertCategory.valueOf("투표결과"));
        alert.setClassId(classId);
        alert.setVotingId(votingId);
        return alertRepository.save(alert);

    }
}