package com.example.exercise_jpa.Controller;


import com.example.exercise_jpa.ApiResponse.ApiResponse;
import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Model.User;
import com.example.exercise_jpa.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isTrue = userService.updateUser(id, user);
        if (isTrue) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }
    @PostMapping("/buyProduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId) {
        boolean success = userService.buyProduct(userId, productId, merchantId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Product purchased successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Purchase failed"));
    }

    @PostMapping("/transferBalance")
    public ResponseEntity transferBalance(@RequestParam Integer fromUserId, @RequestParam Integer toUserId, @RequestParam double amount) {
        boolean success = userService.transferBalance(fromUserId, toUserId, amount);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Balance transferred successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Balance transfer failed"));
    }

    @PostMapping("/addToWishlist/{userId}/{productId}")
    public ResponseEntity addToWishlist(@PathVariable Integer userId, @PathVariable Integer productId) {
        boolean success = userService.addToWishlist(userId, productId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Product added to wishlist"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to add product to wishlist"));
    }

  
}
