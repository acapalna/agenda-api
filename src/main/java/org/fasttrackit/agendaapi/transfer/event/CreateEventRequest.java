package org.fasttrackit.agendaapi.transfer.event;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateEventRequest {

    @NotNull
    private String eventType;
    @NotNull
    private String eventTitle;
    private Date eventDate;
    private String eventStartTime;
    private String eventDuration;
    private String eventDescription;
    private String eventMembers;

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

    public String getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(String eventDuration) {
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

    @Override
    public String toString() {
        return "CreateEventRequest{" +
                "eventType='" + eventType + '\'' +
                ", eventTitle='" + eventTitle + '\'' +
                ", eventDate=" + eventDate +
                ", eventStartTime='" + eventStartTime + '\'' +
                ", eventDuration='" + eventDuration + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventMembers='" + eventMembers + '\'' +
                '}';
    }
}
