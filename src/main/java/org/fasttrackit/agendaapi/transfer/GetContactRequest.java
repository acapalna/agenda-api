package org.fasttrackit.agendaapi.transfer;


public class GetContactRequest {

    private String partialFirstName;
    private Long partialPhoneNumber;
    private Long partialId;
    private String partialLastName;
    private String partialNickName;
    private Integer partialAge;
    private String partialDescription;

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        this.partialFirstName = partialFirstName;
    }

    public Long getPartialPhoneNumber() {
        return partialPhoneNumber;
    }

    public void setPartialPhoneNumber(Long partialPhoneNumber) {
        this.partialPhoneNumber = partialPhoneNumber;
    }

    public Long getPartialId() {
        return partialId;
    }

    public void setPartialId(Long partialId) {
        this.partialId = partialId;
    }

    public String getPartialLastName() {
        return partialLastName;
    }

    public void setPartialLastName(String partialLastName) {
        this.partialLastName = partialLastName;
    }

    public String getPartialNickName() {
        return partialNickName;
    }

    public void setPartialNickName(String partialNickName) {
        this.partialNickName = partialNickName;
    }

    public Integer getPartialAge() {
        return partialAge;
    }

    public void setPartialAge(Integer partialAge) {
        this.partialAge = partialAge;
    }

    public String getPartialDescription() {
        return partialDescription;
    }

    public void setPartialDescription(String partialDescription) {
        this.partialDescription = partialDescription;
    }

    @Override
    public String toString() {
        return "GetContactRequest{" +
                "partialFirstName='" + partialFirstName + '\'' +
                ", partialPhoneNumber=" + partialPhoneNumber +
                ", partialId=" + partialId +
                ", partialLastName='" + partialLastName + '\'' +
                ", partialNickName='" + partialNickName + '\'' +
                ", partialAge=" + partialAge +
                ", partialDescription='" + partialDescription + '\'' +
                '}';
    }
}
