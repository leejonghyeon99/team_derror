package com.example.demo.repository.event;


import com.example.demo.domain.event.Events;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {
    List<Events> getAllEvents();
    Events getEventById(String id);
    Events insertEvent(Events events);

}