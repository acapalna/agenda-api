package org.fasttrackit.agendaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.agendaapi.domain.Event;
import org.fasttrackit.agendaapi.exception.ResourceNotFoundException;
import org.fasttrackit.agendaapi.repository.EventRepository;
import org.fasttrackit.agendaapi.transfer.event.CreateEventRequest;
import org.fasttrackit.agendaapi.transfer.event.GetEventRequest;
import org.fasttrackit.agendaapi.transfer.event.UpdateEventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EventService(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    public Event createEvent(CreateEventRequest request){
        LOGGER.info("Creating event {}", request);

        Event event = objectMapper.convertValue(request, Event.class);

        LOGGER.info("Event created {}", request);
        return eventRepository.save(event);
    }

    public Event getEvent(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving event {}", id);
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event " + id + " does not exist"));
    }

    public Event updateEvent(long id, UpdateEventRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating event {} with {}", id, request);

        Event event = getEvent(id);
        BeanUtils.copyProperties(request, event);

        LOGGER.info("Event {} was updated with {}", id, request);
        return eventRepository.save(event);
    }

    public void deleteEvent(long id){
        LOGGER.info("Deleting event {}", id);
        eventRepository.deleteById(id);
        LOGGER.info("Deleted event {}", id);
    }

    public Page<Event> getEvent(GetEventRequest request, Pageable pageable) {
        LOGGER.info("Retrieving product {}", request);

        if (request.getEventId() != null) {
            return eventRepository.findByEventIdContaining(request.getEventId(), pageable);
        }
        else if (request.getPartialEventType() != null) {
            return eventRepository.findByEventTypeContaining(request.getPartialEventType(), pageable);
        }
        else if (request.getPartialEventTitle() != null) {
            return eventRepository.findByEventTitleContaining(request.getPartialEventTitle(), pageable);
        }
        else if (request.getPartialEventDescription() != null) {
            return eventRepository.findByEventDescriptionContaining(request.getPartialEventDescription(), pageable);
        }
        else if (request.getEventStartTime() != null) {
            return eventRepository.findByEventStartTime(request.getEventStartTime(), pageable);
        }
        else if (request.getEventDuration() != null) {
            return eventRepository.findByEventDuration(request.getEventDuration(), pageable);
        }
        else if (request.getEventDate() != null) {
            return eventRepository.findByEventDate(request.getEventDate(), pageable);
        }

        return eventRepository.findAll(pageable);
    }

}
