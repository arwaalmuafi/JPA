package com.example.exercise_jpa.Repository;

import com.example.exercise_jpa.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant,Integer> {
}
