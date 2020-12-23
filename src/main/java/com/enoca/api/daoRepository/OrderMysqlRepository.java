package com.enoca.api.daoRepository;

import com.enoca.api.model.mysql.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMysqlRepository extends JpaRepository<CustomerOrder,Long> {

    @Query("SELECT o from CustomerOrder o where o.customer.id=:id")
    List<CustomerOrder> findAll(Long id);
}
