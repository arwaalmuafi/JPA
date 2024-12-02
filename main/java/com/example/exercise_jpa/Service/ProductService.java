package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.MerchantStock;
import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MerchantStockService merchantStockService;


    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Boolean updateProduct(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    //3
    public void increaseSalesCount(Product product) {

        product.setSalesCount(product.getSalesCount() + 1);

        productRepository.save(product);
    }
    //3

    public Product getBestSellingProduct() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return null;
        }

        Product bestSellingProduct = products.get(0);

        for (Product product : products) {
            if (product.getSalesCount() > bestSellingProduct.getSalesCount()) {
                bestSellingProduct = product;
            }
        }

        return bestSellingProduct;
    }

    //4

    public double applyDiscount(Integer merchantID, Integer productID, double discountPercentage) {
        Product product = productRepository.findById(productID).orElse(null);
        if (product == null) {
            return -1;
        }

        MerchantStock stock = merchantStockService.getStockByProductAndMerchant(merchantID,productID);
        if (stock == null) {
            return -1;
        }

        if (!stock.getMerchantId().equals(merchantID)) {
            return -1;
        }

        double originalPrice = product.getPrice();
        double discountedPrice = originalPrice - (originalPrice * discountPercentage / 100);

        product.setPrice(discountedPrice);
        productRepository.save(product);

        return discountedPrice;
    }

}