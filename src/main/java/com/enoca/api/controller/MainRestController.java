package com.enoca.api.controller;

import com.enoca.api.daoRepository.CustomerESRepository;
import com.enoca.api.model.elasticsearch.CustomerDocument;
import com.enoca.api.model.mysql.Customer;
import com.enoca.api.model.mysql.CustomerOrder;
import com.enoca.api.service.CustomerService;
import com.enoca.api.service.ESCustomerService;
import com.enoca.api.service.OrderService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/restapi")
public class MainRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ESCustomerService esCustomerService;

    @Autowired
    private CustomerESRepository customerESRepository;


    @PostConstruct
    public void init() {

        customerESRepository.deleteAll();

        Customer ali = customerService.saveCustomer(new Customer(20, "ALi"));
        Customer mehmet = customerService.saveCustomer(new Customer(35, "Mehmet"));
        Customer ayse = customerService.saveCustomer(new Customer(42, "Ayse"));
        Customer nazlı = customerService.saveCustomer(new Customer(20, "Nazlı"));

        System.out.println(ali);
        System.out.println(mehmet);
        System.out.println(ayse);
        System.out.println(nazlı);

        CustomerOrder books = new CustomerOrder(100, "BOOKS");
        CustomerOrder pencils = new CustomerOrder(100, "PENCILS");
        CustomerOrder cars = new CustomerOrder(100, "CARS");
        CustomerOrder toys = new CustomerOrder(100, "TOYS");
        CustomerOrder foods = new CustomerOrder(100, "FOODS");
        CustomerOrder fruits = new CustomerOrder(100, "FRUITS");
        CustomerOrder cats = new CustomerOrder(100, "CATS");


        books.setCustomer(ali);
        pencils.setCustomer(ali);
        cars.setCustomer(mehmet);
        toys.setCustomer(mehmet);
        foods.setCustomer(ayse);
        fruits.setCustomer(nazlı);
        cats.setCustomer(nazlı);

        orderService.saveOrder(books);
        orderService.saveOrder(pencils);
        orderService.saveOrder(cars);
        orderService.saveOrder(toys);
        orderService.saveOrder(cats);
        orderService.saveOrder(foods);
        orderService.saveOrder(fruits);

        System.out.println(customerService.findById(ali.getId()));
        System.out.println(customerService.findById(nazlı.getId()));
        System.out.println(customerService.findById(mehmet.getId()));
        System.out.println(customerService.findById(ayse.getId()));

        esCustomerService.createCustomerDocument(customerService.findById(ali.getId()));
        esCustomerService.createCustomerDocument(customerService.findById(mehmet.getId()));
        esCustomerService.createCustomerDocument(customerService.findById(nazlı.getId()));
        esCustomerService.createCustomerDocument(customerService.findById(ayse.getId()));

    }


    //-------------------------------------Add new cutomer-----------------------------------------------------------------------

    @RequestMapping(value = "/newCustomer", method = RequestMethod.POST)
    public ResponseEntity<URI> createCustomer(@RequestBody Customer customer) {
        try {
            Customer customer1 = customerService.saveCustomer(customer);
            esCustomerService.createCustomerDocument(customer1);
            Long id = customer1.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //--------------------------------------Add new Order----------------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, value = "/newOrder/{id}")
    public ResponseEntity<URI> createCustomOrder(@RequestBody CustomerOrder customerOrder, @PathVariable("id") Long custpmerId) {

        Customer customer = customerService.findById(custpmerId);
        customerOrder.setCustomer(customer);

        orderService.saveOrder(customerOrder);

        List<CustomerOrder> orders = orderService.findAll(custpmerId);

        esCustomerService.updateCustomerDocument(customerService.findById(custpmerId), orders);

        try {
            return ResponseEntity.ok().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //---------------------------------------------Get list customer orders----------------------------------------------------------------------------

    @RequestMapping(value = "/listCustomerOrders", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerOrder>> listCustomerOrders() {

        List<CustomerOrder> customerOrders = orderService.findAll();
        if (customerOrders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(customerOrders);
        }
    }


    //--------------------------------------------Get list customers-----------------------------------------------------------

    @RequestMapping(value = "/listCustomers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listCustomers() {

        List<Customer> customers = customerService.findAll();
        for (Customer c : customers) {
            System.err.println("-----------------------" + c);
        }

        if (customers == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(customers);
        }
    }

    //------------------------------------------get customer By Id------------------------------------------------------------

    @RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerOrder> findById(@PathVariable("id") Long id) {

        CustomerOrder customerOrder = orderService.findById(id);
        if (customerOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(customerOrder);
        }
    }


}
