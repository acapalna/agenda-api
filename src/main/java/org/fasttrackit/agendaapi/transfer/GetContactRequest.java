package org.fasttrackit.agendaapi.transfer;


public class GetContactRequest {

    private String partialFirstName;
    private Long phoneNumber;

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        this.partialFirstName = partialFirstName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "GetContactRequest{" +
                "partialFirstName='" + partialFirstName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
