package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.dto.AlertDTO;
import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}