package org.example.bookstoreappllicatiodemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "userdetails")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private  Long id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private String password;
    private Integer age;
    private String gender;
    private String otp;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CartEntity> cart;

    public UserEntity(Long id, String firstName, String lastName, String email, String password, Integer age, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}