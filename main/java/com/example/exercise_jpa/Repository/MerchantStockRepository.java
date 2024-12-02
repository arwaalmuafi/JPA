package com.example.exercise_jpa.Repository;

import com.example.exercise_jpa.Model.MerchantStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantStockRepository extends JpaRepository<MerchantStock,Integer> {
    MerchantStock findByMerchantIdAndProductId(Integer merchantId, Integer productId);
}
