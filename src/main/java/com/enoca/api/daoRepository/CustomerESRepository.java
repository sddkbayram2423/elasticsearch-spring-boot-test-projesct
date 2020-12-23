package com.enoca.api.daoRepository;

import com.enoca.api.model.elasticsearch.CustomerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerESRepository extends ElasticsearchRepository<CustomerDocument,Long> {

    List<CustomerDocument> findAll();

    Optional<CustomerDocument> findById(Long id);

    List<CustomerDocument> findByAgeGreaterThanAndNameNotIn(int age , String query);

}
