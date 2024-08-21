// package com.micro.product.controller;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.math.BigDecimal;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.web.servlet.MockMvc;

// import com.micro.product.dto.request.ProductRequest;

// import lombok.extern.slf4j.Slf4j;

// @ExtendWith(MockitoExtension.class)
// @Slf4j
// public class ProductControllerTest {

//     @Mock
//     private ProductService productService;

//     @InjectMocks
//     private ProductController productController;

//     // @BeforeEach
//     // void setUp() {
//     //     MockitoAnnotations.openMocks(this);
//     // }

//     @Test
//     void testCreateProduct(){

//         //arrange
//         ProductRequest request = new ProductRequest(1, "test pr", "deas", 180.0, new BigDecimal("99.99"), 2);

//         when(productService.createProduct(any(ProductRequest.class))).thenReturn(1);
        
//         //act
//         ResponseEntity<Integer> response = productController.createProduct(request);
//         // Assert
//         assertEquals(HttpStatus.OK, response.getStatusCode()); //check kết quả là OK
//         assertEquals(1, response.getBody());
//         verify(productService, times(1)).createProduct(request);
//     }


//     @Test
//     void createUser(){
//         log.info("testing");
//     }

// }
