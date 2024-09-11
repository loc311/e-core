package com.micro.ecommerce.model.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.micro.ecommerce.model.Keys;

public interface KeysRepository extends MongoRepository<Keys, String> {
    
    Keys findByCustomerId(String id);
}
