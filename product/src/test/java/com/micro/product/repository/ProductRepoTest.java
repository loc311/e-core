package com.micro.product.repository;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.micro.product.controller.ProductRepository;
import com.micro.product.model.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepoTest {
    
    @Autowired
    private ProductRepository productRepo;

    @Test
    public void ProductRepo_Save() {

        //arrange
        Product product = Product.builder()
                                .name("test")
                                .description("asb")
                                .quantity(10.0)
                                .price(new BigDecimal("99.99"))
                                .build();
        
        //act
        Product saveProduct = productRepo.save(product);

        //assert
        Assertions.assertThat(saveProduct).isNotNull();
        Assertions.assertThat(saveProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void ProductRepo_FindAll() { 
        //arrange
        Product product1 = Product.builder()
                                .name("test1")
                                .description("asb1")
                                .quantity(10.0)
                                .price(new BigDecimal("99.99"))
                                .build();
        Product product2 = Product.builder()
                                .name("test2")
                                .description("asb1")
                                .quantity(10.0)
                                .price(new BigDecimal("99.99"))
                                .build();

        productRepo.save(product1);
        productRepo.save(product2);

        //act
        Iterable<Product> products = productRepo.findAll();
        //assert
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products).hasSize(27);
    } 

    @Test
    public void ProductRepo_FindById() {
        //arrange
        Product product = Product.builder()
                                .name("test")
                                .description("asb")
                                .quantity(10.0)
                                .price(new BigDecimal("99.99"))
                                .build();
        productRepo.save(product);
        //act
        Product product1 = productRepo.findById(product.getId()).get();
        //assert
        Assertions.assertThat(product1).isNotNull();
        Assertions.assertThat(product1.getId()).isEqualTo(product.getId());
        Assertions.assertThat(product1.getName()).isEqualTo(product.getName());
        Assertions.assertThat(product1.getDescription()).isEqualTo(product.getDescription());
        Assertions.assertThat(product1.getQuantity()).isEqualTo(product.getQuantity());
        Assertions.assertThat(product1.getPrice()).isEqualTo(product.getPrice());
    }
}
