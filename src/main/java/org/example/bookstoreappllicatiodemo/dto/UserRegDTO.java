package org.example.bookstoreappllicatiodemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegDTO {
    private  String firstName;
    private  String lastName;
    private  String email;
    private String password;

    public UserRegDTO() {
    }

    public UserRegDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


