package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.model.Calender;
import com.dingdong.lastdance_s.repository.CalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalenderService {

    @Autowired
    CalenderRepository calenderRepository;

    public Calender CalenderList(int calenderId) {

        return calenderRepository.findById(calenderId).orElse(null);
    }

    public List<Calender> GetCalendar() {
        return calenderRepository.findAll();
    }

    public List<Calender> CalendarUpdate(List<Calender> calendars) {

        calenderRepository.deleteAll();

        for (Calender calendar : calendars) {
            System.out.println("Updating calendar: " + calendar.getCalendarId());

            calenderRepository.save(calendar);
        }

        return calendars;

    }
}
