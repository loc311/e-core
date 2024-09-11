package com.micro.ecommerce.model.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.ecommerce.model.Customer;

import org.springframework.data.mongodb.repository.Update;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
    boolean existsByEmail(String email);

    @Query("{ $and: [ " +
            "{ firstname: { $regex: ?0, $options: 'i' } }, " +
            "{ lastname: { $regex: ?1, $options: 'i' } } " +
            "] }")
    List<Customer> findByFirstnameContainingAndLastnameContaining(String firstname, String lastname);

    @Query("{ $or: [ " +
            "{ $expr: { $eq: [null, :keyword] } }, " +
            "{ $expr: { $regexMatch: { " +
            "    input: { $concat: ['$firstname', ' ', '$lastname'] }, " +
            "    regex: :keyword, " +
            "    options: 'i' " +
            "} } } " +
            "] }")
    int countByFullNameContaining(@Param("keyword") String keyword);

    @Update("{ '$set': { 'isActive': true } }")
    void active(@Param("id") String id);

//     @Update("{ '$set': { 'isActive': false } }")
//     void deactivate(@Param("id") String id);

    @Query(value="{ 'email' : ?0 }", fields="{ 'isActive' : false }")
    void deactivateByEmail(String email);

    @Query("{ 'email': ?0 }")
    Customer getByEmail(String email);

    Customer getById(String id);
}
