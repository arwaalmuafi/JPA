package com.example.exercise_jpa.Service;


import com.example.exercise_jpa.Model.MerchantStock;
import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Model.User;
import com.example.exercise_jpa.Repository.MerchantStockRepository;
import com.example.exercise_jpa.Repository.ProductRepository;
import com.example.exercise_jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

   private final Map<String, List<String>> wishlist = new HashMap<>();

    private final UserRepository userRepository;
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user) {
        User oldUser = userRepository.getById(id);

        if (oldUser == null) {
            return false;
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setBalance(user.getBalance());

        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        User user = userRepository.getById(id);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }

    //12
    public boolean buyProduct(Integer userId, Integer productId, Integer merchantId) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }


        MerchantStock stock = merchantStockRepository.findByMerchantIdAndProductId(merchantId, productId);
        if (stock == null || stock.getStock() <= 0) {
            return false;
        }


        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return false;
        }


        if (user.getBalance() < product.getPrice()) {
            return false;
        }


        user.setBalance(user.getBalance() - product.getPrice());
        stock.setStock(stock.getStock() - 1);
        productService.increaseSalesCount(product);


        userRepository.save(user);
        merchantStockRepository.save(stock);
        productRepository.save(product);

        return true;
    }
    //11
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    //1

    public boolean transferBalance(Integer fromUserId, Integer toUserId, double amount) {
        if (amount <= 0) {
            return false;
        }

        User sender = userRepository.findById(fromUserId).orElse(null);
        User receiver = userRepository.findById(toUserId).orElse(null);

        if (sender == null || receiver == null) {
            return false;
        }

        if (sender.getBalance() < amount) {
            return false;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        return true;
    }

    public boolean addToWishlist(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return false;
        }

        List<String> Wishlist = wishlist.getOrDefault(userId.toString(), new ArrayList<>());
        if (Wishlist.contains(productId)) {
            return false;
        }
        Wishlist.add(String.valueOf(productId));
        wishlist.put(userId.toString(), (List<String>) wishlist);

        return true;
    }
}
