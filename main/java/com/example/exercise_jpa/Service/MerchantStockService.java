package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.MerchantStock;
import com.example.exercise_jpa.Repository.MerchantStockRepository;
import com.example.exercise_jpa.Repository.ProductRepository;
import com.example.exercise_jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;


    public List<MerchantStock> getAllMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    public Boolean updateMerchantStock(int id, MerchantStock updatedMerchantStock) {
        MerchantStock existingMerchantStock = merchantStockRepository.findById(id).orElse(null);

        if (existingMerchantStock == null) {
            return false;
        }

        existingMerchantStock.setMerchantId(updatedMerchantStock.getMerchantId());
        existingMerchantStock.setProductId(updatedMerchantStock.getProductId());
        existingMerchantStock.setStock(updatedMerchantStock.getStock());

        merchantStockRepository.save(existingMerchantStock);
        return true;
    }

    public Boolean deleteMerchantStock(int id) {
        MerchantStock merchantStock = merchantStockRepository.findById(id).orElse(null);

        if (merchantStock == null) {
            return false;
        }

        merchantStockRepository.delete(merchantStock);
        return true;
    }

//11
    public boolean addStock(Integer productId, Integer merchantId, Integer additionalStock) {
        MerchantStock stock = merchantStockRepository.findByMerchantIdAndProductId(merchantId, productId);
        if (stock != null) {
            stock.setStock(stock.getStock() + additionalStock);
            merchantStockRepository.save(stock);
            return true;
        }
        return false;
    }
//5
    public boolean isStockAvailable(Integer productId, Integer merchantId, Integer quantity) {
        MerchantStock stock = merchantStockRepository.findByMerchantIdAndProductId(merchantId, productId);
        return stock != null && stock.getStock() >= quantity;
    }

    public MerchantStock getStockByProductAndMerchant(Integer merchantId, Integer productId) {
        return merchantStockRepository.findByMerchantIdAndProductId(merchantId, productId);
    }
}
