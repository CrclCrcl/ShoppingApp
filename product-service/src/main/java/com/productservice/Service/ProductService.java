package com.productservice.Service;

import com.productservice.Dto.ProductEvent;
import com.productservice.Dto.ProductResponse;
import com.productservice.Entity.Product;
import com.productservice.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;



    public void createProduct(ProductEvent productEvent){
        Product product = Product.builder()
                .name(productEvent.getName())
                .description(productEvent.getDescription())
                .price(productEvent.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product with " + product.getId() + "saved");
    }

    public List<ProductResponse> getAllProducts(){
      List<Product> products = productRepository.findAll();

      return products.stream().map(this::mappingToProductResponse).toList();

    }

    private ProductResponse mappingToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
