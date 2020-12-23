package com.enoca.api.model.elasticsearch;

import com.enoca.api.model.mysql.CustomerOrder;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;


@Document(indexName = "enoca")
public class CustomerDocument {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Object,index = true, store = true, includeInParent = true,name="order-list")
    private List<OrderDocument> orderDocuments;

    public CustomerDocument() {
    }

    public CustomerDocument(Long id, Integer age, String name, List<OrderDocument> orderDocuments) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.orderDocuments = orderDocuments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderDocument> getOrderDocuments() {
        return orderDocuments;
    }

    public void setOrderDocuments(List<OrderDocument> orderDocuments) {
        this.orderDocuments = orderDocuments;
    }

    @Override
    public String toString() {
        return "CustomerDocument{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", orderDocuments=" + orderDocuments +
                '}';
    }
}
