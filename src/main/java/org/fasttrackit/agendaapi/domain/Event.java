package org.fasttrackit.agendaapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long eventId;
    @NotNull
    private String eventType;
    @NotNull
    private String eventTitle;
    private Date eventDate;
    private String eventStartTime;
    private Double eventDuration;
    private String eventDescription;
    private String eventMembers;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Double getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Double eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventMembers() {
        return eventMembers;
    }

    public void setEventMembers(String eventMembers) {
        this.eventMembers = eventMembers;
    }
}
