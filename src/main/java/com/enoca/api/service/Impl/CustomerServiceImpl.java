package com.enoca.api.service.Impl;

import com.enoca.api.daoRepository.CustomerMysqlRepository;
import com.enoca.api.model.mysql.Customer;
import com.enoca.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMysqlRepository customerMysqlRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerMysqlRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerMysqlRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerMysqlRepository.findById(id).get();
    }
}
