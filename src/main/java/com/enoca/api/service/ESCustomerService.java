package com.enoca.api.service;

import com.enoca.api.model.elasticsearch.CustomerDocument;
import com.enoca.api.model.mysql.Customer;
import com.enoca.api.model.mysql.CustomerOrder;

import java.util.List;

public interface ESCustomerService {


    List<CustomerDocument> findAll();

    CustomerDocument findById(Long id);

    CustomerDocument createCustomerDocument(Customer customer);

    List<CustomerDocument> findByAgeGreaterThanAndNameNotIn(int age , String query);

    void updateCustomerDocument(Customer customer,List<CustomerOrder> orders);
}
