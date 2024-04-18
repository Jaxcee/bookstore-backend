package org.example.bookstoreappllicatiodemo.service.customerdetaiIservice;

import org.example.bookstoreappllicatiodemo.entity.CustomerDetails;
import org.example.bookstoreappllicatiodemo.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements ICustomerDetailService{

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;


    @Override
    public CustomerDetails saveCustomerDetails(CustomerDetails details) {
        return customerDetailsRepository.save(details);
    }
}
