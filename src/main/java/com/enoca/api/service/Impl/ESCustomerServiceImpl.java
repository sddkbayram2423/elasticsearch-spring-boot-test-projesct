package com.enoca.api.service.Impl;


import com.enoca.api.daoRepository.CustomerESRepository;
import com.enoca.api.model.elasticsearch.CustomerDocument;
import com.enoca.api.model.elasticsearch.OrderDocument;
import com.enoca.api.model.mysql.Customer;
import com.enoca.api.model.mysql.CustomerOrder;
import com.enoca.api.service.ESCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ESCustomerServiceImpl implements ESCustomerService {

    @Autowired
    private CustomerESRepository customerDaoEs;


    @Override
    public List<CustomerDocument> findAll() {
        return customerDaoEs.findAll();
    }

    @Override
    public CustomerDocument findById(Long id) {
        return customerDaoEs.findById(id).get();
    }

    @Override
    public CustomerDocument createCustomerDocument(Customer customer) {
        List<OrderDocument> orderList = new ArrayList();

        for (CustomerOrder order : customer.getCustomerOrders()) {
            orderList.add(new OrderDocument(order.getId(), order.getName(), order.getOrderNumber()));
        }
        CustomerDocument customerDocument = new CustomerDocument();
        customerDocument.setId(customer.getId());
        customerDocument.setAge(customer.getAge());
        customerDocument.setName(customer.getName());
        customerDocument.setOrderDocuments(orderList);
        return customerDaoEs.save(customerDocument);
    }

    @Override
    public List<CustomerDocument> findByAgeGreaterThanAndNameNotIn(int age, String query) {
        return customerDaoEs.findByAgeGreaterThanAndNameNotIn(age, query);
    }


    @Override
    public void updateCustomerDocument(Customer customer, List<CustomerOrder> orders) {


        List<OrderDocument> orderList = new ArrayList();

        for (CustomerOrder order : orders) {
            orderList.add(new OrderDocument(order.getId(), order.getName(), order.getOrderNumber()));
        }


        CustomerDocument customerDocument = new CustomerDocument();
        customerDocument.setId(customer.getId());
        customerDocument.setAge(customer.getAge());
        customerDocument.setName(customer.getName());
        customerDocument.setOrderDocuments(orderList);

        customerDaoEs.deleteById(customerDocument.getId());

        System.out.println("Customer: " + customer);
        System.out.println("orders  : " + orders);
        System.out.println("customerDocument  : " + customerDocument);

        CustomerDocument s = customerDaoEs.save(customerDocument);

        System.out.println("Kayıttan Sonra  : " + s);
    }
}
