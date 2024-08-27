package com.micro.product.controller;

// // import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.BDDMockito.given;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import java.math.BigDecimal;

// import org.junit.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentMatchers;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import com.micro.product.dto.request.ProductRequest;
// import com.micro.product.dto.response.ProductResponse;
// import com.micro.product.mapper.ProductMapper;
// import com.micro.product.model.Product;
// import com.micro.product.service.ProductService;

// @WebMvcTest(controllers = ProductController.class)
// @AutoConfigureMockMvc(addFilters = false)
// @ExtendWith(MockitoExtension.class)
// public class ControlerTestMock {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private ProductService productService;

//     @Autowired
//     private ProductMapper productMapper;

//     private Product product;

//     private ProductRequest request;
//     private ObjectMapper objectMapper = new ObjectMapper();

//     private static final String PRODUCT_URL = "/api/v1/products/create";

//     private ProductRequest mockProductRequest() {
//         return new ProductRequest(
//                 1,
//                 "test",
//                 "asb",
//                 10.0,
//                 new BigDecimal("99.99"),
//                 1);
//     }

//     private ProductResponse mockProductRepsonse() {
//         return new ProductResponse(1,
//                 "test",
//                 "asb",
//                 10.0,
//                 new BigDecimal("99.99"),
//                 1,
//                 "test",
//                 "asb");
//     }

//     @BeforeEach
//     public void init() {
//         product = Product.builder()
//                 .name("test")
//                 .description("asb")
//                 .quantity(10.0)
//                 .price(new BigDecimal("99.99"))
//                 .build();

//         request = ProductRequest.builder()
//                 .name("test")
//                 .description("asb")
//                 .quantity(10.0)
//                 .price(new BigDecimal("99.99"))
//                 .build();
//     }

//     // 3 thứ cần test controller: request -> service -> return type?
//     @Test
//     void testValid_WhenNameIsNull_ThenThrowException() throws Exception {
//         ProductRequest mockRequest = mockProductRequest();
//         mockRequest.setName("");

//         mockMvc.perform(
//                 post(PRODUCT_URL)
//                         .contentType("application/json")
//                         .content(productMapper.toCustomString(mockRequest)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(jsonPath("$data.code").value("name not blank"))
//                 .andDo(print());
        
//     }

//     @Test
//     public void ProductControler_Test() throws Exception {

//         // given(productService.createProduct(ArgumentMatchers.any())).willAnswer(invocation
//         // -> invocation.getArgument(1));

//         // ResultActions response = mockMvc.perform(post("/api/v1/products/create")
//         // .contentType(MediaType.APPLICATION_JSON)
//         // .content(productMapper.toProductResponse(product)))
//         // .andExpect(status().isOk());
//         ProductResponse productResponse = productMapper.toProductResponse(product);
//         String productJson = objectMapper.writeValueAsString(productResponse);

//         ResultActions response = mockMvc.perform(post("/api/v1/products/create")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(productJson));
//         response.andExpect(MockMvcResultMatchers.status().isCreated());
//     }
// }
