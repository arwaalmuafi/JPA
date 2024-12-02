package com.example.exercise_jpa.Model;



import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product ID must not be empty")
    @Column(columnDefinition = "int not null")
    private Integer productId;

    @NotNull(message = "Merchant ID must not be empty")
    @Column(columnDefinition = " int not null")
    private Integer merchantId;

    @NotNull(message = "Stock must not be null")
    @Min(value = 0, message = "Stock must be at least 0")
    @Column(columnDefinition = "int not null")
    private Integer stock;
}
