package com.enoca.api.daoRepository;

import com.enoca.api.model.mysql.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMysqlRepository extends JpaRepository<Customer, Long> {

    @Override
    List<Customer> findAll();



}
