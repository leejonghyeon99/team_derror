package com.example.demo.service.event;

import com.example.demo.domain.event.EventData;
import com.example.demo.domain.event.Events;

import java.util.List;

/**
 * @author 종선
 */


public interface EventService {

    // 이벤트 목록 가져오기
    List<Events> getAllEvents(String id);

    // 이벤트 id값
    Events getEventById(Events events);
    List<EventData> getEventsByMemberId (Long id);

    // 이벤트 저장
    void save(EventData eventData);

}
