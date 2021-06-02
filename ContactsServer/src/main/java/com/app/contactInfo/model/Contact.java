package com.app.contactInfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
/*@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter*/
public class Contact {

    private String id;
    @NotEmpty(message = "FirstName can not be empty")

    private String firstName;
    @NotEmpty(message = "LastName can not be empty")
    private String lastName;
    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Phone Number can not be empty")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="Phone Number is invalid")
    private String phoneNumber;
    @NotEmpty(message = "Status can not be empty")
    private String status;

    public Contact(){}

    public Contact(String id, String firstName, String lastName, String email, String phoneNumber, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
