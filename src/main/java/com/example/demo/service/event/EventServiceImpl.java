package com.example.demo.service.event;

import com.example.demo.domain.event.EventData;
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
    public List<Events> getAllEvents(String id) {
        return eventRepository.getAllEvents(id);
    }

    // 이벤트 아이디
    @Override
    public Events getEventById(Events events) {
        return eventRepository.getEventById(events.getId());
    }

    // 이벤트 저장
    @Override
    public void save(EventData eventData) {
        eventRepository.save(eventData);
    }


}
