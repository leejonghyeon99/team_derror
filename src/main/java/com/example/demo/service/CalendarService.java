package com.example.demo.service;

import com.example.demo.domain.Calendar;

import java.util.List;

public interface CalendarService {

    void addEvent(Calendar calendar);

    void updateEvent(Calendar calendar);

    void deleteEvent(Long id);

    Calendar findEvent(Long id);

    List<Calendar> getAllEvents(Long memberId);


}
