package org.fasttrackit.agendaapi.transfer.event;


import java.util.Date;

public class GetEventRequest {

    private Long eventId;
    private String partialEventType;
    private String partialEventTitle;
    private String partialEventDescription;
    private String eventStartTime;
    private Double eventDuration;
    private Date eventDate;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getPartialEventType() {
        return partialEventType;
    }

    public void setPartialEventType(String partialEventType) {
        this.partialEventType = partialEventType;
    }

    public String getPartialEventTitle() {
        return partialEventTitle;
    }

    public void setPartialEventTitle(String partialEventTitle) {
        this.partialEventTitle = partialEventTitle;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getPartialEventDescription() {
        return partialEventDescription;
    }

    public void setPartialEventDescription(String partialEventDescription) {
        this.partialEventDescription = partialEventDescription;
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

}
