package com.enoca.api.service.Impl;

import com.enoca.api.daoRepository.OrderMysqlRepository;
import com.enoca.api.model.mysql.CustomerOrder;
import com.enoca.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMysqlRepository orderMysqlRepository;

    @Override
    public CustomerOrder saveOrder(CustomerOrder customerOrder) {
        return orderMysqlRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder findById(Long id) {
        return orderMysqlRepository.findById(id).get();
    }

    @Override
    public List<CustomerOrder> findAll() {
        return orderMysqlRepository.findAll();
    }

    @Override
    public List<CustomerOrder> findAll(Long id) {
        return orderMysqlRepository.findAll(id);
    }
}
