package org.fasttrackit.agendaapi.transfer.contact;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateContactRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String nickName;
    @Min(0)
    private Integer age;
    @NotNull
    private Long phoneNumber;
    private String description;
    private String imagePath;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "UpdateContactRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", description=" + description +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
