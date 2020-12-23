package com.enoca.api.controller;

import com.enoca.api.model.elasticsearch.CustomerDocument;
import com.enoca.api.model.mysql.Customer;
import com.enoca.api.model.mysql.CustomerOrder;
import com.enoca.api.service.CustomerService;
import com.enoca.api.service.ESCustomerService;
import com.enoca.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchRestController {

    @Autowired
    private ESCustomerService esCustomerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void loadDataFromMysql(){

    }


    //-------------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDocument>> requestMethodName() {

        Iterator<CustomerDocument> iterator = esCustomerService.findAll().iterator();
        List<CustomerDocument> customerDocuments = new ArrayList<>();
        while (iterator.hasNext()) {
            customerDocuments.add(iterator.next());

        }
        if (customerDocuments == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(customerDocuments);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerDocument> findById(@PathVariable Long id) {

        CustomerDocument customerDocument = esCustomerService.findById(id);
        if (customerDocument == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(customerDocument);
        }

    }

    //-------------------------------------------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDocument>> findByQuery(@RequestParam("age") int age, @RequestParam("name") String name) {
        System.out.println("Age : " + age + " Name : " + name);

        List<CustomerDocument> customerDocuments = esCustomerService.findByAgeGreaterThanAndNameNotIn(age, name);
        if (customerDocuments == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(customerDocuments);
        }
    }


}
