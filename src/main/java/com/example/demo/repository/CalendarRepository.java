package com.example.demo.repository;

import com.example.demo.domain.Calendar;

import java.util.List;

public interface CalendarRepository {

    // 삽입
    void insert(Calendar calendar);

    void update(Calendar calendar);

    void deleteById(Long id);

    Calendar findById(Long id);

    List<Calendar> findAll(Long memberId);

}
