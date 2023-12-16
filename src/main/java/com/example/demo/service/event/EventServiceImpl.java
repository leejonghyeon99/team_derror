package com.example.demo.service.event;

import com.example.demo.domain.event.Events;
import com.example.demo.repository.event.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // 이벤트 목록
    @Override
    public List<Events> getAllEvents() {
        return eventRepository.getAllEvents();
    }


    @Override
    public Events getEventById(String eventId) {
        return eventRepository.getEventById(eventId);
    }

    // 이벤트 저장
    @Override
    public Events save(Events event) {
        return eventRepository.insertEvent(event);
    }

//    @Override
//    public Events registerEvent(Events event) {
//        return eventRepository.insertEvent(event);
//    }
}
