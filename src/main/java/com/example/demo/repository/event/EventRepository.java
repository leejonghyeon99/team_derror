package com.example.demo.repository.event;


import com.example.demo.domain.event.EventData;
import com.example.demo.domain.event.Events;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {
    List<Events> getAllEvents(String id);
    Events getEventById(String id);
    List<EventData> getEventsByMemberId(Long id);

    void save(EventData eventData);

}