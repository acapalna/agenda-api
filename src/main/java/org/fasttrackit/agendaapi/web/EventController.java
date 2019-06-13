package org.fasttrackit.agendaapi.web;

import org.fasttrackit.agendaapi.domain.Event;
import org.fasttrackit.agendaapi.exception.ResourceNotFoundException;
import org.fasttrackit.agendaapi.service.EventService;
import org.fasttrackit.agendaapi.transfer.event.CreateEventRequest;
import org.fasttrackit.agendaapi.transfer.event.GetEventRequest;
import org.fasttrackit.agendaapi.transfer.event.UpdateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<Page<Event>> getEvents(GetEventRequest request, Pageable pageable){
        Page<Event> response = eventService.getEvent(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid CreateEventRequest request){
        Event event = eventService.createEvent(request);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent(@PathVariable Long id) throws ResourceNotFoundException {
        Event event = eventService.getEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateEventRequest request) throws ResourceNotFoundException {
        eventService.updateEvent(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
