package com.example.exercise_jpa.Controller;

import com.example.exercise_jpa.ApiResponse.ApiResponse;
import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        Boolean isUpdated = productService.updateProduct(id, product);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        Boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }
    @GetMapping("/best-selling")
    public ResponseEntity getBestSellingProduct() {
        Product bestSellingProduct = productService.getBestSellingProduct();
        if (bestSellingProduct == null) {
            return ResponseEntity.status(200).body(bestSellingProduct);
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found "));
    }

    @PutMapping("/apply-discount/{merchantID}/{productID}/{discountPercentage}")
    public ResponseEntity applyDiscount(@PathVariable Integer merchantID,
                                                @PathVariable Integer productID,
                                                @PathVariable double discountPercentage) {
        double discountedPrice = productService.applyDiscount(merchantID, productID, discountPercentage);
        if (discountedPrice == -1) {
            return  ResponseEntity.status(400).body(new ApiResponse("not found "));
        }
        return  ResponseEntity.status(200).body(discountedPrice);
    }



}
