package com.example.demo.service.event;

import com.example.demo.domain.event.Events;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 종선
 */

@Service
public interface EventService {

    // 이벤트 목록 가져오기
    List<Events> getAllEvents();

    // 이벤트 id값
    Events getEventById(String eventId);

    // 이벤트 등록
    Events save(Events event);
}
