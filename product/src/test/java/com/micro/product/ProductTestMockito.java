package com.micro.product;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.micro.product.controller.ProductRepository;
import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductResponse;
import com.micro.product.mapper.ProductMapper;
import com.micro.product.model.Category;
import com.micro.product.model.Product;
import com.micro.product.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductTestMockito {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        // Product product = Product.builder()
        // .name("test")
        // .description("asb")
        // .quantity(10.0)
        // .price(new BigDecimal("99.99"))
        // .build();

        Product product = new Product();

        ProductRequest productRequest = ProductRequest.builder()
                .name("test")
                .description("asb")
                .quantity(10.0)
                .price(new BigDecimal("99.99"))
                .build();

        when(productMapper.toProduct(productRequest)).thenReturn(product);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductRequest saveProduct = productService.createProduct(productRequest);

        Assertions.assertThat(saveProduct).isNotNull();

    }

    @Test
    public void testGetAllProducts() {
        // Create test products
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("test1");
        product1.setDescription("desc1");
        product1.setQuantity(10.0);
        product1.setPrice(new BigDecimal("99.99"));
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Category1");
        category1.setDescription("Category Description 1");
        product1.setCategory(category1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("test2");
        product2.setDescription("desc2");
        product2.setQuantity(20.0);
        product2.setPrice(new BigDecimal("199.99"));
        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Category2");
        category2.setDescription("Category Description 2");
        product2.setCategory(category2);

        // Create corresponding ProductResponses
        ProductResponse productResponse1 = new ProductResponse(
                1, "test1", "desc1", 10.0, new BigDecimal("99.99"),
                1, "Category1", "Category Description 1");
        ProductResponse productResponse2 = new ProductResponse(
                2, "test2", "desc2", 20.0, new BigDecimal("199.99"),
                2, "Category2", "Category Description 2");

        // Mock repository behavior
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Mock mapper behavior
        when(productMapper.toProductResponse(product1)).thenReturn(productResponse1);
        when(productMapper.toProductResponse(product2)).thenReturn(productResponse2);

        // Call the service method
        List<ProductResponse> result = productService.findAll();

        // Assertions
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(productResponse1, productResponse2);

        // Verify that the repository method was called
        verify(productRepository, times(1)).findAll();

        // Verify that the mapper was called for each product
        verify(productMapper, times(1)).toProductResponse(product1);
        verify(productMapper, times(1)).toProductResponse(product2);
    }

    @Test
    public void testGetProductById() {
        // Create test products
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("test1");
        product1.setDescription("desc1");
        product1.setQuantity(10.0);
        product1.setPrice(new BigDecimal("99.99"));
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Category1");
        category1.setDescription("Category Description 1");
        product1.setCategory(category1);

        // Create corresponding ProductResponses
        ProductResponse productResponse1 = new ProductResponse(
                1, "test1", "desc1", 10.0, new BigDecimal("99.99"),
                1, "Category1", "Category Description 1");

        // Mock repository behavior
        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));

        when(productMapper.toProductResponse(product1)).thenReturn(productResponse1);

        // Call the service method
        ProductResponse result = productService.findById(product1.getId());

        // Assertions
        Assertions.assertThat(result).isNotNull();

        // Verify that the repository method was called
        verify(productRepository, times(1)).findById(product1.getId());
        // Verify that the mapper was called for each product
        verify(productMapper, times(1)).toProductResponse(product1);

    }
}
