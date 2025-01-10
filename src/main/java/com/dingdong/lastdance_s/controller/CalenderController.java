package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.model.Calender;
import com.dingdong.lastdance_s.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/calendar")
public class CalenderController {

    @Autowired
    CalenderService calenderService;

    @GetMapping("/list")
    public ResponseEntity<Object> CalenderList() {



        // 서비스에서 해당 id로 데이터를 가져옴
        List<Calender> response = calenderService.GetCalendar();


        return ResponseEntity.ok(response);
    }

    @PostMapping("/listselect")
    public ResponseEntity<Object> CalenderList(@RequestBody Map<String, Integer> requestBody) {
        // 요청 본문에서 input 값 가져오기
        int calenderId = requestBody.get("input");

        // 서비스에서 해당 id로 데이터를 가져옴
        Calender response = calenderService.CalenderList(calenderId);


        return ResponseEntity.ok(response);
    }


    @PostMapping("/update")
    public ResponseEntity<Object> CalendarUpdate(@RequestBody List<Calender> requestBody) {

        List<Calender> response = calenderService.CalendarUpdate(requestBody);


        return ResponseEntity.ok(response);
    }
    @PostMapping("/selectupdate")
    public ResponseEntity<Object> CalendarUpdateSelect(@RequestBody Map<String,Object> requestBody) {

        System.out.println(requestBody);
        Calender response = calenderService.CalendarUpdateSelect(requestBody);


        return ResponseEntity.ok(response);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> CalendarInsert(@RequestBody Calender requestBody) {


        int response = calenderService.CalendarInsert(requestBody);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/delete")
    public ResponseEntity<Object> CalendarDelete(@RequestBody Map<String, Integer> requestBody) {
        // Extract the ID from the request body
        Integer id = requestBody.get("id");

        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        System.out.println("Deleting calendar event with ID: " + id);

        int response = calenderService.CalendarDelete(id);

        return ResponseEntity.ok("Event with ID " + id + " deleted successfully");
    }
}


