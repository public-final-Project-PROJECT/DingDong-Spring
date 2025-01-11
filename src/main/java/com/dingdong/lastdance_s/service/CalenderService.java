package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.model.Calender;
import com.dingdong.lastdance_s.repository.CalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public int CalendarInsert(Calender requestBody) {

        return calenderRepository.save(requestBody).getCalendarId();
    }

    public int CalendarDelete(Integer id) {
        if(calenderRepository.findById(id).isPresent()) {
            calenderRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    public Calender CalendarUpdateSelect( Map<String,Object> requestBody) {


        // Step 1: Extract the calendarId from the request body
        int calendarId = (int) requestBody.get("calendarId");

        // Step 2: Retrieve the existing calendar entity by its ID
        Calender existingCalendar = calenderRepository.findById(calendarId)
                .orElseThrow(() -> new RuntimeException("Calendar not found with ID: " + calendarId));

        // Step 3: Update fields from the request body
        if (requestBody.containsKey("title")) {
            existingCalendar.setTitle((String) requestBody.get("title"));
        }
        if (requestBody.containsKey("description")) {
            existingCalendar.setDescription((String) requestBody.get("description"));
        }
        if (requestBody.containsKey("start")) {
            existingCalendar.setStart((String) requestBody.get("start"));
        }
        if (requestBody.containsKey("end")) {
            existingCalendar.setEnd((String) requestBody.get("end"));
        }

        // Step 4: Save the updated entity
        Calender updatedCalendar = calenderRepository.save(existingCalendar);

        // Step 5: Return the updated entity
        return updatedCalendar;

    }
}
