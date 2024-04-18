package org.example.bookstoreappllicatiodemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer Details")
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String pincode;
    private String locality;
    private String address;
    private String cityTown;
    private String landmark;


}
