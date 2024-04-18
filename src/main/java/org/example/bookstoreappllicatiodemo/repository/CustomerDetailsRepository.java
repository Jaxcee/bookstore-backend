package org.example.bookstoreappllicatiodemo.repository;

import org.example.bookstoreappllicatiodemo.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails , Long > {

}
