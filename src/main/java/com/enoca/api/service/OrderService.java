package com.enoca.api.service;


import com.enoca.api.model.mysql.CustomerOrder;

import java.util.List;

public interface OrderService {

    CustomerOrder saveOrder(CustomerOrder customerOrder);

    CustomerOrder findById(Long id);

    List<CustomerOrder> findAll();

    List<CustomerOrder> findAll(Long id);
}
