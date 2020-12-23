package com.enoca.api.service;

import com.enoca.api.model.mysql.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);
}
