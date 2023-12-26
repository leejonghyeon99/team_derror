package com.example.demo.controller;
import com.example.demo.config.PrincipalDetails;
import com.example.demo.domain.Calendar;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 사용자의 일정 목록을 가져오는 엔드포인트
    @GetMapping("/events")
    public List<Calendar> getEvents(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principalDetails.getMember().getId();
        return calendarService.getAllEvents(memberId);
    }

    // 일정 추가 엔드포인트
    @PostMapping("/events/add")
    public void addEvent(@RequestBody Calendar calendar, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long member_id = principalDetails.getMember().getId();
        calendar.setMemberId(member_id);
        calendarService.addEvent(calendar);
    }

    // 일정 수정 엔드포인트
    @PostMapping("/events/update")
    public void updateEvent(Calendar calendar, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long member_id = principalDetails.getMember().getId();
        calendar.setMemberId(member_id);
        calendarService.updateEvent(calendar);
    }

    // 일정 삭제 엔드포인트
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        calendarService.deleteEvent(id);
    }
}
