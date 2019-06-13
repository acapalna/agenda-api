package org.fasttrackit.agendaapi.repository;

import org.fasttrackit.agendaapi.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByEventIdContaining(Long partialEventId, Pageable pageable);
    Page<Event> findByEventTypeContaining(String partialEventType, Pageable pageable);
    Page<Event> findByEventTitleContaining(String partialEventTitle, Pageable pageable);
    Page<Event> findByEventDate(Date eventDate, Pageable pageable);
    Page<Event> findByEventStartTime(String eventStartTime, Pageable pageable);
    Page<Event> findByEventDuration(Double eventDuration, Pageable pageable);
    Page<Event> findByEventDescriptionContaining(String partialEventDescription, Pageable pageable);

}
