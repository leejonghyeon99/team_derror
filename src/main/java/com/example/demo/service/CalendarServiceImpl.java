package com.example.demo.service;

import com.example.demo.domain.Calendar;
import com.example.demo.repository.CalendarRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CalendarServiceImpl implements CalendarService {

    private CalendarRepository calendarRepository;

    @Autowired
    public CalendarServiceImpl(SqlSession sqlSession){
        calendarRepository = sqlSession.getMapper(CalendarRepository.class);
    }

    @Override
    public void addEvent(Calendar calendar) {
        calendarRepository.insert(calendar);
    }

    @Override
    public void updateEvent(Calendar calendar) {
        calendarRepository.update(calendar);
    }

    @Override
    public void deleteEvent(Long id) {
        calendarRepository.deleteById(id);
    }

    @Override
    public Calendar findEvent(Long id) {
        return calendarRepository.findById(id);
    }

    @Override
    public List<Calendar> getAllEvents(Long memberId) {
        return calendarRepository.findAll(memberId);
    }

}
