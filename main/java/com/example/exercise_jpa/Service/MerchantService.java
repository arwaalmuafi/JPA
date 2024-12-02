package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.Merchant;
import com.example.exercise_jpa.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public Boolean updateMerchant(int id, Merchant updatedMerchant) {
        Merchant existingMerchant = merchantRepository.findById(id).orElse(null);

        if (existingMerchant == null) {
            return false;
        }

        existingMerchant.setName(updatedMerchant.getName());
        existingMerchant.setRating(updatedMerchant.getRating());
        merchantRepository.save(existingMerchant);
        return true;
    }

    public Boolean deleteMerchant(int id) {
        Merchant merchant = merchantRepository.findById(id).orElse(null);

        if (merchant == null) {
            return false;
        }

        merchantRepository.delete(merchant);
        return true;
    }
}
