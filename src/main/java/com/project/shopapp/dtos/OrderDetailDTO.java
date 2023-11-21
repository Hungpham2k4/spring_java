package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order's ID must be > 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product's ID must be > 0")
    private Long productID;

    @Min(value = 0, message = "Price's must be >= 0")
    private Long price;

    @JsonProperty("number_of_product")
    @Min(value = 1, message = "Number_of_product's ID must be > 1")
    private int numberOfProduct;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total_money's must be >= 0")
    private int totalMoney;

    private String color;
}
